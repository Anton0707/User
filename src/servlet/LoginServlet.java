package servlet;

import dao.impl.UserDaoImpl;
import entities.User;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by admin on 9/2/17.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {


    UserDaoImpl userDao = new UserDaoImpl();
    private String email = null;
    private String password = null;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        email = request.getParameter("email");
        password = request.getParameter("pass");

        try {
            if (userDao.checkUser(email,password)) {

                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                response.sendRedirect("welcome.jsp");

            } else {
                response.sendRedirect("login.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
