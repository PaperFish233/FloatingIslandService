package com.example.dao.impl;

import com.example.dao.PostsDao;
import com.example.entity.Posts;
import com.example.utils.ConnectDB;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PostsDaoImpl implements PostsDao {

    static Connection connection = null;
    static Connection connection1 = null;
    static ResultSet resultSet = null;
    static PreparedStatement preparedStatement = null;
    static PreparedStatement preparedStatement1 = null;

    @Override
    public List<Posts> getData() {
        List<Posts> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        connection1 = ConnectDB.getConn();
        String sql = "select a.pid,b.uavatarurl,a.pcontent,a.pimageurl,b.unickname,c.tname,DATE_FORMAT(a.pdate, '%Y-%m-%d %k:%i:%s') as pdate from posts a,users b,topic c where a.paccount=b.uaccount and a.ptopicid=c.tid order by pid desc";
        String sql1 = "select count(*) c from postslike where lpostsid=?";
        ResultSet resultSet1 = null;
        int i=0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Posts posts = new Posts();
                posts.setPid(resultSet.getInt(1));
                posts.setAvatarurl(resultSet.getString(2));
                posts.setContent(resultSet.getString(3));
                posts.setImageurl(resultSet.getString(4));
                posts.setNickname(resultSet.getString(5));
                posts.setTopicname(resultSet.getString(6));
                posts.setDate(resultSet.getString(7));

                preparedStatement1 = connection1.prepareStatement(sql1);
                preparedStatement1.setInt(1,posts.getPid());
                resultSet1 = preparedStatement1.executeQuery();
                while(resultSet1.next()){
                    i = resultSet1.getInt(1);
                }
                posts.setLikenum(i);

                list.add(posts);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int insertData(String pconnect, String pimageurl) {
        connection = ConnectDB.getConn();
        String sql = "INSERT INTO posts (pcontent,pimageurl,ptopicid,paccount,pdate) VALUE(?,?,?,?,?)";
        int i = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pconnect);
            preparedStatement.setString(2, pimageurl);
            preparedStatement.setInt(3, 1);
            preparedStatement.setString(4, "paperfish");
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String datetime = formatter.format(calendar.getTime());
            preparedStatement.setString(5, datetime);
            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(null, preparedStatement, connection);
        }
        return i;
    }
}
