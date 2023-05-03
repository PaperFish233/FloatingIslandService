package com.example.dao.impl;

import com.example.dao.VersionDao;
import com.example.entity.Version;
import com.example.utils.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VersionDaoImpl implements VersionDao {

    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    @Override
    public List<Version> getData() {
        List<Version> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        String sql = "select * from version order by vid desc";
        int i=0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Version version = new Version();
                version.setVid(resultSet.getInt(1));
                version.setVnumber(resultSet.getString(2));
                version.setVupdatetitle(resultSet.getString(3));
                version.setVcontent(resultSet.getString(4));
                version.setVapkurl(resultSet.getString(5));

                list.add(version);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(resultSet,preparedStatement,connection);
        }
        return list;
    }

    @Override
    public int insertData(String vnumber,String vupdatetitle,String vcontent,String vapkurl) {
        connection = ConnectDB.getConn();
        String sql = "INSERT INTO version (vnumber,vupdatetitle,vcontent,vapkurl) VALUE(?,?,?,?)";
        int i = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, vnumber);
            preparedStatement.setString(2, vupdatetitle);
            preparedStatement.setString(3, vcontent);
            preparedStatement.setString(4, vapkurl);
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
        String sql="delete from version where vid=?";
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
    public int updateData(int id, String vnumber, String vupdatetitle, String vcontent, String vapkurl) {
        connection = ConnectDB.getConn();
        String sql = "update version set vnumber=?,vupdatetitle=?,vcontent=?,vapkurl=? where vid=?";
        int i = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, vnumber);
            preparedStatement.setString(2, vupdatetitle);
            preparedStatement.setString(3, vcontent);
            preparedStatement.setString(4, vapkurl);
            preparedStatement.setInt(5, id);
            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(null, preparedStatement, connection);
        }
        return i;
    }

    @Override
    public List<Version> getNewData() {
        List<Version> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        String sql = "select * from version order by vid desc limit 1";
        int i=0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Version version = new Version();
                version.setVid(resultSet.getInt(1));
                version.setVnumber(resultSet.getString(2));
                version.setVupdatetitle(resultSet.getString(3));
                version.setVcontent(resultSet.getString(4));
                version.setVapkurl(resultSet.getString(5));

                list.add(version);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(resultSet,preparedStatement,connection);
        }
        return list;
    }
}
