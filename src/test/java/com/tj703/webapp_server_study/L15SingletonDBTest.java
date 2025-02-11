package com.tj703.webapp_server_study;

import com.tj703.webapp_server_study.model2.dao.L15SingletonDB;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class L15SingletonDBTest {
    // TDD, Test Driven Development, 테스트 주도 개발.
    // Dao 작업을 할 때, Junit를 가지고 TDD로 작업을 했음.
    // Junit,(jupiter엔진을 가지고) 즉 단위별로 테스트 하는 도구.


    // 테스트 주도 개발(TDD): 코드 작성 전에 테스트 케이스를 먼저 작성하고, 이를 통과하는 코드를 작성하는 방식입니다.
    @Test // 메서드 단위로 각각 실행할 수 있음. -> 단위 테스트 unit text -> java에서 하는 유닛테스트 -> junit
    void getConnection() throws SQLException, ClassNotFoundException {
        L15SingletonDB.getConnection();
    }
}
/*
JUnit 테스트는 코드가 의도한 대로 동작하는지 자동으로 확인하는 과정입니다.
    이를 통해 코드의 신뢰성을 높이고, 자동화된 테스트를 통해 지속적인 검증을 할 수 있습니다.

디버깅은 코드가 실행되는 동안 문제를 추적하고 수정하는 과정입니다.
    코드 실행 중에 발생하는 문제를 라인별로 분석할 수 있습니다.
 */