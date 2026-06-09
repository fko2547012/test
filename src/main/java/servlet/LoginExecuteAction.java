package servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;
import bean.Teacher;

public class LoginExecuteAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
  
        String id = req.getParameter("id");
        String password = req.getParameter("password");

        if ("admin".equals(id) && "password".equals(password)) {
            
            Teacher teacher = new Teacher();
            teacher.setId(id);
            teacher.setName("大原太郎");
            teacher.setAuthenticated(true);
            
            HttpSession session = req.getSession(true);
            session.setAttribute("user", teacher);

    
            req.getRequestDispatcher("/menu.jsp").forward(req, res);
            
        } else {
            req.setAttribute("error", "ユーザーIDまたはパスワードが間違っています。");
            req.getRequestDispatcher("/login.jsp").forward(req, res);
        }
    }
}
