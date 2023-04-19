package com.example.servlet;

import com.alibaba.fastjson.JSON;
import com.example.dao.NoticeDao;
import com.example.dao.TopicDao;
import com.example.dao.impl.NoticeDaoImpl;
import com.example.dao.impl.TopicDaoImpl;
import com.example.entity.Notice;
import com.example.entity.Topic;
import com.example.utils.TMessage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "NoticeServlet", value = "/NoticeServlet")
public class NoticeServlet extends HttpServlet {
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
        TMessage<List<Notice>> tMessage = new TMessage();
        NoticeDao noticeDao = new NoticeDaoImpl();

        String action = req.getParameter("action");
        switch (action) {
            case "GetNotice":
                List<Notice> data = noticeDao.getData();
                tMessage.setCode(200);
                tMessage.setData(data);
                tMessage.setMessage("获取成功");
                out.print(JSON.toJSON(tMessage));
                break;
            case "GetInfoNotice":
                int nid = Integer.parseInt(req.getParameter("nid"));
                List<Notice> infodata = noticeDao.getInfoData(nid);
                tMessage.setCode(200);
                tMessage.setData(infodata);
                tMessage.setMessage("获取成功");
                out.print(JSON.toJSON(tMessage));
                break;
        }

    }
}
