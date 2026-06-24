package servlet;

import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Student;
import bean.StudentResult;
import bean.SubjectResult;
import dao.ClassNumDAO;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestListDAO;

@WebServlet("/TestListServlet")
public class TestListServlet extends HttpServlet {

@Override
protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {

    try {

        List<Integer> entYearList =
                new ArrayList<>();

        int currentYear =
                Year.now().getValue();

        for (int i = currentYear;
                i >= currentYear - 10;
                i--) {

            entYearList.add(i);
        }

        ClassNumDAO classNumDAO =
                new ClassNumDAO();

        SubjectDAO subjectDAO =
                new SubjectDAO();

        request.setAttribute(
                "entYearList",
                entYearList);

        request.setAttribute(
                "classNumList",
                classNumDAO.findAll());

        request.setAttribute(
                "subjectList",
                subjectDAO.findAll());

        request.getRequestDispatcher(
                "/test_list.jsp")
                .forward(request, response);

    } catch (Exception e) {

        throw new ServletException(e);
    }
}

@Override
protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {

    try {

        request.setCharacterEncoding("UTF-8");

        String f =
                request.getParameter("f");

        TestListDAO dao =
                new TestListDAO();

        List<Integer> entYearList =
                new ArrayList<>();

        int currentYear =
                Year.now().getValue();

        for (int i = currentYear;
                i >= currentYear - 10;
                i--) {

            entYearList.add(i);
        }

        ClassNumDAO classNumDAO =
                new ClassNumDAO();

        SubjectDAO subjectDAO =
                new SubjectDAO();

        request.setAttribute(
                "entYearList",
                entYearList);

        request.setAttribute(
                "classNumList",
                classNumDAO.findAll());

        request.setAttribute(
                "subjectList",
                subjectDAO.findAll());

        // 科目検索
        if ("sj".equals(f)) {

            String entYearStr =
                    request.getParameter("f1");

            String classNum =
                    request.getParameter("f2");

            String subjectCd =
                    request.getParameter("f3");

            if (entYearStr == null || entYearStr.isEmpty()
                    || classNum == null || classNum.isEmpty()
                    || subjectCd == null || subjectCd.isEmpty()) {

                request.setAttribute(
                        "error",
                        "入学年度・クラス・科目をすべて選択してください");

            } else {

                int entYear =
                        Integer.parseInt(entYearStr);

                List<SubjectResult> list =
                        dao.findBySubject(
                                entYear,
                                classNum,
                                subjectCd);

                if (list.isEmpty()) {

                    request.setAttribute(
                            "subjectError",
                            "学生情報が存在しませんでした");

                } else {

                    request.setAttribute(
                            "subjectResults",
                            list);
                }

                request.setAttribute(
                        "f1",
                        entYear);

                request.setAttribute(
                        "f2",
                        classNum);

                request.setAttribute(
                        "f3",
                        subjectCd);
            }
        }

        // 学生検索
        else if ("st".equals(f)) {

            String studentNo =
                    request.getParameter("f4");
            
            StudentDAO studentDAO =
                    new StudentDAO();

            Student student =
                    studentDAO.findByNo(studentNo);

            if (student == null) {

                request.setAttribute(
                        "studentError",
                        "学生情報が存在しませんでした");

            } else {

                List<StudentResult> list =
                        dao.findByStudent(studentNo);

                if (list.isEmpty()) {

                    request.setAttribute(
                            "studentError",
                            "成績情報が存在しませんでした");

                } else {

                    request.setAttribute(
                            "studentResults",
                            list);

                    request.setAttribute(
                            "student",
                            student);
                }
            }
            
            request.setAttribute(
                    "f4",
                    studentNo);
        }

        request.getRequestDispatcher(
                "/test_list.jsp")
                .forward(request, response);

    } catch (Exception e) {

        throw new ServletException(e);
    }
}

}
