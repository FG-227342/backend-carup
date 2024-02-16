package com.nero.carupapi.model;

public class JWTResponse {
    private String jwt;
    private String userName;
    private Long idUsrMobile;

    private String mail;
    private Integer idUsuario;

    public JWTResponse() {
    }

    public JWTResponse(String jwt, String userName, Long idUsrMobile, String mail, Integer idUsuario) {
        this.jwt = jwt;
        this.userName = userName;
        this.idUsrMobile = idUsrMobile;
        this.mail = mail;
        this.idUsuario = idUsuario;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getId() {
        return idUsuario;
    }

    public void setId(Integer id) {
        this.idUsuario = id;
    }
}
