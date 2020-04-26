package com;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/servletDemo2")
public class ServletDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        List<User> userList=new ArrayList<User>();
        User zhangsan=new User();
        zhangsan.setName("张三");
        zhangsan.setGender("男");
        zhangsan.setAge(40);
        User lisi=new User();
        lisi.setAge(20);
        lisi.setGender("女");
        lisi.setName("李四");
        userList.add(zhangsan);
        userList.add(lisi);
        req.getSession().setAttribute("userList",userList);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
