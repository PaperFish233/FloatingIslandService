package com.example.dao;

import com.example.entity.Posts;

import java.util.List;

public interface PostsDao {

    public List<Posts> getData();

    public int insertData(String pconnect, String pimageurl);

    public List<Posts> getMineData(String uaccount);

    public List<Posts> getMineCollectionData(String uaccount);

    public List<Posts> getMineCollectionUserPostsData(String uaccount);
}
