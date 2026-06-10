package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDAO;

@WebServlet("/SubjectUpdateServlet")
public class SubjectUpdateServlet extends HttpServlet {

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

            request.getRequestDispatcher("subject_update.jsp")
                   .forward(request, response);

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
            String name = request.getParameter("name");

            Subject subject = new Subject();

            subject.setCd(cd);
            subject.setName(name);

            SubjectDAO dao = new SubjectDAO();

            boolean result = dao.update(subject);

            if (result) {

            	if(result){

            	    request.getRequestDispatcher(
            	            "subject_update_done.jsp")
            	           .forward(request, response);

            	}

            } else {

                request.setAttribute(
                        "errorMsg",
                        "更新に失敗しました"
                );

                request.setAttribute(
                        "subject",
                        subject
                );

                request.getRequestDispatcher(
                        "subject_update.jsp"
                ).forward(request, response);
            }

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                    "errorMsg",
                    "システムエラーが発生しました"
            );

            request.getRequestDispatcher(
                    "subject_update_done.jsp")
                   .forward(request, response);
        }
    }
}