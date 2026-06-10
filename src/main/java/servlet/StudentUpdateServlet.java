package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Student;
import dao.ClassNumDAO;
import dao.StudentDAO;

@WebServlet("/StudentUpdateServlet")
public class StudentUpdateServlet extends HttpServlet {

@Override
protected void doGet(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {

    try {

        String no = request.getParameter("no");

        StudentDAO dao = new StudentDAO();
        Student student = dao.findByNo(no);

        List<Integer> entYearList = new ArrayList<>();

        int currentYear =
                java.time.Year.now().getValue();

        for (int i = currentYear; i >= currentYear - 10; i--) {
            entYearList.add(i);
        }

        ClassNumDAO classNumDAO =
                new ClassNumDAO();

        List<String> classNumList =
                classNumDAO.findAll();

        request.setAttribute(
                "student",
                student);

        request.setAttribute(
                "ent_year_list",
                entYearList);

        request.setAttribute(
                "class_num_list",
                classNumList);

        request.getRequestDispatcher(
                "/student_update.jsp")
                .forward(request, response);

    } catch (Exception e) {

        throw new ServletException(e);

    }
}

@Override
protected void doPost(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");

    try {

        String no =
                request.getParameter("no");

        String name =
                request.getParameter("name");

        String entYearStr =
                request.getParameter("ent_year");

        String classNum =
                request.getParameter("class_num");

        boolean attend =
                request.getParameter("attend")
                != null;

        String error = null;

        if (no == null || no.trim().isEmpty()) {

            error = "学生番号を入力してください";

        } else if (no.length() > 10) {

            error = "学生番号は10文字以内で入力してください";

        } else if (name == null
                || name.trim().isEmpty()) {

            error = "氏名を入力してください";

        } else if (name.length() > 30) {

            error = "氏名は30文字以内で入力してください";

        } else if (entYearStr == null
                || entYearStr.isEmpty()) {

            error = "入学年度を選択してください";
        }

        int entYear = 0;

        if (error == null) {
            entYear =
                    Integer.parseInt(entYearStr);
        }

        if (error != null) {

            Student student =
                    new Student();

            student.setNo(no);
            student.setName(name);
            student.setClassNum(classNum);
            student.setAttend(attend);

            if (entYearStr != null
                    && !entYearStr.isEmpty()) {

                student.setEntYear(
                        Integer.parseInt(
                                entYearStr));
            }

            List<Integer> entYearList =
                    new ArrayList<>();

            int currentYear =
                    java.time.Year.now()
                    .getValue();

            for (int i = currentYear;
                    i >= currentYear - 10;
                    i--) {

                entYearList.add(i);
            }

            ClassNumDAO classNumDAO =
                    new ClassNumDAO();

            List<String> classNumList =
                    classNumDAO.findAll();

            request.setAttribute(
                    "error",
                    error);

            request.setAttribute(
                    "student",
                    student);

            request.setAttribute(
                    "ent_year_list",
                    entYearList);

            request.setAttribute(
                    "class_num_list",
                    classNumList);

            request.getRequestDispatcher(
                    "/student_update.jsp")
                    .forward(
                            request,
                            response);

            return;
        }

        Student student =
                new Student();

        student.setNo(no);
        student.setName(name);
        student.setEntYear(entYear);
        student.setClassNum(classNum);
        student.setAttend(attend);

        StudentDAO dao =
                new StudentDAO();

        dao.update(student);

        response.sendRedirect(
                "StudentListServlet");

    } catch (Exception e) {

        throw new ServletException(e);

    }
}


}
