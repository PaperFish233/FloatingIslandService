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

    Connection connection = null;
    Connection connection1 = null;
    Connection connection2 = null;
    Connection connection3 = null;
    Connection connection4 = null;
    ResultSet resultSet = null;
    ResultSet resultSet1 = null;
    ResultSet resultSet2 = null;
    ResultSet resultSet3 = null;
    ResultSet resultSet4 = null;
    PreparedStatement preparedStatement = null;
    PreparedStatement preparedStatement1 = null;
    PreparedStatement preparedStatement2 = null;
    PreparedStatement preparedStatement3 = null;
    PreparedStatement preparedStatement4 = null;

    @Override
    public List<Posts> getData() {
        List<Posts> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        connection1 = ConnectDB.getConn();
        String sql = "select a.pid,b.uavatarurl,a.pcontent,a.pimageurl,b.unickname,c.tname,DATE_FORMAT(a.pdate, '%Y-%m-%d %k:%i:%s') as pdate from posts a,users b,topic c where a.paccount=b.uaccount and a.ptopicid=c.tid order by a.pdate desc";
        String sql1 = "select count(*) c from postslike where lpostsid=?";
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
        } finally {
            ConnectDB.closeAll(resultSet,preparedStatement,connection);
            ConnectDB.closeAll(resultSet1,preparedStatement1,connection1);
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

    @Override
    public List<Posts> getMineData(String uaccount) {
        List<Posts> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        connection1 = ConnectDB.getConn();
        String sql = "select a.pid,b.uavatarurl,a.pcontent,a.pimageurl,b.unickname,c.tname,DATE_FORMAT(a.pdate, '%Y-%m-%d %k:%i:%s') as pdate from posts a,users b,topic c where a.paccount=b.uaccount and a.ptopicid=c.tid and a.paccount=? order by a.pdate desc";
        String sql1 = "select count(*) c from postslike where lpostsid=?";
        int i=0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,uaccount);
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
        } finally {
            ConnectDB.closeAll(resultSet,preparedStatement,connection);
            ConnectDB.closeAll(resultSet1,preparedStatement1,connection1);
        }
        return list;
    }

    @Override
    public List<Posts> getMineCollectionData(String uaccount) {
        List<Posts> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        connection1 = ConnectDB.getConn();
        String sql = "select a.pid,b.uavatarurl,a.pcontent,a.pimageurl,b.unickname,c.tname,DATE_FORMAT(a.pdate, '%Y-%m-%d %k:%i:%s') as pdate from posts a,users b,topic c,postscollection d where a.paccount=b.uaccount and a.ptopicid=c.tid and d.cpostsid=a.pid and d.caccount=? order by d.cid desc";
        String sql1 = "select count(*) c from postslike where lpostsid=?";
        int i=0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,uaccount);
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
        } finally {
            ConnectDB.closeAll(resultSet,preparedStatement,connection);
            ConnectDB.closeAll(resultSet1,preparedStatement1,connection1);
        }
        return list;
    }

    @Override
    public List<Posts> getMineCollectionUserPostsData(String uaccount) {
        List<Posts> list = new ArrayList<>();
        connection2 = ConnectDB.getConn();
        connection3 = ConnectDB.getConn();
        connection4 = ConnectDB.getConn();
        int j=0;
        String sql = "select uaccount from usersfocus where faccount=?";
        String sql2 = "select count(*) c from postslike where lpostsid=?";
        try {
            preparedStatement2 = connection2.prepareStatement(sql);
            preparedStatement2.setString(1,uaccount);
            resultSet2 = preparedStatement2.executeQuery();

            ArrayList<String> resultList = new ArrayList<>(); // 用于存储查询结果
            while (resultSet2.next()) {
                resultList.add(resultSet2.getString(1)); // 获取结果集中的每个参数并添加到结果列表中
            }

            StringBuilder sqlBuilder = new StringBuilder("select a.pid,b.uavatarurl,a.pcontent,a.pimageurl,b.unickname,c.tname,DATE_FORMAT(a.pdate, '%Y-%m-%d %k:%i:%s') as pdate from posts a,users b,topic c where a.paccount=b.uaccount and a.ptopicid=c.tid and a.paccount in (?");
            for (int i = 1; i < resultList.size(); i++) {
                sqlBuilder.append(",?");
            }
            sqlBuilder.append(") ORDER BY pdate DESC");
            String sql1 = sqlBuilder.toString();

            preparedStatement3 = connection3.prepareStatement(sql1);
            for(int i=0;i<resultList.size();i++){
                preparedStatement3.setString(i+1,resultList.get(i));
            }
            resultSet3 = preparedStatement3.executeQuery();
            while (resultSet3.next()) {
                Posts posts = new Posts();
                posts.setPid(resultSet3.getInt(1));
                posts.setAvatarurl(resultSet3.getString(2));
                posts.setContent(resultSet3.getString(3));
                posts.setImageurl(resultSet3.getString(4));
                posts.setNickname(resultSet3.getString(5));
                posts.setTopicname(resultSet3.getString(6));
                posts.setDate(resultSet3.getString(7));

                preparedStatement4 = connection4.prepareStatement(sql2);
                preparedStatement4.setInt(1,posts.getPid());
                resultSet4 = preparedStatement4.executeQuery();
                while(resultSet4.next()){
                    j = resultSet4.getInt(1);
                }
                posts.setLikenum(j);

                list.add(posts);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(resultSet2,preparedStatement2,connection2);
            ConnectDB.closeAll(resultSet3,preparedStatement3,connection3);
            ConnectDB.closeAll(resultSet4,preparedStatement4,connection4);
        }
        return list;
    }

    @Override
    public List<Posts> getSearchPostsData(String keyword) {
        List<Posts> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        connection1 = ConnectDB.getConn();

        String keyword1 = keyword; // 要搜索的关键字
        String pattern = "%" + keyword1 + "%"; // 根据关键字构造模式字符串
        String sql = "SELECT a.pid, b.uavatarurl, a.pcontent, a.pimageurl, b.unickname, c.tname, DATE_FORMAT(a.pdate, '%Y-%m-%d %k:%i:%s') as pdate " +
                "FROM posts a, users b, topic c " +
                "WHERE a.paccount=b.uaccount AND a.ptopicid=c.tid AND a.pcontent LIKE ? " +
                "ORDER BY pdate DESC";

        String sql1 = "select count(*) c from postslike where lpostsid=?";
        int i=0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,pattern);
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
        } finally {
            ConnectDB.closeAll(resultSet,preparedStatement,connection);
            ConnectDB.closeAll(resultSet1,preparedStatement1,connection1);
        }
        return list;
    }
}
