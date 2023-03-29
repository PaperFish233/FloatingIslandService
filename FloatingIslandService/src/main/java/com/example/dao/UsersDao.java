package com.example.dao;

import com.example.entity.Users;

import java.util.List;

public interface UsersDao {
    public List<Users> getData(String uaccount);

}
