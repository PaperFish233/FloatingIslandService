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
        String uaccount = req.getParameter("uaccount");
        String action = req.getParameter("action");
        switch (action) {
            case "GetUsers":
                List<Users> data = usersDao.getData(uaccount);
                tMessage.setCode(200);
                tMessage.setData(data);
                tMessage.setMessage("获取成功");
                out.print(JSON.toJSON(tMessage));
                break;
        }

    }
}
