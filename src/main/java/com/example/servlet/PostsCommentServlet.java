package com.example.servlet;

import com.alibaba.fastjson.JSON;
import com.example.dao.PostsCommentDao;
import com.example.dao.impl.PostsCommentDaoImpl;
import com.example.entity.PostsComment;
import com.example.utils.TMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
            case "InsertPostsComment":
                int pid1 = Integer.parseInt(req.getParameter("pid"));
                String account = req.getParameter("uaccount");
                String comment = req.getParameter("comment");
                int i = postscommentDao.insertData(pid1,account,comment);
                tMessage.setCode(200);
                tMessage.setMessage("发布成功");
                out.print(JSON.toJSON(tMessage));
                break;
            case "SelectComment":
                int pid2 = Integer.parseInt(req.getParameter("pid"));
                String faccount2 = req.getParameter("faccount");
                String uaccount2 = req.getParameter("uaccount");
                int j = postscommentDao.selectData(pid2,faccount2,uaccount2);
                if(j==1){
                    tMessage.setCode(200);
                    tMessage.setMessage("该账号发布");
                    out.print(JSON.toJSON(tMessage));
                }else{
                    tMessage.setCode(200);
                    tMessage.setMessage("非该账号发布");
                    out.print(JSON.toJSON(tMessage));
                }
                break;
            case "DeleteComment":
                int cid = Integer.parseInt(req.getParameter("cid"));
                String uaccount3 = req.getParameter("uaccount");
                int i3 = postscommentDao.deleteData(cid,uaccount3);
                tMessage.setCode(200);
                tMessage.setMessage("删除成功");
                out.print(JSON.toJSON(tMessage));
                break;
            case "DeletePostsCommentAdmin":
                int deleteid = Integer.parseInt(req.getParameter("deleteid"));
                int i4 =postscommentDao.deleteDataAdmin(deleteid);
                tMessage.setCode(200);
                if(i4==1){
                    tMessage.setMessage("删除成功");
                }else{
                    tMessage.setMessage("删除失败");
                }
                out.print(JSON.toJSON(tMessage));
                break;
        }
    }
}
