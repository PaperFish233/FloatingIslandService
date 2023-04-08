package com.example.servlet;

import com.alibaba.fastjson.JSON;
import com.example.dao.PostsCommentDao;
import com.example.dao.impl.PostsCommentDaoImpl;
import com.example.entity.Posts;
import com.example.entity.PostsComment;
import com.example.utils.TMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PostsCommentServlet", value = "/PostsCommentServlet")
public class PostsCommentServlet extends HttpServlet {
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
        TMessage<List<PostsComment>> tMessage = new TMessage();
        PostsCommentDao postscommentDao = new PostsCommentDaoImpl();

        String action = req.getParameter("action");
        switch (action) {
            case "GetPostsComment":
                int pid = Integer.parseInt(req.getParameter("pid"));
                List<PostsComment> data = postscommentDao.getData(pid);
                tMessage.setCode(200);
                tMessage.setData(data);
                tMessage.setMessage("获取成功");
                out.print(JSON.toJSON(tMessage));
                break;
        }
    }
}
