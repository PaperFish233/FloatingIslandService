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
                int tid = Integer.parseInt(req.getParameter("tid"));
                String uaccount3 = req.getParameter("uaccount");
                String pconnect = req.getParameter("pconnect");
                String pimageurl = req.getParameter("pimageurl");
                int i =postsDao.insertData(tid,uaccount3,pconnect,pimageurl);
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
            case "GetMineCollectionUserPosts":
                String uaccount2 = req.getParameter("uaccount");
                List<Posts> minecollectionuserpostsdata = postsDao.getMineCollectionUserPostsData(uaccount2);
                tMessage.setCode(200);
                tMessage.setData(minecollectionuserpostsdata);
                tMessage.setMessage("获取成功");
                out.print(JSON.toJSON(tMessage));
                break;
            case "GetSearchPosts":
                String keyword = req.getParameter("keyword");
                List<Posts> searchpostsdata = postsDao.getSearchPostsData(keyword);
                tMessage.setCode(200);
                tMessage.setData(searchpostsdata);
                tMessage.setMessage("获取成功");
                out.print(JSON.toJSON(tMessage));
                break;
            case "GetUserPosts":
                int pid = Integer.parseInt(req.getParameter("pid"));
                List<Posts> userdata = postsDao.getUserPostsData(pid);
                tMessage.setCode(200);
                tMessage.setData(userdata);
                tMessage.setMessage("获取成功");
                out.print(JSON.toJSON(tMessage));
                break;
            case "UpdatePosts":
                int pid4 = Integer.parseInt(req.getParameter("pid"));
                String uaccount4 = req.getParameter("uaccount");
                String pconnect4 = req.getParameter("pconnect");
                String pimageurl4 = req.getParameter("pimageurl");
                int i4 = postsDao.updateData(pid4,uaccount4,pconnect4,pimageurl4);
                tMessage.setCode(200);
                if(i4==1){
                    tMessage.setMessage("更新成功");
                }else{
                    tMessage.setMessage("更新失败");
                }
                out.print(JSON.toJSON(tMessage));
                break;
            case "DeletePosts":
                int pid5 = Integer.parseInt(req.getParameter("pid"));
                String uaccount5 = req.getParameter("uaccount");
                int i5 =postsDao.deleteData(pid5,uaccount5);
                tMessage.setCode(200);
                if(i5==1){
                    tMessage.setMessage("删除成功");
                }else{
                    tMessage.setMessage("删除失败");
                }
                out.print(JSON.toJSON(tMessage));
                break;
            case "GetTidPosts":
                int tid1 = Integer.parseInt(req.getParameter("tid"));
                List<Posts> Tiddata = postsDao.getTidPostsData(tid1);
                tMessage.setCode(200);
                tMessage.setData(Tiddata);
                tMessage.setMessage("获取成功");
                out.print(JSON.toJSON(tMessage));
                break;
            case "GetRankingPosts":
                List<Posts> Rankingdata = postsDao.getRankingData();
                tMessage.setCode(200);
                tMessage.setData(Rankingdata);
                tMessage.setMessage("获取成功");
                out.print(JSON.toJSON(tMessage));
                break;
            case "GetUserPostsByuaccount":
                String uaccount6 = req.getParameter("uaccount");
                List<Posts> userbyuaccountdata = postsDao.getUserPostsByuaccountData(uaccount6);
                tMessage.setCode(200);
                tMessage.setData(userbyuaccountdata);
                tMessage.setMessage("获取成功");
                out.print(JSON.toJSON(tMessage));
                break;
            case "GetRankingCollectionPosts":
                List<Posts> RankingCollectiondata = postsDao.getRankingCollectionData();
                tMessage.setCode(200);
                tMessage.setData(RankingCollectiondata);
                tMessage.setMessage("获取成功");
                out.print(JSON.toJSON(tMessage));
                break;
            case "GetRankingCommentPosts":
                List<Posts> RankingCommentdata = postsDao.getRankingCommentData();
                tMessage.setCode(200);
                tMessage.setData(RankingCommentdata);
                tMessage.setMessage("获取成功");
                out.print(JSON.toJSON(tMessage));
                break;
        }

    }
}
