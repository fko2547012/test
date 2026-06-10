package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDAO;

@WebServlet("/SubjectCreateServlet")
public class SubjectCreateServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "subject_create.jsp")
               .forward(request, response);
    }
    
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            // フォームから値取得
            String cd = request.getParameter("cd");
            String name = request.getParameter("name");

            // Beanへセット
            Subject subject = new Subject();
            subject.setCd(cd);
            subject.setName(name);

            // DAO実行
            SubjectDAO dao = new SubjectDAO();
            boolean result = dao.insert(subject);

            if (result) {

                // 登録成功
            	request.getRequestDispatcher(
                        "subject_create_done.jsp")
                       .forward(request, response);

            } else {

                // 登録失敗
                request.setAttribute("error", "登録に失敗しました");

                request.getRequestDispatcher("subject_create.jsp")
                       .forward(request, response);
            }

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute("error", "システムエラーが発生しました");

            request.getRequestDispatcher(
                    "subject_create_done.jsp")
                   .forward(request, response);
        }
    }
}