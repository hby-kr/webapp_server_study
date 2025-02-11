package com.tj703.webapp_server_study;

import com.tj703.webapp_server_study.model2.dao.L16EmpDaoImp;
import com.tj703.webapp_server_study.model2.dto.L17EmpDto;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

// jnuit은 클래스 실행버튼을 누르면 내부의 모든 메서드가 동시에 실행하게 되어있음. (멀티스테드)
// 순서를 정해서 실행하고 싶다면 TestMethodOrder를 사용하면 된다.
// @TestMethodOrder = "나 순서대로 실행하거다! 선언"
// 그리고 각 메서드에 @Order(순서) 라고 적으면 됨.
// +tip. delete를 맨 마지막 순서에 넣어서, 반복 테스트 할 수 있게 함.
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class L16EmpDaoImpTest {
    // TDD, Test Driven Development, 테스트 주도 개발.
    // Dao 작업을 할 때, Junit를 가지고 TDD로 작업을 했음.
    // Junit,(jupiter엔진을 가지고) 즉 단위별로 테스트 하는 도구.


    // 테스트 주도 개발(TDD): 코드 작성 전에 테스트 케이스를 먼저 작성하고, 이를 통과하는 코드를 작성하는 방식입니다.
    @Test // 메서드 단위로 각각 실행할 수 있음. -> 단위 테스트 unit text -> java에서 하는 유닛테스트 -> junit
    @Order(4)
    void findAll() throws Exception {
        System.out.println(new L16EmpDaoImp().findAll());
    }

    @Test
    @Order(2)
    void findById() throws Exception {
        System.out.println(new L16EmpDaoImp().findById(9999));
    }

    @Test
    @Order(1)
    void insert() throws Exception {
        // 넣을 dto 객체 하나 만들기
        L17EmpDto emp = new L17EmpDto();
        emp.setEmpno(9999);
        emp.setBirthday("2025-02-11");
        emp.setGender("F");
        emp.setFirstname("kim");
        emp.setLastname("lisi");
        emp.setHiredate("2025-02-12");

        // 만든 더미 dto 객체 넣기
        int insert = new L16EmpDaoImp().insert(emp);
        System.out.println(insert);

        // assert 사용해보기
        assertTrue(insert > 0); // 조건이 참일 때만 junit 성공이야.
        // Java에서 assert는 주로 디버깅을 위해 프로그래머가 만든 가정을 테스트하는 데 사용되는 키워드
        // assert는 조건이 거짓일 경우 AssertionError를 발생시키며, 이는 코드에서 예상되는 조건이 충족되지 않았을 때 이를 알리기 위한 방법
        //      assertTrue(condition);           조건이 true일 경우 테스트가 성공합니다.
        //      assertFalse(condition);          조건이 false일 경우 테스트가 성공합니다.
        //      assertEquals(expected, actual);          예상 값과 실제 값이 같지 않으면 실패합니다.
        //      assertNotEquals(unexpected, actual);          예상하지 않은 값과 실제 값이 같으면 실패합니다.
        //      assertNull(object);          객체가 null이어야만 성공합니다.
        //      assertNotNull(object);          객체가 null이 아니어야만 성공합니다.
        //      assertSame(expected, actual);          두 객체가 같은 객체여야만 성공합니다.
        //      assertNotSame(expected, actual);          두 객체가 같은 객체가 아니어야만 성공합니다.
        //      assertArrayEquals(expected, actual);          두 배열이 동일해야만 성공합니다.
        //      각각의 메서드는 조건이 맞지 않으면 AssertionError를 던지며, 이를 통해 테스트가 실패했다고 알 수 있습니다!
    }

    @Test
    @Order(3)
    void update() throws Exception {
        // 넣을 dto 객체 하나 만들기
        L17EmpDto emp = new L17EmpDto();
        emp.setEmpno(9999);
        emp.setBirthday("1988-02-11");
        emp.setGender("F");
        emp.setFirstname("lee");
        emp.setLastname("asdfbsdagfs");
        emp.setHiredate("2025-02-12");
        // 만든 더미 dto 객체 넣기
        int update = new L16EmpDaoImp().update(emp);
        // asert 사용해보기
        assert update > 0 : "성공";
    }

    @Test
    @Order(5)
    void deleteById() throws Exception {
        int delete = new L16EmpDaoImp().deleteById(9999);
        System.out.println(delete);
        assertTrue(delete > 0);
    }
}