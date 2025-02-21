package com.tj703.webapp_server_study.model2_service.service;

import com.tj703.webapp_server_study.model2_service.dto.LoginLogDto;
import com.tj703.webapp_server_study.model2_service.dto.UserDto;
import com.tj703.webapp_server_study.model2_service.dto.UserServiceLoginDto;

import java.util.Map;

public interface UserService {

    // 1. Map으로 구현하기
    Map<String, Object> login(String email, String password, String ip, String agent) throws Exception;

    // Map으로 반환하는거 별로라서 다시 오버로딩
    UserServiceLoginDto login(UserDto user, LoginLogDto loginLog) throws Exception;
    // session 수업하면서 다른 것 필요해서 다시 만듬

    // 위에 login은 평문, 아래 로그인은 해쉬값이 들어온다.
    UserServiceLoginDto autoLogin(UserDto user, LoginLogDto loginLog) throws Exception;


    // 회원가입
    // 0. 이전 패스워드 중복 있는지?
    // 1. 회원등록
    // 2. 회원 패스워드 수정 이력 등록 -> boolean 또는 eunm을 반환
    // enum SIGNUPSTATES 예시. (SUCCESS, PREV_PW, PW_HISTORY_ERROR  ... 상태를 만들어서 enum값을 반환)
    boolean signUp(UserDto user) throws Exception;


    // 회원 비번 수정
    // 0. 이전 패스워드 중복 있는지?
    // 1. 회원 비번 수정
    // 2. 회원 패스워드 수정 이력 등록 -> boolean 반환

    boolean modifyPw(UserDto user) throws Exception;

/*
설계부터 하고 구현하기 위해서 인터페이스 생성
서비스를 설계하면 DAO가 보임.
    1. DTO 정의
        DTO 클래스 설계 및 필드 정의, 유효성 검증 추가.
    2. 서비스 인터페이스 기획
        비즈니스 로직을 담은 서비스 인터페이스 정의, 서비스 계층에서 수행할 핵심 작업 구상.
    3. DAO 기획 및 설계
        데이터베이스 설계, 필요한 CRUD 메서드 기획, 트랜잭션 관리 계획.
    4. DAO 구현 및 단위/통합 테스트
        DAO 메서드 구현, 단위 테스트 및 통합 테스트 작성.
    5. 서비스 구현
        DAO와 서비스 연결, 비즈니스 로직 구현, 예외 처리, 트랜잭션 처리, 응답 구조 설계.

 서비스의 경우에는 서비스의 함수이름은 DB쿼리보다는 서비스에 가깝게 작명하는 관습이 있음.
 DAO의 경우, findAll, findById

ex)
 로그인 뭐할건지 구상:
 1. db에 저장된 유저 정보와 email, pw가 동일한 것이 있는지 조회
 2. login_log 저장
 3. pw history 조회(6개월 전) -> true:비번변경 유도,  false:로그인 진행  -> 가져할 데이터 id와 pw에서 각 가져와야 함.
 보내야할 데이터가 복수일 때, 데이터를 보내는 방법
      1. list, Map
      2. UserDto에 boolean isPwHistroy 필드를 정의하는 것 (한쪽에 자리를 더 만든다) (권장하지 않음)
      3. UserVo(Beans)를 따로 만들어서 UserDto 필드 + isPwHistroy 정의


DTO (Data Transfer Object)와 VO (Value Object)는
    둘 다 객체 지향 프로그래밍에서 데이터 전송 및 관리를 위한 디자인 "패턴"임. 즉 개발자가 만드는 방식의 차이를 말하는 것.
    그 목적과 사용 방식이 다릅니다. Dto는 db의 값을 그대로 가져오는 것, Vos는 그 안에 입맛에 맞는 메서드를 구현할 수 있음

DTO는
    여러 정보를 한 번에 다른 시스템이나 계층에 전달하고 싶을 때 사용.
    데이터를 전송하는 데 사용됨. 변경 가능: 객체의 내용을 수정할 수 있음. == setter메서드를 만듬

VO는
    값을 나타내는 객체입니다. 중요한 건, VO는 불변(immutable)이라는 점입니다. 즉, 한 번 만들어지면 그 값은 변경되지 않음.
    불변: 값이 한 번 설정되면 변경되지 않음.
    == setter메서드를 안만듬, getter만 만듬
    == 필드를 final로 선언하여, (생성자에서) 객체 생성 시 한 번만 값을 설정할 수 있게 함
    때문에 VO는 동등성 비교를 할 때 그 값이 같으면 동일한 객체로 취급.
ex.
[dto] 사용자가 상품을 주문해서 주문의 상태, 상품 목록, 결제 정보 등을 서버에서 클라이언트로 전달할 때, 여러 정보를 한 번에 묶어서 전달하려면 DTO를 사용
[vo] Money VO는 값이 바뀌지 않도록 설계되기 때문에, 한 번 생성된 후에는 변경할 수 없습니다.
로그인 과정에서는 주로 사용자 정보나 인증에 관련된 데이터를 다루게 되는데, 이때 VO를 활용하면 불변성과 값의 동등성을 유지하는 데 유용할 수 있습니다.
 */

}
