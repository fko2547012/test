package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDAO;

@WebServlet("/SubjectDeleteServlet")
public class SubjectDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String cd = request.getParameter("cd");

            SubjectDAO dao = new SubjectDAO();

            Subject subject = dao.findByCd(cd);

            request.setAttribute("subject", subject);

            request.getRequestDispatcher(
                    "subject_delete.jsp"
            ).forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String cd = request.getParameter("cd");

            SubjectDAO dao = new SubjectDAO();

            boolean result = dao.delete(cd);

            if (result) {

            	request.getRequestDispatcher(
                        "subject_delete_done.jsp")
                       .forward(request, response);

            } else {

                request.setAttribute(
                        "errorMsg",
                        "削除に失敗しました"
                );

                request.getRequestDispatcher(
                        "subject_delete.jsp"
                ).forward(request, response);
            }

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                    "errorMsg",
                    "システムエラーが発生しました"
            );

            request.getRequestDispatcher(
                    "subject_delete_done.jsp")
                   .forward(request, response);
        }
    }
}