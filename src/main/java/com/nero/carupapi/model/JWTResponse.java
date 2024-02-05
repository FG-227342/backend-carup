package com.nero.carupapi.model;

public class JWTResponse {
    private String jwt;
    private String userName;
    private Long idUsrMobile;

    public JWTResponse() {
    }

    public JWTResponse(String jwt, String userName, Long idUsrMobile) {
        this.jwt = jwt;
        this.userName = userName;
        this.idUsrMobile = idUsrMobile;
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

    public Long getIdUsrMobile() {
        return idUsrMobile;
    }

    public void setIdUsrMobile(Long idUsrMobile) {
        this.idUsrMobile = idUsrMobile;
    }
}
