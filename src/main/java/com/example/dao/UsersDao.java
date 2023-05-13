package com.example.dao;

import com.example.entity.Notice;
import com.example.entity.Users;

import java.util.List;

public interface UsersDao {

    public List<Users> getData(String uaccount);

    public int logininfo(String uaccount, String upassword);

    public int register(String uaccount, String upassword, String unickname);

    public int selectuser(String uaccount);

    public List<Users> getUserData(int pid);

    public int updateUserBackgroundurl(String inputText, String uaccount);

    public int updateUserAvatarurl(String inputText, String uaccount);

    public int updateUserNickname(String inputText, String uaccount);

    public int updateUserSignature(String inputText, String uaccount);

    public int updateUserPassword(String inputText, String uaccount);

    public List<Users> getFocusUserData(String uaccount);

    public List<Users> getUserFocusData(String uaccount);

    public List<Users> getUserByuaccountData(String uaccount);

    public List<Users> getSearchUserData(String uaccount);

    public int loginAdmin(String uaccount, String upassword);

    public List<Users> getAllData();

    public int insertData(String uaccount, String upassword, String unickname, int upermissions);

    public int deleteData(int id);

    public int updateData(int id,String uaccount,String upassword,String unickname,String usignature,String upermissions,String ustate);

}
