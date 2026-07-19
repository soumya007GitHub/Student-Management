package controller;

import dao.StudentDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Student;

import java.io.IOException;
import java.util.List;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    HttpSession session;
    private final StudentDao studentDao = new StudentDao();
    private RequestDispatcher dispatcher;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "view";
        }

        switch (action) {

            case "view":
                showStudents(request, response);
                break;

            case "add":
                dispatcher = request.getRequestDispatcher("add.jsp");
                dispatcher.forward(request, response);
                break;

            case "edit":
                showEditPage(request, response);
                break;

            case "delete":
                deleteStudent(request, response);
                break;

            default:
                response.sendRedirect("student?action=view");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action) {

            case "add":
                addStudent(request, response);
                break;

            case "edit":
                editStudent(request, response);
                break;

            default:
                response.sendRedirect("student?action=view");
        }
    }

    public void showStudents(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Student> students = studentDao.viewStudentDao();

        request.setAttribute("students", students);

        dispatcher = request.getRequestDispatcher("homepage.jsp");
        dispatcher.forward(request, response);
    }

    public void addStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Student student = new Student(
                request.getParameter("firstName"),
                request.getParameter("lastName"),
                request.getParameter("email"),
                request.getParameter("phone"),
                request.getParameter("gender"),
                request.getParameter("dob"),
                request.getParameter("course"),
                Integer.parseInt(request.getParameter("year")),
                request.getParameter("address")
        );

        if (!studentDao.addStudentDao(student)) {
            session = request.getSession();
            session.setAttribute("error", "Failed to add student details.");
        }else{
            if(session != null){
                session.removeAttribute("error");
            }
        }
        response.sendRedirect("student?action=view");
    }

    public void showEditPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");

        List<Student> students = studentDao.searchStudentDao(email);

        if (!students.isEmpty()) {
            request.setAttribute("student", students.get(0));
        }

        dispatcher = request.getRequestDispatcher("edit.jsp");
        dispatcher.forward(request, response);
    }

    public void editStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");

        Student student = new Student(
                request.getParameter("firstName"),
                request.getParameter("lastName"),
                email,
                request.getParameter("phone"),
                request.getParameter("gender"),
                request.getParameter("dob"),
                request.getParameter("course"),
                Integer.parseInt(request.getParameter("year")),
                request.getParameter("address")
        );

        if (!studentDao.updateStudentDao(student, email)) {
            session = request.getSession();
            session.setAttribute("error", "Failed to update student details.");
        }else{
            if(session != null){
                session.removeAttribute("error");
            }
        }
        response.sendRedirect("student?action=view");
    }

    public void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String email = request.getParameter("email");

        if(!studentDao.deleteStudentDao(email)){
            session = request.getSession();
            session.setAttribute("error", "Failed to delete student details.");
        }else{
            if(session != null){
                session.removeAttribute("error");
            }
        }

        response.sendRedirect("student?action=view");
    }
}