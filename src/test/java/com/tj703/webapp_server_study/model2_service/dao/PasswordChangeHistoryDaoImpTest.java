package com.tj703.webapp_server_study.model2_service.dao;

import com.tj703.webapp_server_study.model2_service.dto.PasswordChangeHistoryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

class PasswordChangeHistoryDaoImpTest {
    // 준비
    private static Connection conn;
    private static PasswordChangeHistoryDao dao;
    @BeforeEach
    void setUp() throws Exception {
        conn = UserManagerDBConn.getConnection();
        dao = new PasswordChangeHistoryDaoImp(conn);
    }


    @Test
    void insert() throws Exception {
        PasswordChangeHistoryDto dto = new PasswordChangeHistoryDto();
        dto.setUserId(3);
        dto.setOldPassword("1234");
        int insert = dao.insert(dto);
        System.out.println(insert);

    }


    @Test
    void findByPwAndUserId() throws Exception {
        System.out.println(dao.findByPwAndUserId("1234", 2));
    }


    @Test
    void findByChangeAtAndUserId() throws Exception {

    // Java의 표준 날짜 및 시간 API

    // 1. Date
        Date date = new Date();
        System.out.println(date); // Tue Feb 18 12:43:53 KST 2025
        // 2025-2-18을 받고 싶은데?
        int y = date.getYear(); // 125
        int m = date.getMonth(); // 0~11 / +1 해줘야하고..
        int d = date.getDate(); //
        System.out.println(y + "-" + m + "-" + d);  // 125-1-18

    // SimpleDateFormat ; 문자열을 date로. date를 문자열로
    // SimpleDateFormat은 java.text 패키지에 속하는 클래스입니다. 이는 Java 1.1부터 제공
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(date));

        // date에서 6개월을 빼는 방법
        Date date1 = new Date(0);  // 밀리초가 0인 날짜
        System.out.println(sdf.format(date1)); // 1970-01-01
        System.out.println(date.getTime());  // 현재의 밀리초  1739850637406  / 1000=1초,
        // 6개월은 180일 * 24시간 * 60분 * 60초 * 1000밀리초 = 15552000000
        long sixM = 15552000000l;
        System.out.println(sixM); // 15552000000
        System.out.println(date.getTime() - sixM); // 1724299099350

        System.out.println(sdf.format(new Date(date.getTime()-sixM))); // 2024-08-22

    // 2.Calendar
        // 달력으로 계산해주는 클래스
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, -6);
        System.out.println(calendar.getTime()); // Thu Jul 18 12:58:19 KST 2024

    // 3.  LocalDate
        // 이 패키지는 Java 8에서 새로 도입되었으며, 이전의 java.util.Date와 java.util.Calendar보다 더 직관적
        LocalDate now = LocalDate.now();
        System.out.println(now); // 2025-02-18
        System.out.println(now.minusMonths(6)); // 2024-08-18

        String prev6month = now.minusMonths(6).toString();
        System.out.println(dao.findByChangeAtAndUserId(prev6month, 1));
    }

}
/*
java.util.Date: 날짜와 시간을 표현하는 기본 클래스, 직관적이지 않음. (Java 1.0) (1996)
java.util.Calendar: Date의 단점을 개선한 클래스, 시간대와 날짜 조작 기능 제공.  (Java 1.1) (1997)
java.time: Java 8에서 도입된 새로운 API, 더 직관적이고 유연하며 다양한 날짜/시간 연산을 지원.  (Java 8)(2014)

java.time에 포함된 주요 클래스:
    LocalDate, LocalTime, LocalDateTime: 날짜와 시간 관련 객체들.
    ZonedDateTime: 시간대까지 고려한 날짜/시간.
    Duration, Period: 시간 차이 계산.
    DateTimeFormatter: 날짜/시간을 포맷하거나 파싱하는 클래스.

1. 현재 날짜 얻기
    now(): 현재 날짜를 반환합니다.
2. 특정 날짜 생성
    of(int year, int month, int dayOfMonth): 특정 연, 월, 일로 LocalDate 객체를 생성합니다.
3. 날짜 정보 얻기
    getYear(): 연도를 반환합니다.
    getMonth(): 월을 반환합니다.
    getMonthValue(): 월을 숫자(1~12)로 반환합니다.
    getDayOfMonth(): 날짜의 일(day)을 반환합니다.
    getDayOfWeek(): 요일을 반환합니다.
    getDayOfYear(): 해당 날짜가 올해의 몇 번째 날인지를 반환합니다.
4. 날짜 계산 및 변경
    plusDays(long days): 지정된 일수를 더한 날짜를 반환합니다.
    minusDays(long days): 지정된 일수를 뺀 날짜를 반환합니다.
    plusMonths(long months): 지정된 개월 수를 더한 날짜를 반환합니다.
    minusMonths(long months): 지정된 개월 수를 뺀 날짜를 반환합니다.
    plusYears(long years): 지정된 년 수를 더한 날짜를 반환합니다.
    minusYears(long years): 지정된 년 수를 뺀 날짜를 반환합니다.
5. 날짜 비교
    isBefore(LocalDate other): 다른 날짜보다 이전인지 확인합니다.
    isAfter(LocalDate other): 다른 날짜보다 이후인지 확인합니다.
    isEqual(LocalDate other): 다른 날짜와 동일한지 확인합니다.
6. 윤년 계산
    isLeapYear(): 해당 연도가 윤년인지 확인합니다.
7. 날짜 포맷팅 및 파싱
    format(DateTimeFormatter formatter): 날짜를 지정된 포맷으로 문자열로 변환합니다.
    parse(CharSequence text, DateTimeFormatter formatter): 문자열을 LocalDate 객체로 변환합니다.
8. 다음 달의 첫 번째 날짜 또는 마지막 날짜 구하기
    withDayOfMonth(int dayOfMonth): 날짜를 특정 일로 변경합니다.
    lengthOfMonth(): 해당 월의 일 수를 반환합니다.
9. 연/월/일 단위로 변환
    atStartOfDay(): 해당 날짜의 시작 시간(00:00)을 LocalDateTime으로 반환합니다.



 */