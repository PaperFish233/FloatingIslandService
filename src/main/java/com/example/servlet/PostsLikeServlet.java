package com.example.servlet;

import com.alibaba.fastjson.JSON;
import com.example.dao.PostsLikeDao;
import com.example.dao.impl.PostsLikeDaoImpl;
import com.example.utils.TMessage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PostsLikeServlet", value = "/PostsLikeServlet")
public class PostsLikeServlet extends HttpServlet {
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
        String uaccount = req.getParameter("uaccount");
        PrintWriter out = resp.getWriter();
        TMessage<Integer> tMessage = new TMessage();
        PostsLikeDao postsLikeDao = new PostsLikeDaoImpl();

        String action = req.getParameter("action");
        switch (action) {
            case "Like":
                int i = postsLikeDao.selectData(pid,uaccount);
                if(i==1){
                    postsLikeDao.deleteData(pid,uaccount);
                    tMessage.setCode(200);
                    tMessage.setData(2);
                    tMessage.setMessage("取消点赞成功");
                    out.print(JSON.toJSON(tMessage));
                }else{
                    postsLikeDao.insertData(pid,uaccount);
                    tMessage.setCode(200);
                    tMessage.setData(1);
                    tMessage.setMessage("点赞成功");
                    out.print(JSON.toJSON(tMessage));
                }
                break;
            case "SelectLike":
                int j = postsLikeDao.selectData(pid,uaccount);
                if(j==1){
                    tMessage.setCode(200);
                    tMessage.setData(j);
                    tMessage.setMessage("已点赞");
                    out.print(JSON.toJSON(tMessage));
                }else{
                    tMessage.setCode(200);
                    tMessage.setData(j);
                    tMessage.setMessage("未点赞");
                    out.print(JSON.toJSON(tMessage));
                }
                break;
        }

    }
}
