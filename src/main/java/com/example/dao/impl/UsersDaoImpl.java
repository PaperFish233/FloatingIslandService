package com.example.dao.impl;

import com.example.dao.UsersDao;
import com.example.entity.Users;
import com.example.utils.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UsersDaoImpl implements UsersDao {

    static Connection connection = null;
    static ResultSet resultSet = null;
    static PreparedStatement preparedStatement = null;

    @Override
    public List<Users> getData(String uaccount) {
        List<Users> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        String sql = "select uavatarurl,unickname,uaccount,usignature from users where uaccount=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,uaccount);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Users users = new Users();
                users.setUavatarurl(resultSet.getString(1));
                users.setUnickname(resultSet.getString(2));
                users.setUaccount(resultSet.getString(3));
                users.setUsignature(resultSet.getString(4));
                list.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int logininfo(String uaccount, String upassword) {
        connection = ConnectDB.getConn();
        String sql = "select count(*) c from users where uaccount=? and upassword=?";
        int i = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, uaccount);
            preparedStatement.setString(2, upassword);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                i=resultSet.getInt("c");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(resultSet, preparedStatement, connection);
        }
        return i;
    }

    @Override
    public int register(String uavatarurl, String uaccount, String upassword, String unickname) {
        connection = ConnectDB.getConn();
        String sql = "INSERT INTO users (uavatarurl,uaccount,upassword,unickname,usignature,upermissions,ustate) VALUE(?,?,?,?,'这个人很懒,什么也没有留下。',1,1)";
        int i = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, uavatarurl);
            preparedStatement.setString(2, uaccount);
            preparedStatement.setString(3, upassword);
            preparedStatement.setString(4, unickname);
            i = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(null, preparedStatement, connection);
        }
        return i;
    }

    @Override
    public int selectuser(String uaccount) {
        connection = ConnectDB.getConn();
        String sql="select count(*) c from users where uaccount=?";
        int i = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,uaccount);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                i=resultSet.getInt("c");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(resultSet,preparedStatement,connection);
        }
        return i;
    }
}
