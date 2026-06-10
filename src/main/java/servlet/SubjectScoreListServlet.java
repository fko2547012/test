package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 必要に応じて対応するBeanとDAOのパッケージをインポートしてください
import bean.SubjectScore;
import dao.SubjectScoreDAO;

@WebServlet("/SubjectScoreListServlet")
public class SubjectScoreListServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            // DAO作成
            SubjectScoreDAO dao = new SubjectScoreDAO();

            // 科目スコア一覧取得
            List<SubjectScore> list = dao.findAll();

            // requestへ保存
            request.setAttribute("subjectScoreList", list);

            // JSPへ転送（遷移先のJSP名に合わせて変更してください）
            request.getRequestDispatcher("subject_score_list.jsp")
                   .forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
