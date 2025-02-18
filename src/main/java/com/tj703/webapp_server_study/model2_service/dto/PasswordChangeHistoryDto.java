package com.tj703.webapp_server_study.model2_service.dto;

public class PasswordChangeHistoryDto {

    private int changeId;
    private int userId;
    private String oldPassword;
    private String changedAt;

    @Override
    public String toString() {
        return "{" +
                "changeId=" + changeId +
                ", userId=" + userId +
                ", oldPassword='" + oldPassword + '\'' +
                ", changedAt='" + changedAt + '\'' +
                "}\n";
    }

    public String getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(String changedAt) {
        this.changedAt = changedAt;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getChangeId() {
        return changeId;
    }

    public void setChangeId(int changeId) {
        this.changeId = changeId;
    }

}
