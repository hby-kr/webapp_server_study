package com.tj703.webapp_server_study.model2.dao;

import com.tj703.webapp_server_study.model2.dto.M17deptDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.class)
class M16DeptDaoImpTest {

    @Test
    @Order(4)
    void findAll() throws Exception {
        System.out.println(new M16DeptDaoImp().findAll());
    }

    @Test
    @Order(2)
    void findById() throws Exception {
        System.out.println(new M16DeptDaoImp().findById("d999"));
    }

    @Test
    @Order(1)
    void insert() throws Exception {
        // 넣을 객체 하나 만들기
        M17deptDto deptDto = new M17deptDto();
        deptDto.setDeptNoStr("d999");
        deptDto.setDeptName("테스트부서");

        // 만든 객체 넣기
        int insert = new M16DeptDaoImp().insert(deptDto);

        // 검증
        assertTrue(insert > 0);
    }

    @Test
    @Order(3)
    void update() throws Exception {
        // 업데이트 할 객체 하나 만들기
        M17deptDto deptDto = new M17deptDto();
        deptDto.setDeptNoStr("d999");
        deptDto.setDeptName("테스트부서(수정!!!!!!)");
        // 객체 넣기
        int update = new M16DeptDaoImp().update(deptDto);
        // 검증
        assertTrue(update > 0);
    }

    @Test
    @Order(5)
    void deleteById() throws Exception {
        int delete = new M16DeptDaoImp().deleteById("d999");
        System.out.println(delete);
        // assertTrue(delete > 0);
    }

}