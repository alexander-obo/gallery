package ao.gallery.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        try {
            request.login(userName, password);
            response.sendRedirect("profile?user=" + request.getRemoteUser());
        } catch (ServletException ex) {
            log("Login failed", ex);
            session.invalidate();
            response.sendRedirect("login_failed.html");
        }
    }
}
