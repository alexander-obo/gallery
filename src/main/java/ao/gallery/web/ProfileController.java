package ao.gallery.web;

import ao.gallery.util.Util;
import ao.gallery.dao.DAOException;
import ao.gallery.dao.DAO;
import ao.gallery.dao.MySQLDAO;
import ao.gallery.dao.Picture;
import ao.gallery.web.session.Profile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProfileController extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger(ProfileController.class);
    private static final int FILES_COUNT_LIMIT = 5;

    private final DAO dao = MySQLDAO.getInstance();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Picture> pictures = getPictures(request.getParameter("user"));
        Profile profile = new Profile();
        profile.setPictures(Util.convertPicturesDataToView(pictures));
        HttpSession session = request.getSession();
        session.setAttribute("profile", profile);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") != null) {
            int pictureId = Integer.parseInt(request.getParameter("id"));
            Profile profile = (Profile) request.getSession().getAttribute("profile");
            for (ao.gallery.web.Picture picture : profile.getPictures()) {
                if (picture.getId() == pictureId) {
                    response.getWriter().write(picture.getContent());
                    break;
                }
            }
            return;
        }
        String action = request.getParameter("action");
        if (action != null && action.equals("logout")) {
            logout(request, response);
            return;
        }
        processMultipartContent(request);
        response.sendRedirect("profile?user=" + request.getRemoteUser());
    }

    private void processMultipartContent(HttpServletRequest request) {
        try {
            int filesCount = 0;
            for (Part part : request.getParts()) {
                if (part.getName().equals("pictures") && part.getSize() > 0) {
                    filesCount++;
                }
            }
            if (filesCount > FILES_COUNT_LIMIT) {
                return;
            }
            for (Part part : request.getParts()) {
                if (part.getName().equals("pictures") && part.getSize() > 0) {
                    String fileName = part.getSubmittedFileName();
                    String fileUploaderName = request.getRemoteUser();
                    byte[] pictureBytes = new byte[(int) part.getSize()];
                    part.getInputStream().read(pictureBytes);
                    byte[] thumbnailBytes = Util.getPictureThumbnail(pictureBytes);
                    Picture picture = new Picture(fileName, fileUploaderName, pictureBytes, thumbnailBytes);
                    dao.addPicture(picture);
                }
            }
        } catch (ServletException | IOException | DAOException ex) {
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
