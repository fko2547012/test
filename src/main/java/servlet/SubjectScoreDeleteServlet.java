package servlet;

import java.io.IOException;

import dao.SubjectScoreDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SubjectScoreDeleteServlet")
public class SubjectScoreDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String studentId = request.getParameter("studentId");
            String subjectName = request.getParameter("subjectName");

            SubjectScoreDAO dao = new SubjectScoreDAO();

            dao.delete(studentId, subjectName);

            response.sendRedirect("SubjectScoreListServlet");

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
