package servlet;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Test;
import dao.ClassNumDAO;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestDAO;
import tool.Action;

public class TestRegistAction implements Action {

    @Override
    public void execute(
            HttpServletRequest req,
            HttpServletResponse res)
            throws Exception {

        // プルダウン用データ取得
        StudentDAO studentDao =
                new StudentDAO();

        SubjectDAO subjectDao =
                new SubjectDAO();

        ClassNumDAO classDao =
                new ClassNumDAO();

        // 入学年度一覧作成
        List<Integer> entYearList =
                studentDao.findAll()
                          .stream()
                          .map(Student::getEntYear)
                          .distinct()
                          .sorted()
                          .collect(Collectors.toList());

        req.setAttribute(
                "entYearList",
                entYearList);

        req.setAttribute(
                "classNumList",
                classDao.findAll());

        req.setAttribute(
                "subjectList",
                subjectDao.findAll());

        // 初回表示
        if (req.getParameter("ent_year") == null) {

            req.getRequestDispatcher(
                    "/test_regist.jsp")
               .forward(req, res);

            return;
        }

        // 検索条件取得
        String entYearStr =
                req.getParameter("ent_year");

        String classNum =
                req.getParameter("class_num");

        String subjectNo =
                req.getParameter("subject_cd");

        String noStr =
                req.getParameter("no");

        // 必須チェック
        if (entYearStr.isEmpty()
                || classNum.isEmpty()
                || subjectNo.isEmpty()
                || noStr.isEmpty()) {

            req.setAttribute(
                    "error",
                    "すべて選択してください");

            req.getRequestDispatcher(
                    "/test_regist.jsp")
               .forward(req, res);

            return;
        }

        int entYear =
                Integer.parseInt(entYearStr);

        int no =
                Integer.parseInt(noStr);

        // 成績検索
        TestDAO dao =
                new TestDAO();

        List<Test> testList =
                dao.findForRegist(
                        entYear,
                        classNum,
                        subjectNo,
                        no);

        // JSPへ渡す
        req.setAttribute(
                "testList",
                testList);
        if(testList.isEmpty()){
            req.setAttribute(
                "error",
                "該当する学生が存在しません。");
        }

        req.setAttribute(
                "selectedYear",
                entYear);

        req.setAttribute(
                "selectedClass",
                classNum);

        req.setAttribute(
                "selectedSubject",
                subjectNo);

        req.setAttribute(
                "selectedNo",
                no);

        req.setAttribute(
                "isSearched",
                true);

        req.getRequestDispatcher(
                "/test_regist.jsp")
           .forward(req, res);
    }
}