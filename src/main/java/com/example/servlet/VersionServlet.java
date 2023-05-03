package com.example.servlet;

import com.alibaba.fastjson.JSON;
import com.example.dao.VersionDao;
import com.example.dao.impl.VersionDaoImpl;
import com.example.entity.Version;
import com.example.utils.TMessage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "VersionServlet", value = "/VersionServlet")
public class VersionServlet extends HttpServlet {
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
        TMessage<List<Version>> tMessage = new TMessage();
        VersionDao versiondao = new VersionDaoImpl();

        String action = req.getParameter("action");
        switch (action) {
            case "GetVersion":
                List<Version> data = versiondao.getData();
                tMessage.setCode(200);
                tMessage.setData(data);
                tMessage.setMessage("获取成功");
                out.print(JSON.toJSON(tMessage));
                break;
            case "InsertVersion":
                String vnumber = req.getParameter("vnumber");
                String vupdatetitle = req.getParameter("vupdatetitle");
                String vcontent = req.getParameter("vcontent");
                String vapkurl = req.getParameter("vapkurl");
                int i =versiondao.insertData(vnumber,vupdatetitle,vcontent,vapkurl);
                tMessage.setCode(200);
                if(i==1){
                    tMessage.setMessage("发布成功");
                }else{
                    tMessage.setMessage("发布失败");
                }
                out.print(JSON.toJSON(tMessage));
                break;
            case "DeleteVersion":
                int deleteid = Integer.parseInt(req.getParameter("deleteid"));
                int i2 =versiondao.deleteData(deleteid);
                tMessage.setCode(200);
                if(i2==1){
                    tMessage.setMessage("删除成功");
                }else{
                    tMessage.setMessage("删除失败");
                }
                out.print(JSON.toJSON(tMessage));
                break;
            case "UpdateVersion":
                int updateid = Integer.parseInt(req.getParameter("updateid"));
                String vnumber1 = req.getParameter("vnumber1");
                String vupdatetitle1 = req.getParameter("vupdatetitle1");
                String vcontent1 = req.getParameter("vcontent1");
                String vapkurl1 = req.getParameter("vapkurl1");
                int i3 =versiondao.updateData(updateid,vnumber1,vupdatetitle1,vcontent1,vapkurl1);
                tMessage.setCode(200);
                if(i3==1){
                    tMessage.setMessage("更新成功");
                }else{
                    tMessage.setMessage("更新失败");
                }
                out.print(JSON.toJSON(tMessage));
                break;
            case "GetNewVersion":
                List<Version> newdata = versiondao.getNewData();
                tMessage.setCode(200);
                tMessage.setData(newdata);
                tMessage.setMessage("获取成功");
                out.print(JSON.toJSON(tMessage));
                break;
        }

    }
}
