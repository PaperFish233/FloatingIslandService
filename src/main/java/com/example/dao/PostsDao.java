package com.example.dao;

import com.example.entity.Posts;

import java.util.List;

public interface PostsDao {

    public List<Posts> getData();

    public int insertData(int tid, String uaccount, String pconnect, String pimageurl);

    public List<Posts> getMineData(String uaccount);

    public List<Posts> getMineCollectionData(String uaccount);

    public List<Posts> getMineCollectionUserPostsData(String uaccount);

    public List<Posts> getSearchPostsData(String keyword);

    public List<Posts> getUserPostsData(int pid);

    public int updateData(int pid, String uaccount, String pconnect, String pimageurl, int tid);

    public int deleteData(int pid, String uaccount);

    public List<Posts> getTidPostsData(int tid);

    public List<Posts> getRankingData();

    public List<Posts> getUserPostsByuaccountData(String uaccount);

    public List<Posts> getRankingCollectionData();

    public List<Posts> getRankingCommentData();

    public List<Posts> getDataAdmin();

    public int deleteDataAdmin(int id);
}
