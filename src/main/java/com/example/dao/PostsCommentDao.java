package com.example.dao;

import com.example.entity.PostsComment;

import java.util.List;

public interface PostsCommentDao {

    public List<PostsComment> getData(int pid);

    public int insertData(int pid,String uaccount,String comment);

    public int selectData(int pid,String faccount,String uaccount);

    public int deleteData(int cid,String uaccount);

}
