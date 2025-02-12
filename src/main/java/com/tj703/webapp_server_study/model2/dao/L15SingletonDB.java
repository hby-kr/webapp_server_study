package com.tj703.webapp_server_study.model2.dao;

import java.sql.*;

public class L15SingletonDB {
    /*  디자인 패턴
    싱글톤(Singleton) 패턴은 객체 지향 설계에서 하나의 클래스에 대해 하나의 인스턴스만 존재하도록 보장하는 디자인 패턴
        인스턴스 객체를 하나 만들고 그것을 재사용하려는 목적.
     */

    private static Connection conn; // private으로 만들어서 접근할 수 없게 만들고
    private static final String URL = "jdbc:mysql://localhost:3306/employees";
    private static final String USER = "root";
    private static final String PASSWORD = "mysql";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    static { // 새로운 것!  컴파일시 static{}을 함수처럼 호출  // 컴파일 될 때 객체가 만들어지길 바란다면 이렇게 쓰면 됨
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD); // 객체를 만들기
        } catch (Exception e) {
            throw new RuntimeException(e);
            // 컴파일시에 실행된다는 것은 오류가 발생하면, 그 다음 코드들에 영향을 준다는 것.
            // 따라서 throw로 어플을 멈추는 것이 안정적이다.
        }
    } // 이로써 자바가 메모리에 로딩될 때 이미 커넥션이 되어버린 상태가 된다.


    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        // 싱글톤이기 때문에 필드가 private이지만, public 메서드를 만들어 return 할 수 있게 만듬.

        // 객체가 이미 만들어져 있으면 인스턴스 객체를 새로 만들지 않고 기존 객체를 반환하고,
        // 없거나 사용할 수 없는 상태면 인스턴스 객체를 만들어 대입 후 반환한다.

        if (conn == null || conn.isClosed()) { // 없거나, 닫혀있거나
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD); // 객체를 만들기
        }
        return conn;
    }

}
