package com.tj703.webapp_server_study.model2_service.dao;

import com.tj703.webapp_server_study.model2_service.dto.LoginLogDto;

import java.util.List;

public interface LoginLogDao {
    // 필요한 메서드 구상중

    // 로그인할 때마다 기록을 추가
    int insert (LoginLogDto dto) throws Exception;

    // 관리자가 유저가 로그인한 로그를 본다.
    List<LoginLogDto> findAll () throws Exception;

}
