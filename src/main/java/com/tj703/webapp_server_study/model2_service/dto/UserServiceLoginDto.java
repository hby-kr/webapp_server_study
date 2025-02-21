package com.tj703.webapp_server_study.model2_service.dto;

public class UserServiceLoginDto {
    // UserServiceImp의 login 메서드가 반환하는 방식인 Map<String Object> 대체 하기 위함
    // map.set("userDto", new UserDto());
    // map.set("isPwHistory", true); / 이거 대체하기 위함

    private UserDto user;  // 필드로 dto를 받을거임
    private boolean isPwHistory;

    @Override
    public String toString() {
        return "UserServiceLoginDto{" +
                "user=" + user +
                ", isPwHistory=" + isPwHistory +
                '}';
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public boolean isPwHistory() {
        return isPwHistory;
    }

    public void setPwHistory(boolean pwHistory) {
        isPwHistory = pwHistory;
    }
}
