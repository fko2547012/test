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
import dao.ClassNumDAO;
import dao.StudentDAO;

@WebServlet("/StudentCreateServlet")
public class StudentCreateServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int currentYear =
                    Year.now().getValue();

            List<Integer> entYearList =
                    new ArrayList<>();

            for (int i = currentYear - 10;
                 i <= currentYear + 10;
                 i++) {

                entYearList.add(i);
            }

            ClassNumDAO classDao =
                    new ClassNumDAO();

            List<String> classNumList =
                    classDao.findAll();

            request.setAttribute(
                    "ent_year_list",
                    entYearList);

            request.setAttribute(
                    "class_num_list",
                    classNumList);

            request.getRequestDispatcher(
                    "student_create.jsp")
                   .forward(
                           request,
                           response);

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

            String no =
                    request.getParameter("no");

            String name =
                    request.getParameter("name");

            String entYearStr =
                    request.getParameter(
                            "ent_year");

            String classNum =
                    request.getParameter(
                            "class_num");

            String error = null;

            // 学生番号チェック
            if (no == null ||
                no.trim().isEmpty()) {

                error = "学生番号は必須です";

            } else if (no.length() > 10) {

                error =
                    "学生番号は10文字以内で入力してください";
            }

            // 氏名チェック
            else if (name == null ||
                     name.trim().isEmpty()) {

                error = "氏名は必須です";

            } else if (name.length() > 30) {

                error =
                    "氏名は30文字以内で入力してください";
            }

            // 入学年度チェック
            else if (entYearStr == null ||
                     entYearStr.isEmpty()) {

                error =
                    "入学年度を選択してください";
            }

            if (error != null) {

                request.setAttribute(
                        "error",
                        error);

                doGet(
                        request,
                        response);

                return;
            }

            int entYear =
                    Integer.parseInt(
                            entYearStr);

            Student student =
                    new Student();

            student.setNo(no);
            student.setName(name);
            student.setEntYear(entYear);
            student.setClassNum(classNum);
            student.setAttend(true);

            StudentDAO dao =
                    new StudentDAO();

            boolean result =
                    dao.insert(student);

            if (result) {

                request.getRequestDispatcher(
                        "student_create_done.jsp")
                       .forward(
                               request,
                               response);

            } else {

                request.setAttribute(
                        "error",
                        "登録に失敗しました");

                doGet(
                        request,
                        response);
            }

        } catch (Exception e) {

            throw new ServletException(e);
        }
    }
}