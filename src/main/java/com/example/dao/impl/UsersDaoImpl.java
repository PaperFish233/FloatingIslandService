package com.example.dao.impl;

import com.example.dao.UsersDao;
import com.example.entity.Users;
import com.example.utils.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UsersDaoImpl implements UsersDao {

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
    public List<Users> getData(String uaccount) {
        int i = 0;
        int j = 0;
        int n = 0;
        List<Users> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        connection1 = ConnectDB.getConn();
        connection2 = ConnectDB.getConn();
        connection3 = ConnectDB.getConn();
        String sql = "select uavatarurl,unickname,uaccount,usignature,ubackgroundurl from users where uaccount=?";
        String sql1 = "select count(*) c from posts where paccount=?";
        String sql2 = "select count(*) c from usersfocus where faccount=?";
        String sql3 = "select count(*) c from usersfocus where uaccount=?";
        try {
            preparedStatement1 = connection1.prepareStatement(sql1);
            preparedStatement1.setString(1,uaccount);
            resultSet1 = preparedStatement1.executeQuery();
            while(resultSet1.next()){
                i = resultSet1.getInt(1);
            }

            preparedStatement2 = connection2.prepareStatement(sql2);
            preparedStatement2.setString(1,uaccount);
            resultSet2 = preparedStatement2.executeQuery();
            while(resultSet2.next()){
                j = resultSet2.getInt(1);
            }

            preparedStatement3 = connection3.prepareStatement(sql3);
            preparedStatement3.setString(1,uaccount);
            resultSet3 = preparedStatement3.executeQuery();
            while(resultSet3.next()){
                n = resultSet3.getInt(1);
            }

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,uaccount);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Users users = new Users();
                users.setUavatarurl(resultSet.getString(1));
                users.setUnickname(resultSet.getString(2));
                users.setUaccount(resultSet.getString(3));
                users.setUsignature(resultSet.getString(4));
                users.setUbackgroundurl(resultSet.getString(5));
                users.setPostsnum(i);
                users.setFfocusnum(j);
                users.setUfocusnum(n);

                list.add(users);
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
    public int logininfo(String uaccount, String upassword) {
        connection = ConnectDB.getConn();
        String sql = "select count(*) c from users where uaccount=? and upassword=? and ustate=1";
        int i = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, uaccount);
            preparedStatement.setString(2, upassword);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                i=resultSet.getInt("c");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(resultSet, preparedStatement, connection);
        }
        return i;
    }

    @Override
    public int register(String uaccount, String upassword, String unickname) {
        connection = ConnectDB.getConn();
        String sql = "INSERT INTO users (uavatarurl,uaccount,upassword,unickname,usignature,ubackgroundurl,upermissions,ustate) VALUE('http://paperfish233.3vkj.club/src/avatar.jpg',?,?,?,'这个人很懒,什么也没有留下。','http://paperfish233.3vkj.club/src/background.jpg',1,1)";
        int i = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, uaccount);
            preparedStatement.setString(2, upassword);
            preparedStatement.setString(3, unickname);
            i = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(null, preparedStatement, connection);
        }
        return i;
    }

    @Override
    public int selectuser(String uaccount) {
        connection = ConnectDB.getConn();
        String sql="select count(*) c from users where uaccount=?";
        int i = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,uaccount);
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
    public List<Users> getUserData(int pid) {
        int i = 0;
        int j = 0;
        int n = 0;
        String uaccount="";
        List<Users> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        connection1 = ConnectDB.getConn();
        connection2 = ConnectDB.getConn();
        connection3 = ConnectDB.getConn();
        connection4 = ConnectDB.getConn();
        String sql = "select a.uavatarurl,a.unickname,a.uaccount,a.usignature,a.ubackgroundurl from users a,posts b where a.uaccount=b.paccount and b.pid=?";
        String sql1 = "select count(*) c from posts where paccount=?";
        String sql2 = "select count(*) c from usersfocus where faccount=?";
        String sql3 = "select count(*) c from usersfocus where uaccount=?";
        String sql4 = "select paccount from posts where pid=?";
        try {
            preparedStatement4 = connection4.prepareStatement(sql4);
            preparedStatement4.setString(1, String.valueOf(pid));
            resultSet4 = preparedStatement4.executeQuery();
            while(resultSet4.next()){
                uaccount = resultSet4.getString(1);
            }

            preparedStatement1 = connection1.prepareStatement(sql1);
            preparedStatement1.setString(1,uaccount);
            resultSet1 = preparedStatement1.executeQuery();
            while(resultSet1.next()){
                i = resultSet1.getInt(1);
            }

            preparedStatement2 = connection2.prepareStatement(sql2);
            preparedStatement2.setString(1,uaccount);
            resultSet2 = preparedStatement2.executeQuery();
            while(resultSet2.next()){
                j = resultSet2.getInt(1);
            }

            preparedStatement3 = connection3.prepareStatement(sql3);
            preparedStatement3.setString(1,uaccount);
            resultSet3 = preparedStatement3.executeQuery();
            while(resultSet3.next()){
                n = resultSet3.getInt(1);
            }

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,pid);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Users users = new Users();
                users.setUavatarurl(resultSet.getString(1));
                users.setUnickname(resultSet.getString(2));
                users.setUaccount(resultSet.getString(3));
                users.setUsignature(resultSet.getString(4));
                users.setUbackgroundurl(resultSet.getString(5));
                users.setPostsnum(i);
                users.setFfocusnum(j);
                users.setUfocusnum(n);

                list.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(resultSet,preparedStatement,connection);
            ConnectDB.closeAll(resultSet1,preparedStatement1,connection1);
            ConnectDB.closeAll(resultSet2,preparedStatement2,connection2);
            ConnectDB.closeAll(resultSet3,preparedStatement3,connection3);
            ConnectDB.closeAll(resultSet4,preparedStatement4,connection4);
        }
        return list;
    }

    @Override
    public int updateUserBackgroundurl(String inputText, String uaccount) {
        connection = ConnectDB.getConn();
        String sql = "update users set ubackgroundurl=? where uaccount=?";
        int i = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, inputText);
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
    public int updateUserAvatarurl(String inputText, String uaccount) {
        connection = ConnectDB.getConn();
        String sql = "update users set uavatarurl=? where uaccount=?";
        int i = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, inputText);
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
    public int updateUserNickname(String inputText, String uaccount) {
        connection = ConnectDB.getConn();
        String sql = "update users set unickname=? where uaccount=?";
        int i = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, inputText);
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
    public int updateUserSignature(String inputText, String uaccount) {
        connection = ConnectDB.getConn();
        String sql = "update users set usignature=? where uaccount=?";
        int i = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, inputText);
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
    public int updateUserPassword(String inputText, String uaccount) {
        connection = ConnectDB.getConn();
        String sql = "update users set upassword=? where uaccount=?";
        int i = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, inputText);
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
    public List<Users> getFocusUserData(String uaccount) {
        List<Users> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        String sql = "select b.unickname,b.uavatarurl,b.uaccount from usersfocus a,users b where a.uaccount = b.uaccount and a.faccount = ? ORDER BY a.fid DESC";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,uaccount);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Users users = new Users();
                users.setUnickname(resultSet.getString(1));
                users.setUavatarurl(resultSet.getString(2));
                users.setUaccount(resultSet.getString(3));

                list.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(resultSet,preparedStatement,connection);
        }
        return list;
    }

    @Override
    public List<Users> getUserFocusData(String uaccount) {
        List<Users> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        String sql = "select b.unickname,b.uavatarurl,b.uaccount from usersfocus a,users b where a.faccount = b.uaccount and a.uaccount = ? ORDER BY a.fid DESC";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,uaccount);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Users users = new Users();
                users.setUnickname(resultSet.getString(1));
                users.setUavatarurl(resultSet.getString(2));
                users.setUaccount(resultSet.getString(3));

                list.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(resultSet,preparedStatement,connection);
        }
        return list;
    }

    @Override
    public List<Users> getUserByuaccountData(String uaccount) {
        int i = 0;
        int j = 0;
        int n = 0;
        List<Users> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        connection1 = ConnectDB.getConn();
        connection2 = ConnectDB.getConn();
        connection3 = ConnectDB.getConn();
        String sql = "select uavatarurl,unickname,uaccount,usignature,ubackgroundurl from users where uaccount=?";
        String sql1 = "select count(*) c from posts where paccount=?";
        String sql2 = "select count(*) c from usersfocus where faccount=?";
        String sql3 = "select count(*) c from usersfocus where uaccount=?";
        try {
            preparedStatement1 = connection1.prepareStatement(sql1);
            preparedStatement1.setString(1,uaccount);
            resultSet1 = preparedStatement1.executeQuery();
            while(resultSet1.next()){
                i = resultSet1.getInt(1);
            }

            preparedStatement2 = connection2.prepareStatement(sql2);
            preparedStatement2.setString(1,uaccount);
            resultSet2 = preparedStatement2.executeQuery();
            while(resultSet2.next()){
                j = resultSet2.getInt(1);
            }

            preparedStatement3 = connection3.prepareStatement(sql3);
            preparedStatement3.setString(1,uaccount);
            resultSet3 = preparedStatement3.executeQuery();
            while(resultSet3.next()){
                n = resultSet3.getInt(1);
            }

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,uaccount);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Users users = new Users();
                users.setUavatarurl(resultSet.getString(1));
                users.setUnickname(resultSet.getString(2));
                users.setUaccount(resultSet.getString(3));
                users.setUsignature(resultSet.getString(4));
                users.setUbackgroundurl(resultSet.getString(5));
                users.setPostsnum(i);
                users.setFfocusnum(j);
                users.setUfocusnum(n);

                list.add(users);
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
    public List<Users> getSearchUserData(String unickname) {
        List<Users> list = new ArrayList<>();
        connection = ConnectDB.getConn();

        String keyword = unickname; // 要搜索的关键字
        String pattern = "%" + keyword + "%"; // 根据关键字构造模式字符串
        String sql = "SELECT uaccount, unickname, uavatarurl " +
                "FROM users " +
                "WHERE unickname like ? ";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,pattern);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Users users = new Users();
                users.setUaccount(resultSet.getString(1));
                users.setUnickname(resultSet.getString(2));
                users.setUavatarurl(resultSet.getString(3));

                list.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(resultSet,preparedStatement,connection);
        }
        return list;
    }

    @Override
    public int loginAdmin(String uaccount, String upassword) {
        connection = ConnectDB.getConn();
        String sql = "select count(*) c from users where uaccount=? and upassword=? and ustate=1 and upermissions=2";
        int i = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, uaccount);
            preparedStatement.setString(2, upassword);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                i=resultSet.getInt("c");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(resultSet, preparedStatement, connection);
        }
        return i;
    }

    @Override
    public List<Users> getAllData() {
        List<Users> list = new ArrayList<>();
        connection = ConnectDB.getConn();
        String sql = "select * from users";
        int i=0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Users users = new Users();
                users.setUid(resultSet.getInt(1));
                users.setUaccount(resultSet.getString(2));
                users.setUpassword(resultSet.getString(3));
                users.setUnickname(resultSet.getString(4));
                users.setUsignature(resultSet.getString(5));
                users.setUbackgroundurl(resultSet.getString(6));
                users.setUavatarurl(resultSet.getString(7));
                users.setUpermissions(resultSet.getInt(8));
                users.setUstate(resultSet.getInt(9));

                list.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(resultSet,preparedStatement,connection);
        }
        return list;
    }

    @Override
    public int insertData(String uaccount, String upassword, String unickname, int upermissions) {
        connection = ConnectDB.getConn();
        String sql = "INSERT INTO users (uavatarurl,uaccount,upassword,unickname,usignature,ubackgroundurl,upermissions,ustate) VALUE('http://paperfish233.3vkj.club/src/avatar.jpg',?,?,?,'这个人很懒,什么也没有留下。','http://paperfish233.3vkj.club/src/background.jpg',?,1)";
        int i = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, uaccount);
            preparedStatement.setString(2, upassword);
            preparedStatement.setString(3, unickname);
            preparedStatement.setInt(4, upermissions);
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
        String sql="update users set ustate=2 where uid=?";
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
    public int updateData(int id, String uaccount, String upassword, String unickname, String usignature, String ubackgroundurl, String uavatarurl, String upermissions, String ustate) {
        connection = ConnectDB.getConn();
        String sql = "update users set uaccount=?,upassword=?,unickname=?,usignature=?,ubackgroundurl=?,uavatarurl=?,upermissions=?,ustate=? where uid=?";
        int i = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, uaccount);
            preparedStatement.setString(2, upassword);
            preparedStatement.setString(3, unickname);
            preparedStatement.setString(4, usignature);
            preparedStatement.setString(5, ubackgroundurl);
            preparedStatement.setString(6, uavatarurl);
            preparedStatement.setString(7, upermissions);
            preparedStatement.setString(8, ustate);
            preparedStatement.setInt(9, id);
            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeAll(null, preparedStatement, connection);
        }
        return i;
    }
}
