package com.example.entity;

public class Users {

    private int uid;
    private String uaccount;
    private String upassword;
    private String unickname;
    private String usignature;
    private String uavatarurl;
    private int upermissions;
    private int ustate;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUaccount() {
        return uaccount;
    }

    public void setUaccount(String uaccount) {
        this.uaccount = uaccount;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUnickname() {
        return unickname;
    }

    public void setUnickname(String unickname) {
        this.unickname = unickname;
    }

    public String getUsignature() {
        return usignature;
    }

    public void setUsignature(String usignature) {
        this.usignature = usignature;
    }

    public String getUavatarurl() {
        return uavatarurl;
    }

    public void setUavatarurl(String uavatarurl) {
        this.uavatarurl = uavatarurl;
    }

    public int getUpermissions() {
        return upermissions;
    }

    public void setUpermissions(int upermissions) {
        this.upermissions = upermissions;
    }

    public int getUstate() {
        return ustate;
    }

    public void setUstate(int ustate) {
        this.ustate = ustate;
    }
}
