package servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDAO;
import tool.Action;

public class LoginExecuteAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
  
        String id = req.getParameter("id");
        String password = req.getParameter("password");

        TeacherDAO dao = new TeacherDAO();

        Teacher teacher =
            dao.login(id, password);

        if (teacher != null) {

            HttpSession session =
                req.getSession();

            session.setAttribute(
                "user",
                teacher);

            req.getRequestDispatcher(
                "/menu.jsp")
               .forward(req, res);

        } else {

            req.setAttribute(
                "error",
                "ユーザーIDまたはパスワードが間違っています。");

            req.getRequestDispatcher(
                "/login.jsp")
               .forward(req, res);
        }
    }
}
