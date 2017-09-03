package servlet;

import entities.Item;
import services.impl.ItemServiceImpl;
import services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by admin on 9/1/17.
 */
@WebServlet("/")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            req.setAttribute("list", ItemServiceImpl.getInstance().getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }
}
