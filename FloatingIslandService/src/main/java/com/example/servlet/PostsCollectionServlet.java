package com.example.servlet;

import com.alibaba.fastjson.JSON;
import com.example.dao.PostsCollectionDao;
import com.example.dao.impl.PostsCollectionDaoImpl;
import com.example.utils.TMessage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PostsCollectionServlet", value = "/PostsCollectionServlet")
public class PostsCollectionServlet extends HttpServlet {
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
        PostsCollectionDao postscollectionDao = new PostsCollectionDaoImpl();

        String action = req.getParameter("action");
        switch (action) {
            case "Collection":
                int i = postscollectionDao.selectData(pid);
                if(i==1){
                    postscollectionDao.deleteData(pid);
                    tMessage.setCode(200);
                    tMessage.setData(2);
                    tMessage.setMessage("取消收藏成功");
                    out.print(JSON.toJSON(tMessage));
                }else{
                    postscollectionDao.insertData(pid);
                    tMessage.setCode(200);
                    tMessage.setData(1);
                    tMessage.setMessage("收藏成功");
                    out.print(JSON.toJSON(tMessage));
                }
                break;
            case "SelectCollection":
                int j = postscollectionDao.selectData(pid);
                if(j==1){
                    tMessage.setCode(200);
                    tMessage.setData(j);
                    tMessage.setMessage("已收藏");
                    out.print(JSON.toJSON(tMessage));
                }else{
                    tMessage.setCode(200);
                    tMessage.setData(j);
                    tMessage.setMessage("未收藏");
                    out.print(JSON.toJSON(tMessage));
                }
                break;
        }

    }
}
