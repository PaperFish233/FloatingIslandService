package com.example.servlet;

import com.alibaba.fastjson.JSON;
import com.example.dao.PostsDao;
import com.example.dao.impl.PostsDaoImpl;
import com.example.entity.Posts;
import com.example.utils.TMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/PostsServlet", name = "PostsServlet")
public class PostsServlet extends HttpServlet {
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
        TMessage<List<Posts>> tMessage = new TMessage();
        PostsDao postsDao = new PostsDaoImpl();

        String action = req.getParameter("action");
        switch (action) {
            case "GetPosts":
                List<Posts> data = postsDao.getData();
                tMessage.setCode(200);
                tMessage.setData(data);
                tMessage.setMessage("获取成功");
                out.print(JSON.toJSON(tMessage));
                break;
            case "InsertPosts":
                String pconnect = req.getParameter("pconnect");
                String pimageurl = req.getParameter("pimageurl");
                int i =postsDao.insertData(pconnect,pimageurl);
                tMessage.setCode(200);
                if(i==1){
                    tMessage.setMessage("发布成功");
                }else{
                    tMessage.setMessage("发布失败");
                }
                out.print(JSON.toJSON(tMessage));
                break;
            case "GetMinePosts":
                String uaccount = req.getParameter("uaccount");
                List<Posts> minedata = postsDao.getMineData(uaccount);
                tMessage.setCode(200);
                tMessage.setData(minedata);
                tMessage.setMessage("获取成功");
                out.print(JSON.toJSON(tMessage));
                break;
            case "GetMineCollectionPosts":
                String uaccount1 = req.getParameter("uaccount");
                List<Posts> collectiondata = postsDao.getMineCollectionData(uaccount1);
                tMessage.setCode(200);
                tMessage.setData(collectiondata);
                tMessage.setMessage("获取成功");
                out.print(JSON.toJSON(tMessage));
                break;
        }

    }
}
