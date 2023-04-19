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
        }

    }
}
