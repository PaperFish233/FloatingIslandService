package com.example.dao;

import com.example.entity.Notice;

import java.util.List;

public interface NoticeDao {

    public List<Notice> getData();

    public List<Notice> getInfoData(int nid);

    public List<Notice> getAllData();

    public int insertData(String ncontent,String naccount,String nimageurl);

    public int deleteData(int id);

    public int updateData(int id,String ncontent,String naccount,String nimageurl);

}
