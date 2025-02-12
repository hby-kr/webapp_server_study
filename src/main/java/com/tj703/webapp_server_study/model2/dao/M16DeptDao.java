package com.tj703.webapp_server_study.model2.dao;

import com.tj703.webapp_server_study.model2.dto.M17deptDto;

import java.util.List;

public interface M16DeptDao {

    List<M17deptDto> findAll() throws Exception;

    M17deptDto findById(String empNoStr) throws Exception;

    int insert(M17deptDto emp) throws Exception;

    int update(M17deptDto emp) throws Exception;

    int deleteById(String empNoStr) throws Exception;

    void close();
}