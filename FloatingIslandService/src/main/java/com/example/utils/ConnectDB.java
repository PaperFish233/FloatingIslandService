package com.example.utils;

import java.sql.*;

public class ConnectDB {
    public static Connection conn = null;
    private static String DBUNAME = "root";
    private static String DBUPWD = "908520";
    //输入自己的mysql数据库的密码
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String URL = "jdbc:mysql://127.0.0.1:3306/floatingisland?"
            + "serverTimezone=UTC"
            + "&useUnicode=true&characterEncoding=utf-8"
            + "&zeroDateTimeBehavior=convertToNull"
            + "&useSSL=false"
            + "&allowPublicKeyRetrieval=true";

    public static Connection getConn() {

        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, DBUNAME, DBUPWD);
            if (conn != null) {

            } else {
                System.out.println("数据库连接失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;

    }

    public static void closeAll(ResultSet rs,PreparedStatement ps,Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
