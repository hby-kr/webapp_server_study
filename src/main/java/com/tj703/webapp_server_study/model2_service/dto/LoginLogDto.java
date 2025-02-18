package com.tj703.webapp_server_study.model2_service.dto;

public class LoginLogDto {

    private int logId;
    private int userId;
    private String loginTime;
    private String ipAddress;
    private String userAgent;

    @Override
    public String toString() {
        return "{" +
                "logId=" + logId +
                ", userId=" + userId +
                ", loginTime='" + loginTime + '\'' +
                ", loginIp='" + ipAddress + '\'' +
                ", userAgent='" + userAgent + '\'' +
                "}\n";
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
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
        return logId;
    }

    public void setLoginId(int loginId) {
        this.logId = loginId;
    }

}
