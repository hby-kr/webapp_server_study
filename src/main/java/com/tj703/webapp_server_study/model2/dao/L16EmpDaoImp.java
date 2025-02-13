package com.tj703.webapp_server_study.model2.dao;

import com.tj703.webapp_server_study.model2.dto.L17EmpDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*  model2를 구현해보자
 model 1 : 동적 페이지에서 접속하고 출력하는 모든 코드가 모여있는 디자인
          모든 비즈니스 로직과 뷰(출력) 코드가 한 곳에 모여 있는 디자인 패턴
 model 2 :  MVC (Model-View-Controller) 디자인 패턴  / Model = dao(=service) , View = html, Controller = Servlet    */


public class L16EmpDaoImp implements L16EmpDao {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public L16EmpDaoImp() throws Exception { // 생성자
        conn = L15SingletonDB.getConnection(); // 해당 클래스가 객체가 될 때, 무조건 필드conn이 생성됨
    }

    @Override
    public List<L17EmpDto> findAll() throws Exception {
        List<L17EmpDto> emplist = null;
        // 이름을 emps로 할 수도 있는데, -s붙일만한게 배열[]과 리스트가 있으니 명시적으로 list라고...

        String sql = "select * from employees limit 50";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery(); // 여기에서 50개 리스트가 다 들어옴.
        emplist = new ArrayList<>();

        while (rs.next()) { // 한줄한줄 있니? 물어보는 것
            L17EmpDto emp = new L17EmpDto();
            emp.setEmpno(rs.getInt("emp_no"));
            emp.setFirstname(rs.getString("first_name"));
            emp.setLastname(rs.getString("last_name"));
            emp.setBirthday(rs.getString("birth_date"));
            emp.setGender(rs.getString("gender"));
            emp.setHiredate(rs.getString("hire_date"));
            emplist.add(emp); // 다 받았으니 리스트에 넣기
        }
        return emplist;
    }

    @Override
    public L17EmpDto findById(Integer empNo) throws Exception {
        L17EmpDto findById = null;

        String sql = "select * from employees where emp_no = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, empNo);
        rs = ps.executeQuery();

        if (rs.next()) {
            findById = new L17EmpDto(); // 받을 공간 만들고.
            findById.setEmpno(rs.getInt("emp_no")); // 파싱중~~
            findById.setFirstname(rs.getString("first_name"));
            findById.setLastname(rs.getString("last_name"));
            findById.setBirthday(rs.getString("birth_date"));
            findById.setGender(rs.getString("gender"));
            findById.setHiredate(rs.getString("hire_date"));
        }
        return findById;

    }

    @Override
    public int insert(L17EmpDto emp) throws Exception {
        int insert = 0;
        String sql = "insert into employees (emp_no, birth_date, first_name, last_name, gender, hire_date)" +
                " values(?,?,?,?,?,?)";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, emp.getEmpno());
        ps.setString(2, emp.getBirthday());
        ps.setString(3, emp.getFirstname());
        ps.setString(4, emp.getLastname());
        ps.setString(5, emp.getGender());
        ps.setString(6, emp.getHiredate());
        insert = ps.executeUpdate(); // 성공하면 1
        return insert;
    }

    @Override
    public int update(L17EmpDto emp) throws Exception {
        int update = 0;
        String sql = "update employees SET birth_date=?, first_name=?, last_name=?, gender=?, hire_date=?  where emp_no = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, emp.getBirthday());
        ps.setString(2, emp.getFirstname());
        ps.setString(3, emp.getLastname());
        ps.setString(4, emp.getGender());
        ps.setString(5, emp.getHiredate());
        ps.setInt(6, emp.getEmpno());
        update = ps.executeUpdate();
        return update;
    }

    @Override
    public int deleteById(Integer empNo) throws Exception {
        int delete = 0;
        String sql = "delete from employees where emp_no = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, empNo);
        delete = ps.executeUpdate();

        return delete;
    }

    @Override
    public void close() {
        try {
            if (rs != null) { rs.close();}
            if (ps != null) { ps.close();}
            if (conn != null) { conn.close();}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
