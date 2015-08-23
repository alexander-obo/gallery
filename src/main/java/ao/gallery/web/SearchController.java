package ao.gallery.web;

import ao.gallery.dao.DAO;
import ao.gallery.dao.DAOException;
import ao.gallery.dao.MySQLDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchController extends HttpServlet {

    private final DAO dao = MySQLDAO.getInstance();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> usersNames = new ArrayList<>();
        try {
            usersNames = dao.getUsersNames("");
        } catch (DAOException ex) {
            log("Error loading users names", ex);
        }
        request.setAttribute("usersNames", usersNames);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search.jsp");
        dispatcher.forward(request, response);
    }

}
