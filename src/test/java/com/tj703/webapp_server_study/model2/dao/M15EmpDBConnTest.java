package com.tj703.webapp_server_study.model2.dao;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class M15EmpDBConnTest {

    @Test
    void getConnection() throws SQLException, ClassNotFoundException {
        M15EmpDBConn.getConnection();
    }
}