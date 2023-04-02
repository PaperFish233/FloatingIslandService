package com.example.dao.impl;

import com.example.dao.PostsCollectionDao;
import com.example.utils.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostsCollectionDaoImpl implements PostsCollectionDao {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    int i = 0;

    @Override
    public int insertData(int pid,String uaccount) {
        connection = ConnectDB.getConn();
        String sql = "INSERT INTO postscollection (caccount,cpostsid) VALUE(?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,uaccount);
            preparedStatement.setInt(2,pid);
            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(null,preparedStatement,connection);
        }
        return i;
    }

    @Override
    public int selectData(int pid,String uaccount) {
        connection = ConnectDB.getConn();
        String sql="select count(*) c from postscollection where caccount=? and cpostsid=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,uaccount);
            preparedStatement.setInt(2,pid);
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

    @Override
    public int deleteData(int pid,String uaccount) {
        connection = ConnectDB.getConn();
        String sql="delete from postscollection where caccount=? and cpostsid=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,uaccount);
            preparedStatement.setInt(2,pid);
            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(null,preparedStatement,connection);
        }
        return i;
    }
}
