package com.nero.carupapi.model;

public class JWTResponse {
    private String jwt;
    private String userName;

    public JWTResponse() {
    }

    public JWTResponse(String jwt, String userName) {
        this.jwt = jwt;
        this.userName = userName;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
