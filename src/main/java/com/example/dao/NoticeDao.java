package com.example.dao;

import com.example.entity.Notice;

import java.util.List;

public interface NoticeDao {

    public List<Notice> getData();

    public List<Notice> getInfoData(int nid);

}
