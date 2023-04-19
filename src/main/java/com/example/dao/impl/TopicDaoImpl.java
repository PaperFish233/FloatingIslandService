package com.example.dao.impl;

import com.example.dao.TopicDao;
import com.example.entity.Posts;
import com.example.entity.Topic;
import com.example.entity.Users;
import com.example.utils.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicDaoImpl implements TopicDao {

    Connection connection = null;
    Connection connection1 = null;
    ResultSet resultSet = null;
    ResultSet resultSet1 = null;
    PreparedStatement preparedStatement = null;
    PreparedStatement preparedStatement1 = null;

    @Override
    public List<Topic> getData() {
        List<Topic> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        String sql = "select tid,tname,timageurl,tsignature from topic order by tid desc";
        int i=0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Topic topic = new Topic();
                topic.setTid(resultSet.getInt(1));
                topic.setTname(resultSet.getString(2));
                topic.setTimageurl(resultSet.getString(3));
                topic.setTsignature(resultSet.getString(4));

                list.add(topic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(resultSet,preparedStatement,connection);
        }
        return list;
    }

    @Override
    public List<Topic> getTidData(int tid) {
        int i = 0;
        List<Topic> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        connection1 = ConnectDB.getConn();
        String sql = "select tname,timageurl,tsignature from topic where tid=?";
        String sql1 = "select count(*) c from posts where ptopicid=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,tid);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Topic topic = new Topic();
                topic.setTname(resultSet.getString(1));
                topic.setTimageurl(resultSet.getString(2));
                topic.setTsignature(resultSet.getString(3));

                preparedStatement1 = connection1.prepareStatement(sql1);
                preparedStatement1.setInt(1,tid);
                resultSet1 = preparedStatement1.executeQuery();
                while(resultSet1.next()){
                    i = resultSet1.getInt(1);
                }
                topic.setTpostsnum(i);

                list.add(topic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(resultSet,preparedStatement,connection);
            ConnectDB.closeAll(resultSet1,preparedStatement1,connection1);
        }
        return list;
    }

}
