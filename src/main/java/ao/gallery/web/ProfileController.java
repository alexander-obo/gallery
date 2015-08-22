package ao.gallery.web;

import ao.gallery.util.Util;
import ao.gallery.dao.DAOException;
import ao.gallery.dao.DAO;
import ao.gallery.dao.MySQLDAO;
import ao.gallery.dao.Picture;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
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

public class ProfileController extends HttpServlet {

    private final DAO dao = MySQLDAO.getInstance();
    // 15 MB
    private final int maxFileSize = 15 * 1024 * 1024;
    // 10 MB
    private final int maxMemorySize = 10 * 1024 * 1024;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        preparePicturesToRender(request);
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
            log("Adding picture exception", ex);
        }
    }

    private void preparePicturesToRender(HttpServletRequest request) {
        try {
            List<Picture> pictures = dao.getUserPictures(request.getParameter("user"));
            List<String> base64Thumbnailes = new ArrayList<>();
            Base64.Encoder encoder = Base64.getEncoder();
            for (Picture picture : pictures) {
                byte[] thumbnailBytes = picture.getThumbnail();
                base64Thumbnailes.add(encoder.encodeToString(thumbnailBytes));
            }
            request.setAttribute("thumbnails", base64Thumbnailes);
        } catch (DAOException ex) {
            log("Error getting pictures", ex);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.invalidate();
        request.logout();
        response.sendRedirect("login");
    }

}
