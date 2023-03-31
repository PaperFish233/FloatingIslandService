package com.example.servlet;

import com.alibaba.fastjson.JSON;
import com.example.dao.UsersFocusDao;
import com.example.dao.impl.UsersFocusDaoImpl;
import com.example.utils.TMessage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UsersFocusServlet", value = "/UsersFocusServlet")
public class UsersFocusServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;utf-8");

        int pid = Integer.parseInt(req.getParameter("pid"));
        PrintWriter out = resp.getWriter();
        TMessage<Integer> tMessage = new TMessage();
        UsersFocusDao usersfocusDao = new UsersFocusDaoImpl();

        String action = req.getParameter("action");
        switch (action) {
            case "Focus":
                int i = usersfocusDao.selectData(pid);
                usersfocusDao.insertData(pid);
                tMessage.setCode(200);
                tMessage.setData(i);
                tMessage.setMessage("关注成功");
                out.print(JSON.toJSON(tMessage));
                break;
            case "SelectFocus":
                int j = usersfocusDao.selectData(pid);
                if(j==1){
                    tMessage.setCode(200);
                    tMessage.setData(j);
                    tMessage.setMessage("已关注");
                    out.print(JSON.toJSON(tMessage));
                }else{
                    tMessage.setCode(200);
                    tMessage.setData(j);
                    tMessage.setMessage("未关注");
                    out.print(JSON.toJSON(tMessage));
                }
                break;
        }

    }
}
