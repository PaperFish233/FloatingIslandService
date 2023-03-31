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
}
