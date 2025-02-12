package com.tj703.webapp_server_study.model2.dao;

import com.tj703.webapp_server_study.model2.dto.M17deptDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class M16DeptDaoImp implements M16DeptDao {

    // 필드 먼저 만들기
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    // 생성자 만들어지면서 바로 연결되도록.
    public M16DeptDaoImp() throws SQLException, ClassNotFoundException {
        conn = M15EmpDBConn.getConnection();
    }

    // 이제 연결되어있(다고 가정하고 있)으니
    // 인터페이스에서 강제한 메서드 구현하기
    @Override
    public List<M17deptDto> findAll() throws Exception {
        List<M17deptDto> deptList = null;

        String sql = "select * from departments";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        deptList = new ArrayList<>();

        while (rs.next()) {
            M17deptDto dto = new M17deptDto();
            dto.setDeptNoStr(rs.getString("dept_no"));
            dto.setDeptName(rs.getString("dept_name"));
            deptList.add(dto);
        }
        return deptList;
    }

    @Override
    public M17deptDto findById(String empNoStr) throws Exception {
        M17deptDto findById = null;
        String sql = "select * from departments where dept_no = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, empNoStr);
        rs = ps.executeQuery();

        while (rs.next()) {
            findById = new M17deptDto();
            findById.setDeptNoStr(rs.getString("dept_no"));
            findById.setDeptName(rs.getString("dept_name"));
        }
        return findById;
    }

    @Override
    public int insert(M17deptDto emp) throws Exception {
        int insert = 0;
        String sql = "insert into departments (dept_no, dept_name) values (?, ?)";
        ps = conn.prepareStatement(sql);
        ps.setString(1, emp.getDeptNoStr());
        ps.setString(2, emp.getDeptName());
        insert = ps.executeUpdate();
        return insert;
    }

    @Override
    public int update(M17deptDto emp) throws Exception {
        int update = 0;
        String sql = "update departments set dept_name=? where dept_no=?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, emp.getDeptName());
        ps.setString(2, emp.getDeptNoStr());
        update = ps.executeUpdate();
        return update;
    }

    @Override
    public int deleteById(String empNoStr) throws Exception {
        int delete = 0;
        String sql = "delete from departments where dept_no = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, empNoStr);
        delete = ps.executeUpdate();
        return delete;
    }

    @Override
    public void close() {
        try  {
            if (rs != null) {rs.close();}
            if (ps != null) {ps.close();}
            if (conn != null) {conn.close();}
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
