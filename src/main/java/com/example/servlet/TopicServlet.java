package com.example.servlet;

import com.alibaba.fastjson.JSON;
import com.example.dao.TopicDao;
import com.example.dao.impl.TopicDaoImpl;
import com.example.entity.Topic;
import com.example.utils.TMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "TopicServlet", value = "/TopicServlet")
public class TopicServlet extends HttpServlet {
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
        TMessage<List<Topic>> tMessage = new TMessage();
        TopicDao topicDao = new TopicDaoImpl();

        String action = req.getParameter("action");
        switch (action) {
            case "GetTopic":
                List<Topic> data = topicDao.getData();
                tMessage.setCode(200);
                tMessage.setData(data);
                tMessage.setMessage("获取成功");
                out.print(JSON.toJSON(tMessage));
                break;
            case "GetTidTopic":
                int tid = Integer.parseInt(req.getParameter("tid"));
                List<Topic> data1 = topicDao.getTidData(tid);
                tMessage.setCode(200);
                tMessage.setData(data1);
                tMessage.setMessage("获取成功");
                out.print(JSON.toJSON(tMessage));
                break;
            case "InsertTopic":
                String tname = req.getParameter("tname");
                String timageurl = req.getParameter("timageurl");
                String tsignature = req.getParameter("tsignature");
                int i =topicDao.insertData(tname,timageurl,tsignature);
                tMessage.setCode(200);
                if(i==1){
                    tMessage.setMessage("发布成功");
                }else{
                    tMessage.setMessage("发布失败");
                }
                out.print(JSON.toJSON(tMessage));
                break;
            case "DeleteTopic":
                int deleteid = Integer.parseInt(req.getParameter("deleteid"));
                int i2 =topicDao.deleteData(deleteid);
                tMessage.setCode(200);
                if(i2==1){
                    tMessage.setMessage("删除成功");
                }else{
                    tMessage.setMessage("删除失败");
                }
                out.print(JSON.toJSON(tMessage));
                break;
            case "UpdateTopic":
                int updateid = Integer.parseInt(req.getParameter("updateid"));
                String tname1 = req.getParameter("tname1");
                String timageurl1 = req.getParameter("timageurl1");
                String tsignature1 = req.getParameter("tsignature1");
                int i3 =topicDao.updateData(updateid,tname1,timageurl1,tsignature1);
                tMessage.setCode(200);
                if(i3==1){
                    tMessage.setMessage("更新成功");
                }else{
                    tMessage.setMessage("更新失败");
                }
                out.print(JSON.toJSON(tMessage));
                break;
            case "GetNumTopic":
                List<Topic> numdata = topicDao.getNumData();
                tMessage.setCode(200);
                tMessage.setData(numdata);
                tMessage.setMessage("获取成功");
                out.print(JSON.toJSON(tMessage));
                break;
        }

    }
}
