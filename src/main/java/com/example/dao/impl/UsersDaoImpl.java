package com.example.dao.impl;

import com.example.dao.UsersDao;
import com.example.entity.Users;
import com.example.utils.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoImpl implements UsersDao {

    Connection connection = null;
    Connection connection1 = null;
    Connection connection2 = null;
    Connection connection3 = null;
    ResultSet resultSet = null;
    ResultSet resultSet1 = null;
    ResultSet resultSet2 = null;
    ResultSet resultSet3 = null;
    PreparedStatement preparedStatement = null;
    PreparedStatement preparedStatement1 = null;
    PreparedStatement preparedStatement2 = null;
    PreparedStatement preparedStatement3 = null;

    @Override
    public List<Users> getData(String uaccount) {
        int i = 0;
        int j = 0;
        int n = 0;
        List<Users> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        connection1 = ConnectDB.getConn();
        connection2 = ConnectDB.getConn();
        connection3 = ConnectDB.getConn();
        String sql = "select uavatarurl,unickname,uaccount,usignature,ubackgroundurl from users where uaccount=?";
        String sql1 = "select count(*) c from posts where paccount=?";
        String sql2 = "select count(*) c from usersfocus where faccount=?";
        String sql3 = "select count(*) c from usersfocus where uaccount=?";
        try {
            preparedStatement1 = connection1.prepareStatement(sql1);
            preparedStatement1.setString(1,uaccount);
            resultSet1 = preparedStatement1.executeQuery();
            while(resultSet1.next()){
                i = resultSet1.getInt(1);
            }

            preparedStatement2 = connection2.prepareStatement(sql2);
            preparedStatement2.setString(1,uaccount);
            resultSet2 = preparedStatement2.executeQuery();
            while(resultSet2.next()){
                j = resultSet2.getInt(1);
            }

            preparedStatement3 = connection3.prepareStatement(sql3);
            preparedStatement3.setString(1,uaccount);
            resultSet3 = preparedStatement3.executeQuery();
            while(resultSet3.next()){
                n = resultSet3.getInt(1);
            }

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,uaccount);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Users users = new Users();
                users.setUavatarurl(resultSet.getString(1));
                users.setUnickname(resultSet.getString(2));
                users.setUaccount(resultSet.getString(3));
                users.setUsignature(resultSet.getString(4));
                users.setUbackgroundurl(resultSet.getString(5));
                users.setPostsnum(i);
                users.setFfocusnum(j);
                users.setUfocusnum(n);

                list.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(resultSet,preparedStatement,connection);
            ConnectDB.closeAll(resultSet1,preparedStatement1,connection1);
            ConnectDB.closeAll(resultSet2,preparedStatement2,connection2);
            ConnectDB.closeAll(resultSet3,preparedStatement3,connection3);
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
        String sql = "INSERT INTO users (uavatarurl,uaccount,upassword,unickname,usignature,ubackgroundurl,upermissions,ustate) VALUE(?,?,?,?,'这个人很懒,什么也没有留下。','https://i.imgtg.com/2023/04/12/8JnYg.jpg',1,1)";
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
