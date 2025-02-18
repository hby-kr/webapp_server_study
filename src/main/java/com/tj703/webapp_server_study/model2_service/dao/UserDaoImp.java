package com.tj703.webapp_server_study.model2_service.dao;

import com.tj703.webapp_server_study.model2_service.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImp implements UserDao {

    // 필드
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    // 생성자
    public UserDaoImp(Connection conn) {
        // 1. 여기서 커낵션 바로 생성하기. 직접생성
        // conn = UserManagerConn.getConnection();

        // 2. (직접생성 안하고) 사용하는 쪽에서 만들어져 전달하기
        // 이유: 접속 후에 여러 쿼리를 실행하기 위해. 트랜젝션을 구현하기 위해
        this.conn = conn;
    }


    // 매서드 구현
    @Override
    public UserDto findByUserIdAndPassword(int id, String pw) throws Exception {

        UserDto user = null;
        String sql = "select * from users where user_id = ? and password = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setString(2, pw);
        rs = ps.executeQuery();

        if (rs.next()) {
            user = new UserDto();
            user.setUserId(rs.getInt("userid"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setCreatedAt(rs.getString("created_at"));
        }
        return user;
    }


    @Override
    public UserDto findByemailAndPassword(String email, String password) throws Exception {

        UserDto user = null;
        String sql = "select * from users where email = ? and password = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, password);
        rs = ps.executeQuery();

        if (rs.next()) {
            user = new UserDto();
            user.setUserId(rs.getInt("user_id"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setCreatedAt(rs.getString("created_at"));
        }
        return user;
    }

    @Override
    public UserDto findByEmail(String email) throws Exception {
        UserDto user = null;
        String sql = "select * from users where email=?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        rs = ps.executeQuery();
        if (rs.next()) {
            user = new UserDto();
            user.setUserId(rs.getInt("user_id"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setCreatedAt(rs.getString("created_at"));
        }
        return user;
    }

    @Override
    public int insert(UserDto user) throws Exception {

        int insert = 0;
        String sql = "INSERT INTO users (password, email) VALUES (?, ?)";
        ps = conn.prepareStatement(sql);
        ps.setString(1, user.getPassword());
        ps.setString(2, user.getEmail());
        insert = ps.executeUpdate();
        return insert;
    }

    @Override
    public int updateSetPasswordByEmail(UserDto user) throws Exception {
        int update = 0;
        String sql = "UPDATE users SET password = ? WHERE email = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, user.getPassword());
        ps.setString(2, user.getEmail());
        update = ps.executeUpdate();
        return update;
    }
}
