package ao.gallery.web;

import ao.gallery.util.Util;
import ao.gallery.dao.DAOException;
import ao.gallery.dao.DAO;
import ao.gallery.dao.MySQLDAO;
import ao.gallery.dao.Picture;
import ao.gallery.web.session.Profile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProfileController extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger(ProfileController.class);
    private static final int FILES_COUNT_LIMIT = 5;

    private final DAO dao = MySQLDAO.getInstance();
    // 15 MB
    private final int maxFileSize = 15 * 1024 * 1024;
    // 10 MB
    private final int maxMemorySize = 10 * 1024 * 1024;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Picture> pictures = getPictures(request.getParameter("user"));
        Profile profile = new Profile();
        profile.setPictures(pictures);
        HttpSession session = request.getSession();
        session.setAttribute("profile", profile);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("logout")) {
            logout(request, response);
            return;
        }
        if (ServletFileUpload.isMultipartContent(request)) {
            processMultipartContent(request);
        }
        response.sendRedirect("profile?user=" + request.getRemoteUser());
    }

    private void processMultipartContent(HttpServletRequest request) {
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        diskFileItemFactory.setSizeThreshold(maxMemorySize);
        // Location to save data that imaxMemSizes larger than maxMemSize.
        diskFileItemFactory.setRepository(new File("c:\\temp"));
        // Create a new file upload handler
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        // maximum file size to be uploaded.
        servletFileUpload.setSizeMax(maxFileSize);
        try {
            List<FileItem> fileItems = servletFileUpload.parseRequest(request);
            if (fileItems.size() > FILES_COUNT_LIMIT) {
                return;
            }
            for (FileItem fileItem : fileItems) {
                if (!fileItem.isFormField() && fileItem.getSize() > 0) {
                    String fileName = fileItem.getName();
                    String fileUploaderName = request.getRemoteUser();
                    byte[] pictureBytes = fileItem.get();
                    byte[] thumbnailBytes = Util.getPictureThumbnail(pictureBytes);
                    Picture picture = new Picture(fileName, fileUploaderName, pictureBytes, thumbnailBytes);
                    dao.addPicture(picture);
                }
            }
        } catch (FileUploadException | IOException | DAOException ex) {
            LOG.error("Adding picture exception", ex);
        }
    }

    private List<Picture> getPictures(String userName) {
        List<Picture> pictures = new ArrayList<>();
        try {
            pictures = dao.getUserPictures(userName);
        } catch (DAOException ex) {
            LOG.error("Error getting pictures", ex);
        }
        return pictures;
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.invalidate();
        request.logout();
        response.sendRedirect("login");
    }

}
