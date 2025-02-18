package com.tj703.webapp_server_study.model2_service.dao;

import com.tj703.webapp_server_study.model2_service.dto.LoginLogDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class LoginLogDaoImpTest {

    private static Connection conn;

    @BeforeEach  // 모든 함수마다 이전에 실행
    // cf. @BeforeAll  // 모든 함수 시작 전 이전에 한번만 시작해라. (static필드로 해놓아야함)
    void setUpBeforeClass() throws Exception {
        conn = UserManagerDBConn.getConnection();
    }
/*
@BeforeEach, @BeforeAll, @AfterEach, @AfterAll 어노테이션은 테스트의 실행 순서를 제어하기 위한 방법
    @BeforeEach : 각 테스트 메소드가 실행되기 전에 매번 실행되는 메소드에 적용
    @BeforeAll : 테스트 클래스 내의 모든 테스트 메소드가 실행되기 전에 단 한 번만 실행. 이 메소드는 static이어야 합니다.
    @AfterEach : 각 테스트 메소드가 실행된 후 매번 실행되는 메소드에 적용
    @AfterAll : 테스트 클래스 내의 모든 테스트 메소드가 실행된 후 단 한 번만 실행. 이 메소드는 static이어야 합니다.
 */

    @Test
    void insert() throws Exception {
        LoginLogDto dto = new LoginLogDto();
        dto.setUserId(1);
        dto.setIpAddress("127.0.0.1");
        dto.setUserAgent("Mozilla/5.0");
        System.out.println(new LoginLogDaoImp(conn).insert(dto));
    }

    @Test
    void findAll() throws Exception {
        System.out.println(new LoginLogDaoImp(conn).findAll());
    }
}