package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.score_managerDAO;

@WebServlet("/StudentScoreListServlet")
public class Score_manager extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            // DAO作成
            score_managerDAO dao = new score_managerDAO();

            // 成績一覧取得
            List<Score_manager> list = dao.findAll();

            // requestへ保存
            request.setAttribute("studentScoreList", list);

            // JSPへ転送（遷移先のJSP名に合わせて変更してください）
            request.getRequestDispatcher("student_score_list.jsp")
                   .forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
