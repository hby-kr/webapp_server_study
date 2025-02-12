package com.tj703.webapp_server_study.model2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class M15EmpDBConn {
    // 이미 L15SingletonDB로 만들어져서 불필요하지만, 연습삼아 다시 만듦

    // 필드로 만들지만 싱글톤이므로 모두 private
    private static Connection conn = null;
    private static final String URL = "jdbc:mysql://localhost:3306/employees";
    private static final String USER = "root";
    private static final String PASSWORD = "mysql";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    static {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    } // 이로써 자바가 메모리에 로딩될 때 이미 커넥션이 되어버린 상태가 된다.

    // 인위적으로 꺼서 연결이 끊어진 상태에, 호출하려고 메서드 만들기
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
      if (conn == null || conn.isClosed()) { // 없거나, 닫혔거나.
          Class.forName(DRIVER);
          conn = DriverManager.getConnection(URL, USER, PASSWORD);
      }
      return conn;
    };
}
