package com.example.dao;

import com.example.entity.Posts;
import com.example.entity.Topic;
import com.example.entity.Users;

import java.util.List;

public interface TopicDao {

    public List<Topic> getData();

    public List<Topic> getTidData(int tid);
}
