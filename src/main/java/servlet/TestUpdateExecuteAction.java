package servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Subject;
import bean.Test;
import dao.TestDAO;
import tool.Action;

public class TestUpdateExecuteAction
        implements Action {

    @Override
    public void execute(
            HttpServletRequest req,
            HttpServletResponse res)
            throws Exception {

        String subjectCd =
                req.getParameter(
                        "subject_cd");

        int no =
                Integer.parseInt(
                        req.getParameter(
                                "no"));

        String[] studentNos =
                req.getParameterValues(
                        "student_no");

        String[] points =
                req.getParameterValues(
                        "point");

        TestDAO dao =
                new TestDAO();

        for (int i = 0;
             i < studentNos.length;
             i++) {

            Test test =
                    new Test();

            Student student =
                    new Student();

            student.setNo(
                    studentNos[i]);

            Subject subject =
                    new Subject();

            subject.setCd(
                    subjectCd);

            test.setStudent(
                    student);

            test.setSubject(
                    subject);

            test.setNo(no);

            test.setPoint(
                    Integer.parseInt(
                            points[i]));

            dao.update(test);
        }

        req.setAttribute(
                "message",
                "成績を変更しました");

        req.getRequestDispatcher(
                "/TestUpdate.action")
           .forward(req, res);
    }
}