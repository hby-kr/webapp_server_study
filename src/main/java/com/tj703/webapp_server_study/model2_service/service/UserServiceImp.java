package com.tj703.webapp_server_study.model2_service.service;

import com.tj703.webapp_server_study.model2_service.dao.*;
import com.tj703.webapp_server_study.model2_service.dto.LoginLogDto;
import com.tj703.webapp_server_study.model2_service.dto.PasswordChangeHistoryDto;
import com.tj703.webapp_server_study.model2_service.dto.UserDto;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImp implements UserService {

    private Connection conn;

    // 사용할 모든 dao 불러오기
    private UserDao userDao;
    private PasswordChangeHistoryDao pwHistoryDao;
    private LoginLogDao loginLogDao;
    // 위 dao가 내부에 생성하는 PreparedStatement, ResultSet를 close할 수 없는 상황임
    // 이것이 객체지향언어의 단점.
    // 객체지향언어(OOP)는 순서대로 동작하고, 사용하는 쪽에 꼭 객체를 매개변수로 사용해야햔다. ==> 서비스로직의 상대적으로 더 복잡해진다.(복잡성이 커진다)
    // 객체를 필요로 하는 곳에서 항상 생성해야 한다.  ==> 네트워크 상황에서 1만개의 동일한 요청이 와도, 객체를 1만개 만들어야 함.

    // AOP, Aspect-Oriented Programming
    // 관점지향 프로그래밍(spring) 서비스 로직 관점에서 객체생성
    // spring은 자바기반이지만 별개의 구조인 관점지향언어임
    // AOP는 공통 관심사(기능)를 관점(aspect)으로 분리하고, 이를 핵심 비즈니스 로직에 적용하는 방식
    // 공통적인 기능(로깅, 보안, 트랜잭션 관리 등)을 핵심 비즈니스 로직과 분리하여 독립적으로 관리하고 적용할 수 있도록 하는 방식.
    // 객체를 컨테이너가 따로 미리 생성하고, 사용하는 곳에 전달하는 구조. (==싱글톤 패턴 같은 원리)
    // 컨테이너는 AOP에서 적용해야 할 기능(예: 로깅, 트랜잭션 처리 등)을 자동으로 설정하고 관리해주는 상자 같은 것.
    // 1. 매개변수(파라미터) 없이 동작한다.
    // 2. 만들어진 것이 있으면 같은 요청이 왔을 때 전달. 하나 만들어서 1만개 요청 처리
    // 3. 무엇이든 서비스 로직을 기준으로 개발이 가능 (개발이 편해짐)

    public UserServiceImp() throws Exception {
        conn = UserManagerDBConn.getConnection();
        userDao = new UserDaoImp(conn);
        loginLogDao = new LoginLogDaoImp(conn);
        pwHistoryDao = new PasswordChangeHistoryDaoImp(conn);
    }

    @Override
    public Map<String, Object> login(String email, String password) throws Exception {
        Map<String, Object> login = new HashMap<>();

        try {
            conn.setAutoCommit(false); // 쿼리를 실행할때마다 각 코드가 독립성을 가지기 때문에, 오토커밋 끔.
            conn.commit(); // 세이브 포인트 만듬
            //conn.setSavepoint("1") 더 구분할 수도 있음.

            UserDto user = userDao.findByemailAndPassword(email, password);

            LoginLogDto loginLog = new LoginLogDto();
            loginLog.setUserId(user.getUserId());
            loginLog.setIpAddress("127.0.0.1");
            loginLog.setUserAgent("Mozilla");

            int insert = loginLogDao.insert(loginLog);

            LocalDate now = LocalDate.now();
            String prev6Month = now.minusMonths(6).toString();
            List<PasswordChangeHistoryDto> pwList = pwHistoryDao.findByChangeAtAndUserId(prev6Month, user.getUserId());

            // Map 구조 만들기
            login.put("user", user);
            login.put("isPwHistory", (pwList.size() > 0)); // null이어야 6개월 이내에 바꾼 것임. null이 아니면 비번변경해야.
            login.put("isInsertLog", insert > 0);

            conn.commit(); // 안하면 등록한 내역이 휘발됨

        } catch (RuntimeException e) {
            conn.rollback(); // 오류나면, 커밋지점으로 되돌려라. -> 원자성을 유지하기 -> 트랜젝션
            // conn.rollback("1"); 특정 포인트 지점으로 보내겠다.
            throw new RuntimeException(e); // 사용하는 쪽에서 오류를 발생
        } finally {
            if (conn != null) {
                conn.close();
            }
            // PreparedStatement, ResultSet를 close할 수 없는 상황이 벌어짐.
        }
        return login;

    }


    @Override
    public boolean signUp(UserDto user) throws Exception {
        boolean signUp = false;

        try {
            conn.setAutoCommit(false);
            conn.commit();

            // signUp(UserDto user) user {email:"sdfsa", pw:"1234}  즉 user_id는 자동값이어서 없음
            int insert = userDao.insert(user);
            // auto increment로 생성된 id를 어떻게 다시 불러올 수 있을 것인가?
            //    1. select last_insert_id()를 조회하는 것.
            //    2. 그냥 등록된 유저를 다시 조회하는 것
            user = userDao.findByemailAndPassword(user.getEmail(), user.getPassword()); // 다시 찾음
            PasswordChangeHistoryDto pwHistoryDto = new PasswordChangeHistoryDto();
            pwHistoryDto.setUserId(user.getUserId());
            pwHistoryDto.setOldPassword(user.getPassword());
            int pwInsert = pwHistoryDao.insert(pwHistoryDto);

            signUp = (pwInsert > 0 && insert > 0);

            conn.commit(); // 안하면 등록한 내역이 휘발됨

        } catch (RuntimeException e) {
            conn.rollback();
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return signUp;
    }


    @Override
    // 세션에서 회원 이메일 가져오고, 폼문에서 입력받은 newPW 받아서, 새로운 dto 만들어서 newPw가 인수로 들어온다고 가정.
    public boolean modifyPw(UserDto user) throws Exception {
        boolean modifyPw = false;

        try {
            conn.setAutoCommit(false);
            conn.commit();
            String newPw = user.getPassword();
            user = userDao.findByEmail(user.getEmail());
            // user_id로 find 메서드를 만들어놔서 이 짓을 하는 것임
            List<PasswordChangeHistoryDto> pwList = pwHistoryDao.findByPwAndUserId(newPw, user.getUserId());

            // pwList.size > 0 면, 이전 비번과 중복상태
            if (pwList.size() == 0) {
                int update = userDao.updateSetPasswordByEmail(user);
                PasswordChangeHistoryDto pswHistoryDto = new PasswordChangeHistoryDto();
                pswHistoryDto.setUserId(user.getUserId());
                pswHistoryDto.setOldPassword(user.getPassword());
                int pwInsert = pwHistoryDao.insert(pswHistoryDto);
                modifyPw = (pwInsert > 0 && update > 0);
            }
            conn.commit();
        } catch (RuntimeException e) {
            conn.rollback();
            throw new RuntimeException(e);
        } finally {
            if (conn != null) { conn.close();}
        }
        return modifyPw;
    }
}

/*
Spring은 자바 기반으로 동작하며, 어노테이션(annotation)을 주로 사용하여 다양한 기능을 설정하고 관리합니다.
이 어노테이션은 Java의 표준 기능을 확장하는 방식으로 Spring의 핵심 기능을 수행합니다.

Spring의 핵심 개념들

1. IoC (Inversion of Control, 제어의 역전)
IoC는 "누가 책임질지"를 Spring이 대신 관리해 준다는 개념입니다.
예를 들어, 우리가 집을 꾸미고 싶으면, 필요한 물건들을 직접 사서 배치하잖아요?
하지만 IoC는 마치 인테리어 전문가가 우리가 원하는 스타일에 맞게 모든 가구와 장식을 자동으로 배치해 주는 것처럼,
Spring이 필요한 객체들을 만들어서 자동으로 배치해주는 방식입니다.

2. DI (Dependency Injection, 의존성 주입)
DI는 어떤 클래스가 다른 클래스를 사용해야 할 때 (의존성) 그 클래스를 Spring이 자동으로 주입해 준다는 개념입니다.

3. AOP (Aspect-Oriented Programming, 관점 지향 프로그래밍)
AOP는 여러 기능을 가진 애플리케이션에서 공통적인 기능(예: 로깅, 보안 체크 등)을 한 곳에 집중시켜 관리하는 방식입니다.
모든 수업에 일일이 넣는 대신, 출석 체크 담당 선생님을 따로 두고, 그 선생님이 모든 수업에 출석 체크를 하게 만드는 방식

4. Bean Life Cycle (빈 생명 주기)
Spring에서는 객체(빈)가 생성되고, 초기화되며, 소멸되는 과정이 있습니다. 이 과정을 빈 생명 주기라고 하죠.
Spring은 빈의 생명 주기를 자동으로 관리해주기 때문에, 개발자는 필요한 시점에만 관심을 가지면 됩니다.

5. Spring Boot
Spring Boot는 Spring을 빠르고 쉽게 사용할 수 있게 해주는 도구입니다.
Spring을 사용하는 데 필요한 복잡한 설정들을 자동으로 처리해 주고,
내장 서버를 제공해서 개발자가 바로 애플리케이션을 실행할 수 있도록 해 줍니다.

 */