package com.example.dao;

import com.example.entity.Users;

import java.util.List;

public interface UsersDao {

    public List<Users> getData(String uaccount);

    public int logininfo(String uaccount, String upassword);

    public int register(String uavatarurl, String uaccount, String upassword, String unickname);

    public int selectuser(String uaccount);

}
