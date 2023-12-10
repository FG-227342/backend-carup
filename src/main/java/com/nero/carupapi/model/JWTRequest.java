package com.nero.carupapi.model;

public class JWTRequest {
    private String username;
    private String password;
    private String playerId;

    public JWTRequest() {
    }

    public JWTRequest(String username, String password, String playerId) {
        this.username = username;
        this.password = password;
        this.playerId = playerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
}
