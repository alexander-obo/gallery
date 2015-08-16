package ao.gallery.web;

import ao.gallery.dao.DAO;
import ao.gallery.dao.DAOException;
import ao.gallery.dao.MySQLDAO;
import ao.gallery.dao.User;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationController extends HttpServlet {
    
    private final DAO dao = MySQLDAO.getInstance();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User(email, login, password);
        try {
            dao.addUser(user);
            response.sendRedirect("login.html");
        } catch (DAOException e) {
            log("Error adding user", e);
            response.sendRedirect("registration.html");
        }
    }
}