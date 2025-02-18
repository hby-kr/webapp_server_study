package com.tj703.webapp_server_study.model2_service.service;

import com.tj703.webapp_server_study.model2_service.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImpTest {

    @Test
    void login() throws Exception {
        System.out.println(new UserServiceImp().login("user2@example.com", "1234"));
    }

    @Test
    void signUp() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setEmail("user2123@example.com");
        userDto.setPassword("4321");
        System.out.println(new UserServiceImp().signUp(userDto));
    }

    @Test
    void modifyPw() throws Exception {
        int newPw= 123444;
        UserDto userDto = new UserDto();

    }
}