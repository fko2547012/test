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

import bean.Test;
import dao.ClassNumDAO;
import dao.SubjectDAO;
import dao.TestDAO;

@WebServlet("/TestRegistServlet")
public class TestRegistServlet extends HttpServlet {

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

            request.setAttribute(
                    "entYearList",
                    entYearList);

            request.setAttribute(
                    "classNumList",
                    new ClassNumDAO().findAll());

            request.setAttribute(
                    "subjectList",
                    new SubjectDAO().findAll());

            request.getRequestDispatcher(
                    "/test_regist.jsp")
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

            int entYear =
                    Integer.parseInt(
                            request.getParameter(
                                    "ent_year"));

            String classNum =
                    request.getParameter(
                            "class_num");

            String subjectCd =
                    request.getParameter(
                            "subject_cd");

            int no =
                    Integer.parseInt(
                            request.getParameter(
                                    "no"));

            List<Integer> entYearList =
                    new ArrayList<>();

            int currentYear =
                    Year.now().getValue();

            for (int i = currentYear;
                    i >= currentYear - 10;
                    i--) {

                entYearList.add(i);
            }

            request.setAttribute(
                    "entYearList",
                    entYearList);

            request.setAttribute(
                    "classNumList",
                    new ClassNumDAO().findAll());

            request.setAttribute(
                    "subjectList",
                    new SubjectDAO().findAll());

            TestDAO dao =
                    new TestDAO();

            List<Test> testList =
                    dao.findForRegist(
                            entYear,
                            classNum,
                            subjectCd,
                            no);

            request.setAttribute(
                    "testList",
                    testList);

            request.setAttribute(
                    "selectedYear",
                    entYear);

            request.setAttribute(
                    "selectedClass",
                    classNum);

            request.setAttribute(
                    "selectedSubject",
                    subjectCd);

            request.setAttribute(
                    "selectedNo",
                    no);

            request.setAttribute(
                    "isSearched",
                    true);

            request.getRequestDispatcher(
                    "/test_regist.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            throw new ServletException(e);
        }
    }
}
