package com.example.dao;

import com.example.entity.PostsComment;

import java.util.List;

public interface PostsCommentDao {

    public List<PostsComment> getData(int pid);

}
