package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDAO;

@WebServlet("/SubjectListServlet")
public class SubjectListServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            SubjectDAO dao = new SubjectDAO();

            List<Subject> list = dao.findAll();

            System.out.println("===== Subject List =====");
            System.out.println("件数：" + list.size());

            for (Subject s : list) {
                System.out.println(
                    s.getCd() + " : " + s.getName()
                );
            }

            request.setAttribute("subjectList", list);

            request.getRequestDispatcher("subject_list.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
