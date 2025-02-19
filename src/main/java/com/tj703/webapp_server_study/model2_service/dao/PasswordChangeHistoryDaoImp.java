package com.tj703.webapp_server_study.model2_service.dao;

import com.tj703.webapp_server_study.model2_service.dto.PasswordChangeHistoryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PasswordChangeHistoryDaoImp implements PasswordChangeHistoryDao {

    // 준비
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public PasswordChangeHistoryDaoImp(Connection conn) {
        this.conn = conn;
    }

    @Override
    public int insert(PasswordChangeHistoryDto dto) throws Exception {
        int insert = 0;
        String sql = "insert into password_change_history (user_id, old_password) values (?,?)";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, dto.getUserId());
        ps.setString(2, dto.getOldPassword());
        insert = ps.executeUpdate();
        return insert;
    }

    // 수정할 때 같은 비밀번호가 있는지 묻는 메서드
    @Override
    public List<PasswordChangeHistoryDto> findByPwAndUserId(String pw, int UserId) throws Exception {
        List<PasswordChangeHistoryDto> list = null;
        String sql = "select * from password_change_history where user_id = ? and old_password = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, UserId);
        ps.setString(2, pw);
        rs = ps.executeQuery();
        list = new ArrayList<>();

        while (rs.next()) {
            PasswordChangeHistoryDto dto = new PasswordChangeHistoryDto();
            dto.setChangeId(rs.getInt("change_id"));
            dto.setUserId(rs.getInt("user_id"));
            dto.setOldPassword(rs.getString("old_password"));
            dto.setChangedAt(rs.getString("changed_at"));
            list.add(dto);
        }
        return list;
    }

    @Override
    public List<PasswordChangeHistoryDto> findByChangeAtAndUserId(String changeAt, int UserId) throws Exception {
        List<PasswordChangeHistoryDto> list = null;
        String sql = "select * from password_change_history where user_id = ? and changed_at <  ? ";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, UserId);
        ps.setString(2, changeAt);
        rs = ps.executeQuery();
        list = new ArrayList<>();

        while (rs.next()) {
            PasswordChangeHistoryDto dto = new PasswordChangeHistoryDto();
            dto.setChangeId(rs.getInt("change_id"));
            dto.setUserId(rs.getInt("user_id"));
            dto.setOldPassword(rs.getString("old_password"));
            dto.setChangedAt(rs.getString("changed_at"));
            list.add(dto);
        }
        return list;
    }
/*
-- 날짜를 숫자 취급해서 비교 (타입이 timestamp 일 때)
select * from password_change_history where user_id = 1
                                        and  changed_at < '2025-1-1';
-- 날짜를 문자열 취급해서 비교 (타입이 date, datetime 일 때)
select * from password_change_history where user_id = 1
                                        and changed_at like '2024%';
-- 물론 mysql은 모두 지원함.

select unix_timestamp();
SELECT UNIX_TIMESTAMP('2025-02-18 12:17:00');
SELECT FROM_UNIXTIME(1676700000);
select now();
select sysdate();
 */
}
