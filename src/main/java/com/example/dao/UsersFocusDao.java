package com.example.dao;

public interface UsersFocusDao {

    public int insertData(int pid,String uaccount);

    public int selectData(int pid,String uaccount);

    public int deleteData(int pid,String uaccount);

    public int insertByuaccountData(String faccount, String uaccount);

    public int selectByuaccountData(String faccount, String uaccount);

    public int deleteByuaccountData(String faccount, String uaccount);
}
