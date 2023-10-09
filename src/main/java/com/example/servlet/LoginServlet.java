package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       if (request.getSession().getAttribute("user") == null) {
           response.sendRedirect(request.getContextPath() + "/login.jsp");
       } else {
           response.sendRedirect(request.getContextPath() + "/user/hello.jsp");
       }
    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if ("user".equals(login) || "admin".equals(login) && !password.isEmpty()) {
            request.getSession().setAttribute("user", login);
            response.sendRedirect(request.getContextPath() + "/user/hello.jsp");
        } else {
            request.setAttribute("error", "Invalid login credentials");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
