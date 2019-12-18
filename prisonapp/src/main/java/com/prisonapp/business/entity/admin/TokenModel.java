package com.prisonapp.business.entity.admin;

public class TokenModel {
    private String token;
    private String tExpiresTime;
    private String refreshToken;
    private String rExpiresTime;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String gettExpiresTime() {
        return tExpiresTime;
    }

    public void settExpiresTime(String tExpiresTime) {
        this.tExpiresTime = tExpiresTime;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getrExpiresTime() {
        return rExpiresTime;
    }

    public void setrExpiresTime(String rExpiresTime) {
        this.rExpiresTime = rExpiresTime;
    }
}
