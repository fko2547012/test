package servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.TestDAO;
import tool.Action;

public class TestDeleteExecuteAction
        implements Action {

    @Override
    public void execute(
            HttpServletRequest req,
            HttpServletResponse res)
            throws Exception {

        String subjectNo =
                req.getParameter("subject_cd");

        int no =
                Integer.parseInt(
                        req.getParameter("no"));

        String[] studentNos =
                req.getParameterValues(
                        "delete_student_no");

        if (studentNos == null
                || studentNos.length == 0) {

            req.setAttribute(
                    "error",
                    "削除対象を選択してください");

            req.getRequestDispatcher(
                    "/test_delete.jsp")
               .forward(req, res);

            return;
        }

        TestDAO dao =
                new TestDAO();

        for (String studentNo
                : studentNos) {

            dao.clearPoint(
                    studentNo,
                    subjectNo,
                    no);
        }

        req.setAttribute(
                "message",
                "成績を削除しました。");

        req.getRequestDispatcher(
                "/test_delete.jsp")
           .forward(req, res);
    }
}