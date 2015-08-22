package ao.gallery.web;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        try {
            request.login(userName, password);
            response.sendRedirect("profile?user=" + request.getRemoteUser());
        } catch (ServletException ex) {
            log("Login failed", ex);
            session.invalidate();
            request.setAttribute("errorMessage", ex.getMessage());
            forwardToLoginJsp(request, response);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardToLoginJsp(request, response);
    }

    private void forwardToLoginJsp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
        dispatcher.forward(request, response);
    }
}
