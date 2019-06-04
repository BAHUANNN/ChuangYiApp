package com.example.base.net.bean;

public class LoginBean {

    /**
     * token : string
     * uid : 0
     */

    private String token;
    private String username;

    public String getUsername(){return username;}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
