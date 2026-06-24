package servlet;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Test;
import dao.ClassNumDAO;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestDAO;
import tool.Action;

public class TestDeleteAction implements Action {

    @Override
    public void execute(
            HttpServletRequest req,
            HttpServletResponse res)
            throws Exception {

        StudentDAO studentDao =
                new StudentDAO();

        SubjectDAO subjectDao =
                new SubjectDAO();

        ClassNumDAO classDao =
                new ClassNumDAO();

        List<Integer> entYearList =
                studentDao.findAll()
                          .stream()
                          .map(s -> s.getEntYear())
                          .distinct()
                          .sorted()
                          .toList();

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
                    "/test_delete.jsp")
               .forward(req, res);

            return;
        }

        String entYearStr =
                req.getParameter("ent_year");

        String classNum =
                req.getParameter("class_num");

        String subjectNo =
                req.getParameter("subject_cd");

        String noStr =
                req.getParameter("no");

        if (entYearStr.isEmpty()
                || classNum.isEmpty()
                || subjectNo.isEmpty()
                || noStr.isEmpty()) {

            req.setAttribute(
                    "error",
                    "すべて選択してください");

            req.getRequestDispatcher(
                    "/test_delete.jsp")
               .forward(req, res);

            return;
        }

        int entYear =
                Integer.parseInt(entYearStr);

        int no =
                Integer.parseInt(noStr);

        TestDAO dao =
                new TestDAO();

        List<Test> testList =
                dao.findForUpdate(
                        entYear,
                        classNum,
                        subjectNo,
                        no);

        req.setAttribute(
                "testList",
                testList);

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
                "/test_delete.jsp")
           .forward(req, res);
    }
}