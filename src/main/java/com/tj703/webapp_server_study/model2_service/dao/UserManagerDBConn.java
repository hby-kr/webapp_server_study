package com.tj703.webapp_server_study.model2_service.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class UserManagerDBConn {

    private static String URL = "jdbc:mysql://localhost:3306/UserManagement";
    private static final String USER = "usermanager";
    private static final String PASS = "mysql";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    // 싱글톤패턴
    private static Connection conn = null;

    static {
        try {
            Class.forName(DRIVER); // 동적로딩 (어떤 객체가 나중에 생성되도록 지정해놓는 것. + 내가 언제 해봤는지 얘기하면 굳)
            conn = DriverManager.getConnection(URL, USER, PASS);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws Exception {
        if (conn == null || conn.isClosed()) {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASS);
        }
        return conn;
    }

}
