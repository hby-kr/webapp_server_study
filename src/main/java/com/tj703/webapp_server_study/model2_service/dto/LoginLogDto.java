package com.tj703.webapp_server_study.model2_service.dto;

public class LoginLogDto {

    private int loginId;
    private int userId;
    private String loginTime;
    private String loginIp;
    private String userAgent;

    @Override
    public String toString() {
        return "{" +
                "loginId=" + loginId +
                ", userId=" + userId +
                ", loginTime='" + loginTime + '\'' +
                ", loginIp='" + loginIp + '\'' +
                ", userAgent='" + userAgent + '\'' +
                "}\n";
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

}
