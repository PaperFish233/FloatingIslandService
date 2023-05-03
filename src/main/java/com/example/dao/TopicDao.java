package com.example.dao;

import com.example.entity.Topic;

import java.util.List;

public interface TopicDao {

    public List<Topic> getData();

    public List<Topic> getTidData(int tid);

    public int insertData(String tname,String timageurl,String tsignature);

    public int deleteData(int id);

    public int updateData(int id,String tname,String timageurl,String tsignature);

    public List<Topic> getNumData();
}
