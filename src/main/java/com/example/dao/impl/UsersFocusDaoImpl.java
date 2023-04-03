package com.example.dao.impl;

import com.example.dao.UsersFocusDao;
import com.example.utils.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersFocusDaoImpl implements UsersFocusDao {

    Connection connection = null;
    Connection connection1 = null;
    PreparedStatement preparedStatement = null;
    PreparedStatement preparedStatement1 = null;
    ResultSet resultSet = null;
    ResultSet resultSet1 = null;
    int i = 0;

    @Override
    public int insertData(int pid,String uaccount) {
        connection = ConnectDB.getConn();
        connection1 = ConnectDB.getConn();
        String sql = "INSERT INTO usersfocus (faccount,uaccount) VALUE(?,?)";
        String sql1 = "select paccount from posts where pid=?";
        String str="";
        try {
            preparedStatement1 = connection1.prepareStatement(sql1);
            preparedStatement1.setInt(1,pid);
            resultSet1 = preparedStatement1.executeQuery();
            while (resultSet1.next()) {
                str = resultSet1.getString(1);
            }

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,uaccount);
            preparedStatement.setString(2,str);
            i = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(null,preparedStatement,connection);
            ConnectDB.closeAll(resultSet1,preparedStatement1,connection1);
        }
        return i;
    }

    @Override
    public int selectData(int pid,String uaccount) {
        connection = ConnectDB.getConn();
        connection1 = ConnectDB.getConn();
        String sql="select count(*) c from usersfocus where faccount=? and uaccount=?";
        String sql1 = "select paccount from posts where pid=?";
        String str="";
        try {
            preparedStatement1 = connection1.prepareStatement(sql1);
            preparedStatement1.setInt(1,pid);
            resultSet1 = preparedStatement1.executeQuery();
            while (resultSet1.next()) {
                str = resultSet1.getString(1);
            }

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,uaccount);
            preparedStatement.setString(2,str);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                i = resultSet.getInt("c");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(resultSet,preparedStatement,connection);
            ConnectDB.closeAll(resultSet1,preparedStatement1,connection1);
        }
        return i;
    }
}
