package com.tj703.webapp_server_study.model2_service.dao;

import com.tj703.webapp_server_study.model2_service.dto.UserDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImpTest {

    private static Connection conn;
    private static UserDao userDao;

    @BeforeAll
    static void setUp() throws Exception {
        conn = UserManagerDBConn.getConnection();
        userDao = new UserDaoImp(conn);
    }

    @Test
    void findByUserIdAndPassword() throws Exception {
        System.out.println(userDao.findByUserIdAndPassword(1, "1234"));

    }

    @Test
    void insert() throws Exception {

        UserDto user = new UserDto();
        user.setPassword("1234");
        user.setEmail("inserttest@gmail.com");
        System.out.println(userDao.insert(user));
    }

    @Test
    void findByemailAndPassword() throws Exception {

        assertNotNull(userDao.findByemailAndPassword("inserttest@gmail.com", "1234"));
    }

    @Test
    void updateSetPasswordByEmail() throws Exception {
        UserDto user = new UserDto();
        user.setPassword("1111");
        user.setEmail("inserttest@gmail.com");
        System.out.println(userDao.updateSetPasswordByEmail(user));
    }
}