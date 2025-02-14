package com.tj703.webapp_server_study.model2_service.dao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerConnTest {

    @Test
    void getConnection() throws Exception {
        assertNotNull(UserManagerDBConn.getConnection());
    }
}