package com.tj703.webapp_server_study.model2_service.dao;

import com.tj703.webapp_server_study.model2_service.dto.PasswordChangeHistoryDto;

import java.util.List;

public interface PasswordChangeHistoryDao {

    // 추가 메서드
    int insert(PasswordChangeHistoryDto dto) throws Exception;

    // 시간 조회 메서드
    // 오늘날짜 - 6개월 전
    List<PasswordChangeHistoryDto> findByChangeAtAndUserId(String changeAt, int id) throws Exception;

    // 비번 중복 체크 메서드
    List<PasswordChangeHistoryDto> findByPwAndUserId(String pw, int id) throws Exception;


}
