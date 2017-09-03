package servlet;

import dao.impl.UserDaoImpl;
import entities.User;
import services.impl.ItemServiceImpl;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

/**
 * Created by admin on 9/2/17.
 */
@WebServlet("/create")
public class CreateAccServlet extends HttpServlet {
    private String lastName = null;
    private String firstName = null;
    private String password = null;
    private String login = null;
    private String email = null;
    private Date dOb = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        lastName = request.getParameter("lastName");
        firstName = request.getParameter("firstName");
        password = request.getParameter("password");
        login = request.getParameter("login");
        email = request.getParameter("email");
        java.util.Date utilDate = Date.valueOf(request.getParameter("dOb"));
        dOb = new Date(utilDate.getTime());

            User user = new User(lastName, firstName, password, login, email, dOb);
            try {
                UserDaoImpl.getInstance().save(user);
                request.setAttribute("list", ItemServiceImpl.getInstance().getAll());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
        rd.forward(request, response);
    }
}

