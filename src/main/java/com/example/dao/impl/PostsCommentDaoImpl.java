package com.example.dao.impl;

import com.example.dao.PostsCommentDao;
import com.example.entity.Posts;
import com.example.entity.PostsComment;
import com.example.utils.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostsCommentDaoImpl implements PostsCommentDao {

    static Connection connection = null;
    static Connection connection1 = null;
    static ResultSet resultSet = null;
    static ResultSet resultSet1 = null;
    static PreparedStatement preparedStatement = null;
    static PreparedStatement preparedStatement1 = null;

    @Override
    public List<PostsComment> getData(int pid) {
        List<PostsComment> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        String sql = "select b.uavatarurl,b.unickname,a.ccontent,DATE_FORMAT(a.cdate, '%Y-%m-%d %k:%i:%s') as cdate from postscomment a,users b where a.caccount=b.uaccount and a.cpostsid=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,pid);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PostsComment postscomment = new PostsComment();
                postscomment.setAvatarurl(resultSet.getString(1));
                postscomment.setNickname(resultSet.getString(2));
                postscomment.setContent(resultSet.getString(3));
                postscomment.setCdate(resultSet.getString(4));

                list.add(postscomment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectDB.closeAll(resultSet,preparedStatement,connection);
        }
        return list;
    }

}
