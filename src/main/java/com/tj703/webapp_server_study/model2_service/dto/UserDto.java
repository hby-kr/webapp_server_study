package com.tj703.webapp_server_study.model2_service.dto;

public class UserDto {
//    usermanagement.users.user_id
//    usermanagement.users.email
//    usermanagement.users.password
//    usermanagement.users.created_at
    // 필드 먼저 만들기
    private int userId;
    private String email;
    private String password;
    private String createdAt;

    @Override
    public String toString() {
        return "{" +
                "user_id=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", created_at='" + createdAt + '\'' +
                "}\n";
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
