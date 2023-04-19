package com.example.dao.impl;

import com.example.dao.PostsCommentDao;
import com.example.entity.Posts;
import com.example.entity.PostsComment;
import com.example.utils.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PostsCommentDaoImpl implements PostsCommentDao {

     Connection connection = null;
     Connection connection1 = null;
     ResultSet resultSet = null;
     ResultSet resultSet1 = null;
     PreparedStatement preparedStatement = null;
     PreparedStatement preparedStatement1 = null;
    int i = 0;

    @Override
    public List<PostsComment> getData(int pid) {
        List<PostsComment> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        String sql = "select a.cid,b.uaccount,b.uavatarurl,b.unickname,a.ccontent,DATE_FORMAT(a.cdate, '%Y-%m-%d %k:%i:%s') as cdate from postscomment a,users b where a.caccount=b.uaccount and a.cpostsid=? ORDER BY cdate DESC";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,pid);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PostsComment postscomment = new PostsComment();
                postscomment.setCid(resultSet.getInt(1));
                postscomment.setUaccount(resultSet.getString(2));
                postscomment.setAvatarurl(resultSet.getString(3));
                postscomment.setNickname(resultSet.getString(4));
                postscomment.setContent(resultSet.getString(5));
                postscomment.setCdate(resultSet.getString(6));

                list.add(postscomment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectDB.closeAll(resultSet,preparedStatement,connection);
        }
        return list;
    }

    @Override
    public int insertData(int pid, String uaccount, String comment) {
        connection = ConnectDB.getConn();
        String sql = "INSERT INTO postscomment (cpostsid,caccount,ccontent,cdate) VALUE(?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,pid);
            preparedStatement.setString(2,uaccount);
            preparedStatement.setString(3,comment);
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String datetime = formatter.format(calendar.getTime());
            preparedStatement.setString(4,datetime);
            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(null,preparedStatement,connection);
        }
        return i;
    }

    @Override
    public int selectData(int pid, String faccount, String uaccount) {
        connection = ConnectDB.getConn();
        String sql="SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END AS c FROM postscomment WHERE cpostsid=? AND caccount=? AND caccount = ?;";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,pid);
            preparedStatement.setString(2,faccount);
            preparedStatement.setString(3,uaccount);
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
    public int deleteData(int cid, String uaccount) {
        connection = ConnectDB.getConn();
        String sql="delete from postscomment where caccount=? and cid=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,uaccount);
            preparedStatement.setInt(2,cid);
            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(null,preparedStatement,connection);
        }
        return i;
    }

}
