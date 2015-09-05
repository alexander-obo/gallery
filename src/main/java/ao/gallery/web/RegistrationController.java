package ao.gallery.web;

import ao.gallery.dao.DAO;
import ao.gallery.dao.DAOException;
import ao.gallery.dao.MySQLDAO;
import ao.gallery.dao.User;
import ao.gallery.util.ValidationUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        String reenteredPassword = request.getParameter("reenteredPassword");
        List<String> errorMessages = new ArrayList<>();
        if (!ValidationUtil.isEmailValid(email)) {
            errorMessages.add("Email is not valid");
        }
        if (!ValidationUtil.isLoginValid(login)) {
            errorMessages.add("Login is not valid");
        }
        if (!password.equals(reenteredPassword)) {
            errorMessages.add("Passwords are not equal");
        } else if (!ValidationUtil.isPasswordValid(password)) {
            errorMessages.add("Password is not valid");
        }
        if (!errorMessages.isEmpty()) {
            request.setAttribute("errorMessages", errorMessages);
            forwardToRegistrationPage(request, response);
            return;
        }
        User user = new User(email, login, password);
        try {
            dao.addUser(user);
            response.sendRedirect("login");
        } catch (DAOException e) {
            LOG.error("Error adding user", e);
            errorMessages.add(e.getMessage());
            request.setAttribute("errorMessages", errorMessages);
            forwardToRegistrationPage(request, response);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRemoteUser() == null) {
            forwardToRegistrationPage(request, response);
        } else {
            response.sendRedirect("profile?user=" + request.getRemoteUser());
        }
    }

    private void forwardToRegistrationPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp");
        dispatcher.forward(request, response);
    }
}
