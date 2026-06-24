package servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.TestDAO;
import tool.Action;

public class TestRegistExecuteAction implements Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        req.setCharacterEncoding("UTF-8");

        String classNum = req.getParameter("class_num");
        String subjectCd = req.getParameter("subject_cd");
        int no = Integer.parseInt(req.getParameter("no"));

        String[] studentNos = req.getParameterValues("student_no");
        String[] points = req.getParameterValues("point");

        HttpSession session = req.getSession();
        Teacher teacher =
        	(Teacher) session.getAttribute("user");

        School school =
            teacher.getSchool();

        TestDAO dao = new TestDAO();

        for (int i = 0; i < studentNos.length; i++) {

            if (points[i] == null || points[i].isEmpty()) {
                continue;
            }

            Student student = new Student();
            student.setNo(studentNos[i]);

            Subject subject = new Subject();
            subject.setCd(subjectCd);

            Test test = new Test();
            test.setStudent(student);
            test.setSubject(subject);
            test.setSchool(school);
            test.setClassNum(classNum);
            test.setNo(no);
            test.setPoint(Integer.parseInt(points[i]));

            dao.save(test);
        }

        req.setAttribute("message", "成績を登録しました。");

        req.getRequestDispatcher("/test_regist.jsp")
           .forward(req, res);
    }
}
