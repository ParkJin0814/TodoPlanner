package com.example.todoplanner.repository;

import com.example.todoplanner.dto.UserResponseDto;
import com.example.todoplanner.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcTemplateUserRepository implements UserRepository{
    private final JdbcTemplate jdbcTemplate;

    // 생성자
    public JdbcTemplateUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // jdbc 유저 테이블 정보 생성
    @Override
    public UserResponseDto saveUser(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("users").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", user.getName());
        parameters.put("email", user.getEmail());
        parameters.put("createAt", user.getCreateAt());
        parameters.put("updateAt", user.getUpdateAt());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new UserResponseDto(key.longValue(), user.getName(), user.getEmail(), user.getCreateAt(), user.getUpdateAt());
    }

    // user id로 Plan 데이터 찾기 없을경우 오류 반환
    @Override
    public User findUserByIdOrElseThrow(Long id) {
        List<User> result = jdbcTemplate.query("select * from users where id = ?", userRowMapperV2(), id);
        return result.stream()
                .findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist userId = " + id));
    }

    @Override
    public List<UserResponseDto> findAllUser() {
        return jdbcTemplate.query("select * from users", userRowMapper());
    }

    @Override
    public void updateUserName(Long id, String name) {
        jdbcTemplate.update("update users set name = ?, updateAt = ? where id = ?", name, LocalDateTime.now(), id);
    }

    @Override
    public void deleteUser(Long id) {
        // 유저관련 데이터 먼저 삭제
        jdbcTemplate.update("delete from plan where userId = ?", id);
        // 유저삭제
        jdbcTemplate.update("delete from users where id = ?", id);
    }

    // 쿼리 반환타입매서드
    private RowMapper<UserResponseDto> userRowMapper() {
        return new RowMapper<UserResponseDto>() {
            @Override
            public UserResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new UserResponseDto(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getTimestamp("createAt").toLocalDateTime(),
                        rs.getTimestamp("updateAt").toLocalDateTime()
                );
            }
        };
    }

    // 쿼리 반환타입매서드
    private RowMapper<User> userRowMapperV2() {
        return new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new User(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getTimestamp("createAt").toLocalDateTime(),
                        rs.getTimestamp("updateAt").toLocalDateTime()
                );
            }
        };
    }
}
