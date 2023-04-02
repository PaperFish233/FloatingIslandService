package com.example.dao;

public interface PostsLikeDao {

    public int insertData(int pid,String uaccount);

    public int selectData(int pid,String uaccount);

    public int deleteData(int pid,String uaccount);
}
