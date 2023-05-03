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
        String sql = "select a.pid,b.uavatarurl,a.pcontent,a.pimageurl,b.unickname,c.tid,c.tname,c.timageurl,DATE_FORMAT(a.pdate, '%Y-%m-%d %k:%i:%s') as pdate from posts a,users b,topic c where a.paccount=b.uaccount and a.ptopicid=c.tid order by a.pdate desc";
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
                posts.setTopicid(resultSet.getInt(6));
                posts.setTopicname(resultSet.getString(7));
                posts.setTopicimageurl(resultSet.getString(8));
                posts.setDate(resultSet.getString(9));

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
    public int insertData(int tid, String uaccount, String pconnect, String pimageurl) {
        connection = ConnectDB.getConn();
        String sql = "INSERT INTO posts (pcontent,pimageurl,ptopicid,paccount,pdate) VALUE(?,?,?,?,?)";
        int i = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pconnect);
            preparedStatement.setString(2, pimageurl);
            preparedStatement.setInt(3, tid);
            preparedStatement.setString(4, uaccount);
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
        String sql = "select a.pid,b.uavatarurl,a.pcontent,a.pimageurl,b.unickname,c.tid,c.tname,c.timageurl,DATE_FORMAT(a.pdate, '%Y-%m-%d %k:%i:%s') as pdate from posts a,users b,topic c where a.paccount=b.uaccount and a.ptopicid=c.tid and a.paccount=? order by a.pdate desc";
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
                posts.setTopicid(resultSet.getInt(6));
                posts.setTopicname(resultSet.getString(7));
                posts.setTopicimageurl(resultSet.getString(8));
                posts.setDate(resultSet.getString(9));

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
        String sql = "select a.pid,b.uavatarurl,a.pcontent,a.pimageurl,b.unickname,c.tid,c.tname,c.timageurl,DATE_FORMAT(a.pdate, '%Y-%m-%d %k:%i:%s') as pdate from posts a,users b,topic c,postscollection d where a.paccount=b.uaccount and a.ptopicid=c.tid and d.cpostsid=a.pid and d.caccount=? order by d.cid desc";
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
                posts.setTopicid(resultSet.getInt(6));
                posts.setTopicname(resultSet.getString(7));
                posts.setTopicimageurl(resultSet.getString(8));
                posts.setDate(resultSet.getString(9));

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

            if(resultList != null && !resultList.isEmpty()){
                // 执行查询操作
                StringBuilder sqlBuilder = new StringBuilder("select a.pid,b.uavatarurl,a.pcontent,a.pimageurl,b.unickname,c.tid,c.tname,c.timageurl,DATE_FORMAT(a.pdate, '%Y-%m-%d %k:%i:%s') as pdate from posts a,users b,topic c where a.paccount=b.uaccount and a.ptopicid=c.tid and a.paccount in (?");
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
                    posts.setTopicid(resultSet3.getInt(6));
                    posts.setTopicname(resultSet3.getString(7));
                    posts.setTopicimageurl(resultSet3.getString(8));
                    posts.setDate(resultSet3.getString(9));

                    preparedStatement4 = connection4.prepareStatement(sql2);
                    preparedStatement4.setInt(1,posts.getPid());
                    resultSet4 = preparedStatement4.executeQuery();
                    while(resultSet4.next()){
                        j = resultSet4.getInt(1);
                    }
                    posts.setLikenum(j);

                    list.add(posts);
                }
            }else{
                // 关注列表为空，不执行查询操作
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
        String sql = "SELECT a.pid, b.uavatarurl, a.pcontent, a.pimageurl, b.unickname, c.tid,c.tname,c.timageurl, DATE_FORMAT(a.pdate, '%Y-%m-%d %k:%i:%s') as pdate " +
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
                posts.setTopicid(resultSet.getInt(6));
                posts.setTopicname(resultSet.getString(7));
                posts.setTopicimageurl(resultSet.getString(8));
                posts.setDate(resultSet.getString(9));

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
    public List<Posts> getUserPostsData(int pid) {
        List<Posts> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        connection1 = ConnectDB.getConn();
        connection2 = ConnectDB.getConn();
        String sql = "select a.pid,b.uavatarurl,a.pcontent,a.pimageurl,b.unickname,c.tid,c.tname,c.timageurl,DATE_FORMAT(a.pdate, '%Y-%m-%d %k:%i:%s') as pdate from posts a,users b,topic c where a.paccount=b.uaccount and a.ptopicid=c.tid and a.paccount=? order by a.pdate desc";
        String sql1 = "select count(*) c from postslike where lpostsid=?";
        String sql2 = "select paccount from posts where pid=?";
        String uaccount = "";
        int i=0;
        try {
            preparedStatement2 = connection2.prepareStatement(sql2);
            preparedStatement2.setInt(1,pid);
            resultSet2 = preparedStatement2.executeQuery();
            while(resultSet2.next()){
                uaccount = resultSet2.getString(1);
            }

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
                posts.setTopicid(resultSet.getInt(6));
                posts.setTopicname(resultSet.getString(7));
                posts.setTopicimageurl(resultSet.getString(8));
                posts.setDate(resultSet.getString(9));

                preparedStatement1 = connection1.prepareStatement(sql1);
                preparedStatement1.setInt(1,pid);
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
            ConnectDB.closeAll(resultSet2,preparedStatement2,connection2);
        }
        return list;
    }

    @Override
    public int updateData(int pid, String uaccount, String pconnect, String pimageurl) {
        connection = ConnectDB.getConn();
        String sql = "update posts set pcontent=?,pimageurl=?,ptopicid=?,paccount=? where pid=?";
        int i = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pconnect);
            preparedStatement.setString(2, pimageurl);
            preparedStatement.setInt(3, 1);
            preparedStatement.setString(4, uaccount);
            preparedStatement.setInt(5, pid);
            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(null, preparedStatement, connection);
        }
        return i;
    }

    @Override
    public int deleteData(int pid, String uaccount) {
        connection = ConnectDB.getConn();
        String sql = "delete from posts where pid=? and paccount=?";
        int i = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, pid);
            preparedStatement.setString(2, uaccount);
            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(null, preparedStatement, connection);
        }
        return i;
    }

    @Override
    public List<Posts> getTidPostsData(int tid) {
        List<Posts> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        connection1 = ConnectDB.getConn();
        String sql = "select a.pid,b.uavatarurl,a.pcontent,a.pimageurl,b.unickname,c.tid,c.tname,c.timageurl,DATE_FORMAT(a.pdate, '%Y-%m-%d %k:%i:%s') as pdate from posts a,users b,topic c where a.paccount=b.uaccount and a.ptopicid=c.tid and c.tid=? order by a.pdate desc";
        String sql1 = "select count(*) c from postslike where lpostsid=?";
        int i=0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,tid);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Posts posts = new Posts();
                posts.setPid(resultSet.getInt(1));
                posts.setAvatarurl(resultSet.getString(2));
                posts.setContent(resultSet.getString(3));
                posts.setImageurl(resultSet.getString(4));
                posts.setNickname(resultSet.getString(5));
                posts.setTopicid(resultSet.getInt(6));
                posts.setTopicname(resultSet.getString(7));
                posts.setTopicimageurl(resultSet.getString(8));
                posts.setDate(resultSet.getString(9));

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
    public List<Posts> getRankingData() {
        List<Posts> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        String sql = "SELECT a.pcontent, (SELECT COUNT(*) FROM postslike WHERE lpostsid = a.pid) AS likes FROM posts a ORDER BY likes DESC LIMIT 10";
        int i=0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Posts posts = new Posts();
                posts.setContent(resultSet.getString(1));
                posts.setLikenum(resultSet.getInt(2));

                list.add(posts);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(resultSet,preparedStatement,connection);
        }
        return list;
    }

    @Override
    public List<Posts> getUserPostsByuaccountData(String uaccount) {
        List<Posts> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        connection1 = ConnectDB.getConn();
        String sql = "select a.pid,b.uavatarurl,a.pcontent,a.pimageurl,b.unickname,c.tid,c.tname,c.timageurl,DATE_FORMAT(a.pdate, '%Y-%m-%d %k:%i:%s') as pdate from posts a,users b,topic c where a.paccount=b.uaccount and a.ptopicid=c.tid and a.paccount=? order by a.pdate desc";
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
                posts.setTopicid(resultSet.getInt(6));
                posts.setTopicname(resultSet.getString(7));
                posts.setTopicimageurl(resultSet.getString(8));
                posts.setDate(resultSet.getString(9));

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
    public List<Posts> getRankingCollectionData() {
        List<Posts> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        String sql = "SELECT a.pcontent, (SELECT COUNT(*) FROM postscollection WHERE cpostsid = a.pid) AS collection FROM posts a ORDER BY collection DESC LIMIT 10";
        int i=0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Posts posts = new Posts();
                posts.setContent(resultSet.getString(1));
                posts.setLikenum(resultSet.getInt(2));

                list.add(posts);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(resultSet,preparedStatement,connection);
        }
        return list;
    }

    @Override
    public List<Posts> getRankingCommentData() {
        List<Posts> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        String sql = "SELECT a.pcontent, (SELECT COUNT(*) FROM postscomment WHERE cpostsid = a.pid) AS comment FROM posts a ORDER BY comment DESC LIMIT 10";
        int i=0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Posts posts = new Posts();
                posts.setContent(resultSet.getString(1));
                posts.setLikenum(resultSet.getInt(2));

                list.add(posts);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(resultSet,preparedStatement,connection);
        }
        return list;
    }

    @Override
    public List<Posts> getDataAdmin() {
        List<Posts> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        connection1 = ConnectDB.getConn();
        connection2 = ConnectDB.getConn();
        connection3 = ConnectDB.getConn();
        String sql = "select a.pid,b.uavatarurl,a.pcontent,a.pimageurl,b.uaccount,b.unickname,c.tid,c.tname,c.timageurl,DATE_FORMAT(a.pdate, '%Y-%m-%d %k:%i:%s') as pdate from posts a,users b,topic c where a.paccount=b.uaccount and a.ptopicid=c.tid order by a.pdate desc";
        String sql1 = "select count(*) c from postslike where lpostsid=?";
        String sql2 = "select count(*) c from postscollection where cpostsid=?";
        String sql3 = "select count(*) c from postscomment where cpostsid=?";
        int i=0;
        int j=0;
        int k=0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Posts posts = new Posts();
                posts.setPid(resultSet.getInt(1));
                posts.setAvatarurl(resultSet.getString(2));
                posts.setContent(resultSet.getString(3));
                posts.setImageurl(resultSet.getString(4));
                posts.setUaccount(resultSet.getString(5));
                posts.setNickname(resultSet.getString(6));
                posts.setTopicid(resultSet.getInt(7));
                posts.setTopicname(resultSet.getString(8));
                posts.setTopicimageurl(resultSet.getString(9));
                posts.setDate(resultSet.getString(10));

                preparedStatement1 = connection1.prepareStatement(sql1);
                preparedStatement1.setInt(1,posts.getPid());
                resultSet1 = preparedStatement1.executeQuery();
                while(resultSet1.next()){
                    i = resultSet1.getInt(1);
                }
                posts.setLikenum(i);

                preparedStatement2 = connection2.prepareStatement(sql2);
                preparedStatement2.setInt(1,posts.getPid());
                resultSet2 = preparedStatement2.executeQuery();
                while(resultSet2.next()){
                    j = resultSet2.getInt(1);
                }
                posts.setCollectionnum(j);

                preparedStatement3 = connection3.prepareStatement(sql3);
                preparedStatement3.setInt(1,posts.getPid());
                resultSet3 = preparedStatement3.executeQuery();
                while(resultSet3.next()){
                    k = resultSet3.getInt(1);
                }
                posts.setCommentnum(k);

                list.add(posts);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(resultSet,preparedStatement,connection);
            ConnectDB.closeAll(resultSet1,preparedStatement1,connection1);
            ConnectDB.closeAll(resultSet2,preparedStatement2,connection2);
            ConnectDB.closeAll(resultSet3,preparedStatement3,connection3);
        }
        return list;
    }

    @Override
    public int deleteDataAdmin(int id) {
        connection = ConnectDB.getConn();
        String sql="delete from posts where pid=?";
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
}
