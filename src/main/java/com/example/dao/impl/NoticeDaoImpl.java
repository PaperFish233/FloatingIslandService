package com.example.dao.impl;

import com.example.dao.NoticeDao;
import com.example.entity.Notice;
import com.example.entity.Posts;
import com.example.utils.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoticeDaoImpl implements NoticeDao {

    Connection connection = null;
    Connection connection1 = null;
    ResultSet resultSet = null;
    ResultSet resultSet1 = null;
    PreparedStatement preparedStatement = null;
    PreparedStatement preparedStatement1 = null;

    @Override
    public List<Notice> getData() {
        List<Notice> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        String sql = "select nid,nimageurl from notice ORDER BY ndate DESC LIMIT 5";
        int i=0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Notice notice = new Notice();
                notice.setNid(resultSet.getInt(1));
                notice.setNimageurl(resultSet.getString(2));

                list.add(notice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(resultSet,preparedStatement,connection);
        }
        return list;
    }

    @Override
    public List<Notice> getInfoData(int nid) {
        List<Notice> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        String sql = "select a.naccount,a.ncontent,a.nimageurl,DATE_FORMAT(a.ndate, '%m-%d %k:%i:%s') as ndate,b.uavatarurl,b.unickname from notice a,users b where a.naccount=b.uaccount and a.nid=? ORDER BY ndate DESC LIMIT 5";
        int i=0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,nid);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Notice notice = new Notice();
                notice.setNaccount(resultSet.getString(1));
                notice.setNcontent(resultSet.getString(2));
                notice.setNimageurl(resultSet.getString(3));
                notice.setNdate(resultSet.getString(4));
                notice.setUavatarurl(resultSet.getString(5));
                notice.setUnickname(resultSet.getString(6));

                list.add(notice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(resultSet,preparedStatement,connection);
        }
        return list;
    }
}
