package com.example.dao;

import com.example.entity.Posts;

import java.util.List;

public interface PostsDao {

    public List<Posts> getData();

    public int insertData(String pconnect, String pimageurl);

    public List<Posts> getMineData();

    public List<Posts> getCollectionData();
}
