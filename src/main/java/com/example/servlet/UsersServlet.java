package com.example.servlet;

import com.alibaba.fastjson.JSON;
import com.example.dao.UsersDao;
import com.example.dao.impl.UsersDaoImpl;
import com.example.entity.Users;
import com.example.utils.TMessage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "UsersServlet", value = "/UsersServlet")
public class UsersServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;utf-8");

        PrintWriter out = resp.getWriter();
        TMessage<List<Users>> tMessage = new TMessage();
        UsersDao usersDao = new UsersDaoImpl();
        String action = req.getParameter("action");
        switch (action) {
            case "GetUsers":
                String uaccount = req.getParameter("uaccount");
                List<Users> data = usersDao.getData(uaccount);
                tMessage.setCode(200);
                tMessage.setData(data);
                tMessage.setMessage("获取成功");
                out.print(JSON.toJSON(tMessage));
                break;
            case "Login":
                String uaccount1 = req.getParameter("uaccount");
                String upassword1 = req.getParameter("upassword");
                int i = usersDao.logininfo(uaccount1,upassword1);
                tMessage.setCode(200);
                if(i==1){
                    tMessage.setMessage("登录成功");
                }else{
                    tMessage.setMessage("登录失败");
                }
                out.print(JSON.toJSON(tMessage));
                break;
            case "Register":
                String uavatarurl2 = req.getParameter("uavatarurl");
                String uaccount2 = req.getParameter("uaccount");
                String upassword2 = req.getParameter("upassword");
                String unickname2 = req.getParameter("unickname");
                tMessage.setCode(200);
                int i2 = usersDao.selectuser(uaccount2);
                if(i2==1){
                    tMessage.setMessage("账号已存在");
                    out.print(JSON.toJSON(tMessage));
                    break;
                }else{
                    int i3 = usersDao.register(uavatarurl2,uaccount2,upassword2,unickname2);
                    if(i3==1){
                        tMessage.setMessage("注册成功");
                    }else{
                        tMessage.setMessage("注册失败");
                    }
                    out.print(JSON.toJSON(tMessage));
                }
                break;
        }

    }
}
