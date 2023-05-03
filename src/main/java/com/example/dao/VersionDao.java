package com.example.dao;

import com.example.entity.Version;

import java.util.List;

public interface VersionDao {

    public List<Version> getData();

    public int insertData(String vnumber,String vupdatetitle,String vcontent,String vapkurl);

    public int deleteData(int id);

    public int updateData(int id,String vnumber,String vupdatetitle,String vcontent,String vapkurl);

    public List<Version> getNewData();
}
