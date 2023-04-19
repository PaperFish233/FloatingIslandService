package com.example.servlet;

import com.alibaba.fastjson.JSON;
import com.example.dao.UsersDao;
import com.example.dao.impl.UsersDaoImpl;
import com.example.entity.Users;
import com.example.utils.TMessage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "UsersServlet", value = "/UsersServlet")
public class UsersServlet extends HttpServlet {
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
        TMessage<List<Users>> tMessage = new TMessage();
        UsersDao usersDao = new UsersDaoImpl();
        String action = req.getParameter("action");
        switch (action) {
            case "GetUsers":
                String uaccount = req.getParameter("uaccount");
                List<Users> data = usersDao.getData(uaccount);
                tMessage.setCode(200);
                tMessage.setData(data);
                tMessage.setMessage("获取成功");
                out.print(JSON.toJSON(tMessage));
                break;
            case "Login":
                String uaccount1 = req.getParameter("uaccount");
                String upassword1 = req.getParameter("upassword");
                int i = usersDao.logininfo(uaccount1,upassword1);
                tMessage.setCode(200);
                if(i==1){
                    tMessage.setMessage("登录成功");
                }else{
                    tMessage.setMessage("登录失败");
                }
                out.print(JSON.toJSON(tMessage));
                break;
            case "Register":
                String uaccount2 = req.getParameter("uaccount");
                String upassword2 = req.getParameter("upassword");
                String unickname2 = req.getParameter("unickname");
                tMessage.setCode(200);
                int i2 = usersDao.selectuser(uaccount2);
                if(i2==1){
                    tMessage.setMessage("账号已存在");
                    out.print(JSON.toJSON(tMessage));
                    break;
                }else{
                    int i3 = usersDao.register(uaccount2,upassword2,unickname2);
                    if(i3==1){
                        tMessage.setMessage("注册成功");
                    }else{
                        tMessage.setMessage("注册失败");
                    }
                    out.print(JSON.toJSON(tMessage));
                }
                break;
            case "GetUserInfo":
                int pid = Integer.parseInt(req.getParameter("pid"));
                List<Users> userinfodata = usersDao.getUserData(pid);
                tMessage.setCode(200);
                tMessage.setData(userinfodata);
                tMessage.setMessage("获取成功");
                out.print(JSON.toJSON(tMessage));
                break;
            case "UpdateUserBackgroundurl":
                String inputText4 = req.getParameter("inputText");
                String uaccount4 = req.getParameter("uaccount");
                int i4 = usersDao.updateUserBackgroundurl(inputText4,uaccount4);
                tMessage.setCode(200);
                if(i4==1){
                    tMessage.setMessage("更新成功");
                }else{
                    tMessage.setMessage("更新失败");
                }
                out.print(JSON.toJSON(tMessage));
                break;
            case "UpdateUserAvatarurl":
                String inputText5 = req.getParameter("inputText");
                String uaccount5 = req.getParameter("uaccount");
                int i5 = usersDao.updateUserAvatarurl(inputText5,uaccount5);
                tMessage.setCode(200);
                if(i5==1){
                    tMessage.setMessage("更新成功");
                }else{
                    tMessage.setMessage("更新失败");
                }
                out.print(JSON.toJSON(tMessage));
                break;
            case "UpdateUserNickname":
                String inputText8 = req.getParameter("inputText");
                String uaccount8 = req.getParameter("uaccount");
                int i8 = usersDao.updateUserNickname(inputText8,uaccount8);
                tMessage.setCode(200);
                if(i8==1){
                    tMessage.setMessage("更新成功");
                }else{
                    tMessage.setMessage("更新失败");
                }
                out.print(JSON.toJSON(tMessage));
                break;
            case "UpdateUserSignature":
                String inputText9 = req.getParameter("inputText");
                String uaccount9 = req.getParameter("uaccount");
                int i9 = usersDao.updateUserSignature(inputText9,uaccount9);
                tMessage.setCode(200);
                if(i9==1){
                    tMessage.setMessage("更新成功");
                }else{
                    tMessage.setMessage("更新失败");
                }
                out.print(JSON.toJSON(tMessage));
                break;
            case "UpdateUserPassword":
                String inputText10 = req.getParameter("inputText");
                String uaccount10 = req.getParameter("uaccount");
                int i10 = usersDao.updateUserPassword(inputText10,uaccount10);
                tMessage.setCode(200);
                if(i10==1){
                    tMessage.setMessage("更新成功");
                }else{
                    tMessage.setMessage("更新失败");
                }
                out.print(JSON.toJSON(tMessage));
                break;
            case "GetFacusUser":
                String uaccount11 = req.getParameter("uaccount");
                List<Users> facususer = usersDao.getFocusUserData(uaccount11);
                tMessage.setCode(200);
                tMessage.setData(facususer);
                tMessage.setMessage("获取成功");
                out.print(JSON.toJSON(tMessage));
                break;
            case "GetUserFacus":
                String uaccount12 = req.getParameter("uaccount");
                List<Users> userfacus = usersDao.getUserFocusData(uaccount12);
                tMessage.setCode(200);
                tMessage.setData(userfacus);
                tMessage.setMessage("获取成功");
                out.print(JSON.toJSON(tMessage));
                break;
            case "GetUserInfoByuaccount":
                String uaccount13 = req.getParameter("uaccount");
                List<Users> userinfobyuaccountdata = usersDao.getUserByuaccountData(uaccount13);
                tMessage.setCode(200);
                tMessage.setData(userinfobyuaccountdata);
                tMessage.setMessage("获取成功");
                out.print(JSON.toJSON(tMessage));
                break;
            case "GetSearchUser":
                String unickname = req.getParameter("unickname");
                List<Users> searchuser = usersDao.getSearchUserData(unickname);
                tMessage.setCode(200);
                tMessage.setData(searchuser);
                tMessage.setMessage("获取成功");
                out.print(JSON.toJSON(tMessage));
                break;
        }

    }
}
