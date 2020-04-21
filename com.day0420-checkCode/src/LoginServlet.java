import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String checkcode = req.getParameter("checkcode");
        HttpSession session=req.getSession();
        Object checkcodeFromSession = session.getAttribute("checkcode");
        if(checkcodeFromSession.toString().equalsIgnoreCase(checkcode)){
            if("zhangsan".equals(username)&&"123456".equals(password))
                req.getRequestDispatcher("/sucess").forward(req,resp);
            else {
                req.setAttribute("passwordError","密码错误，请重新输入");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
        }
        else {
            if("".equals(checkcode))
                req.setAttribute("checkcodeError","验证码不能为空");
            else
                req.setAttribute("checkcodeError","验证码错误，请重新输入");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
