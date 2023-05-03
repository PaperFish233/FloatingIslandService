package com.example.dao.impl;

import com.example.dao.TopicDao;
import com.example.entity.Topic;
import com.example.utils.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

    @Override
    public int insertData(String tname, String timageurl, String tsignature) {
        connection = ConnectDB.getConn();
        String sql = "INSERT INTO topic (tname,timageurl,tsignature) VALUE(?,?,?)";
        int i = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tname);
            preparedStatement.setString(2, timageurl);
            preparedStatement.setString(3, tsignature);
            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(null, preparedStatement, connection);
        }
        return i;
    }

    @Override
    public int deleteData(int id) {
        connection = ConnectDB.getConn();
        String sql="delete from topic where tid=?";
        int i = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(null,preparedStatement,connection);
        }
        return i;
    }

    @Override
    public int updateData(int id, String tname, String timageurl, String tsignature) {
        connection = ConnectDB.getConn();
        String sql = "update topic set tname=?,timageurl=?,tsignature=? where tid=?";
        int i = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tname);
            preparedStatement.setString(2, timageurl);
            preparedStatement.setString(3, tsignature);
            preparedStatement.setInt(4, id);
            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(null, preparedStatement, connection);
        }
        return i;
    }

    @Override
    public List<Topic> getNumData() {
        List<Topic> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        String sql = "SELECT t.tid, t.tname, COUNT(p.ptopicid) AS post_count FROM topic t INNER JOIN posts p ON t.tid = p.ptopicid GROUP BY t.tid, t.tname";
        int i=0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Topic topic = new Topic();
                topic.setTid(resultSet.getInt(1));
                topic.setTname(resultSet.getString(2));
                topic.setTpostsnum(resultSet.getInt(3));

                list.add(topic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(resultSet,preparedStatement,connection);
        }
        return list;
    }

}
