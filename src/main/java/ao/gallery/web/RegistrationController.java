package ao.gallery.web;

import ao.gallery.dao.DAO;
import ao.gallery.dao.DAOException;
import ao.gallery.dao.MySQLDAO;
import ao.gallery.dao.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegistrationController extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger(RegistrationController.class);
    private final DAO dao = MySQLDAO.getInstance();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User(email, login, password);
        try {
            dao.addUser(user);
            response.sendRedirect("login");
        } catch (DAOException e) {
            LOG.error("Error adding user", e);
            request.setAttribute("errorMessage", e.getMessage());
            forwardToRegistrationPage(request, response);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardToRegistrationPage(request, response);
    }

    private void forwardToRegistrationPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp");
        dispatcher.forward(request, response);
    }
}
