package controller;

import dao.AdminDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Admin;

import java.io.IOException;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    AdminDao adminDao = new AdminDao();
    HttpSession session;
    RequestDispatcher dispatcher;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Admin admin = new Admin(username, password);
        boolean result = adminDao.adminLoginDao(admin);
        session = request.getSession();
        if(result){
            if(session != null){
                session.removeAttribute("error");
                session.setAttribute("admin", username);
            }
            response.sendRedirect("student");
        }else{
            session.setAttribute("error", "Username or password is incorrect.");
            dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(session != null){
            session.removeAttribute("admin");
            session.removeAttribute("error");
        }
        dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

}

