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

            // DAO作成
            StudentDAO dao = new StudentDAO();

            // 学生一覧取得
            List<Student> list = dao.findAll();

            // requestへ保存
            request.setAttribute("studentList", list);

            // JSPへ転送
            request.getRequestDispatcher("student_list.jsp")
                   .forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}