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

        PrintWriter out = resp.getWriter();
        TMessage<Integer> tMessage = new TMessage();
        UsersFocusDao usersfocusDao = new UsersFocusDaoImpl();

        String action = req.getParameter("action");
        switch (action) {
            case "Focus":
                int pid = Integer.parseInt(req.getParameter("pid"));
                String uaccount = req.getParameter("uaccount");
                int i = usersfocusDao.selectData(pid,uaccount);
                if(i==1) {
                    usersfocusDao.deleteData(pid,uaccount);
                    tMessage.setCode(200);
                    tMessage.setData(2);
                    tMessage.setMessage("取消关注成功");
                    out.print(JSON.toJSON(tMessage));
                }else{
                    usersfocusDao.insertData(pid,uaccount);
                    tMessage.setCode(200);
                    tMessage.setData(1);
                    tMessage.setMessage("关注成功");
                    out.print(JSON.toJSON(tMessage));
                }
                break;
            case "SelectFocus":
                int pid1 = Integer.parseInt(req.getParameter("pid"));
                String uaccount1 = req.getParameter("uaccount");
                int j = usersfocusDao.selectData(pid1,uaccount1);
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
            case "SelectFocusByuaccount":
                String uaccount2 = req.getParameter("uaccount");
                String faccount = req.getParameter("faccount");
                int k = usersfocusDao.selectByuaccountData(faccount,uaccount2);
                if(k==1){
                    tMessage.setCode(200);
                    tMessage.setData(k);
                    tMessage.setMessage("已关注");
                    out.print(JSON.toJSON(tMessage));
                }else{
                    tMessage.setCode(200);
                    tMessage.setData(k);
                    tMessage.setMessage("未关注");
                    out.print(JSON.toJSON(tMessage));
                }
                break;
            case "FocusByuaccount":
                String ufccount3 = req.getParameter("faccount");
                String uaccount3 = req.getParameter("uaccount");
                int i1 = usersfocusDao.selectByuaccountData(ufccount3,uaccount3);
                if(i1==1) {
                    usersfocusDao.deleteByuaccountData(ufccount3,uaccount3);
                    tMessage.setCode(200);
                    tMessage.setData(2);
                    tMessage.setMessage("取消关注成功");
                    out.print(JSON.toJSON(tMessage));
                }else{
                    usersfocusDao.insertByuaccountData(ufccount3,uaccount3);
                    tMessage.setCode(200);
                    tMessage.setData(1);
                    tMessage.setMessage("关注成功");
                    out.print(JSON.toJSON(tMessage));
                }
                break;
        }

    }
}
