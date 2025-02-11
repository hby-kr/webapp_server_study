package com.tj703.webapp_server_study.model2.dao;

import com.tj703.webapp_server_study.model2.dto.L17EmpDto;

import java.util.*;

public interface L16EmpDao {

    // 여기는 인터페이스. 메서드를 강제하는 곳,
    // 리스트, 상세, 수정, 삭제, 등록 -> 필요한 메서드 생각하기

    // 이름짓기의 규칙
    // find = SELECT  // by = WHERE // ID = pk값

    List<L17EmpDto> findAll() throws Exception;

    L17EmpDto findById(int empNo) throws Exception;

    int insert(L17EmpDto emp) throws Exception;

    int update(L17EmpDto emp) throws Exception;

    int deleteById(int empNo) throws Exception;


}
