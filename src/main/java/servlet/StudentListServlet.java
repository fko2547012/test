package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDAO;

@WebServlet("/StudentListServlet")
public class StudentListServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            StudentDAO dao = new StudentDAO();

            List<Student> list = dao.findAll();

            request.setAttribute("studentList", list);

            request.getRequestDispatcher("student_list.jsp")
                   .forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
