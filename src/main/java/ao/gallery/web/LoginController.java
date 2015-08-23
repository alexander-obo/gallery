package ao.gallery.web;

import ao.gallery.util.ValidationUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        List<String> errorMessages = new ArrayList<>();
        if (!ValidationUtil.isLoginValid(userName)) {
            errorMessages.add("Login is not valid");
        }
        if (!ValidationUtil.isPasswordValid(password)) {
            errorMessages.add("Password is not valid");
        }
        if (!errorMessages.isEmpty()) {
            request.setAttribute("errorMessages", errorMessages);
            forwardToLoginPage(request, response);
            return;
        }
        HttpSession session = request.getSession();
        try {
            request.login(userName, password);
            response.sendRedirect("profile?user=" + request.getRemoteUser());
        } catch (ServletException ex) {
            log("Login failed", ex);
            session.invalidate();
            errorMessages.add(ex.getMessage());
            request.setAttribute("errorMessages", errorMessages);
            forwardToLoginPage(request, response);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardToLoginPage(request, response);
    }

    private void forwardToLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
        dispatcher.forward(request, response);
    }
}
