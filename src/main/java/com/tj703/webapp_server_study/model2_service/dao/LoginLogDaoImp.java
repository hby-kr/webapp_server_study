package com.tj703.webapp_server_study.model2_service.dao;

import com.mysql.cj.xdevapi.SessionFactory;
import com.tj703.webapp_server_study.model2_service.dto.LoginLogDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LoginLogDaoImp implements LoginLogDao {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public LoginLogDaoImp(Connection conn) {
        this.conn = conn;
    }

    @Override
    public int insert(LoginLogDto dto) throws Exception {
        int insert = 0;
        String sql = "insert into login_logs (user_id, ip_address, user_agent) values (?, ?, ?)";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, dto.getUserId());
        ps.setString(2, dto.getIpAddress());
        ps.setString(3, dto.getUserAgent());
        insert = ps.executeUpdate();
        return insert;
    }

    @Override
    public List<LoginLogDto> findAll() throws Exception {
        List<LoginLogDto> findAll = null;
        String sql = "select * from login_logs limit 50";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        findAll = new ArrayList<>();
        while (rs.next()) {
            LoginLogDto dto = new LoginLogDto();
            dto.setLoginId(rs.getInt("log_id"));
            dto.setUserId(rs.getInt("user_id"));
            dto.setLoginTime(rs.getString("login_time"));
            dto.setIpAddress(String.valueOf(rs.getBytes("ip_address")));
            dto.setUserAgent(String.valueOf(rs.getBytes("user_agent")));
            findAll.add(dto);
        }

        return findAll;
    }
}
