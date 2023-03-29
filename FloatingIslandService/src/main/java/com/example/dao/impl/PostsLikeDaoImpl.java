package com.example.dao.impl;

import com.example.dao.PostsLikeDao;
import com.example.utils.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostsLikeDaoImpl implements PostsLikeDao {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    int i = 0;

    @Override
    public int insertData(int pid) {
        connection = ConnectDB.getConn();
        String sql = "INSERT INTO postslike (laccount,lpostsid) VALUE('paperfish',?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,pid);
            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(null,preparedStatement,connection);
        }
        return i;
    }

    @Override
    public int selectData(int pid) {
        connection = ConnectDB.getConn();
        String sql="select count(*) c from postslike where laccount='paperfish' and lpostsid=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,pid);
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
    public int deleteData(int pid) {
        connection = ConnectDB.getConn();
        String sql="delete from postslike where laccount='paperfish' and lpostsid=?;";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,pid);
            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(null,preparedStatement,connection);
        }
        return i;
    }
}
