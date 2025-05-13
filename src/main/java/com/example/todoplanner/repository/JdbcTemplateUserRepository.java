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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcTemplateUserRepository implements UserRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserResponseDto saveUser(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("users").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", user.getName());
        parameters.put("email", user.getEmail());
        parameters.put("password", user.getPassword());
        parameters.put("createAt", user.getCreateAt());
        parameters.put("updateAt", user.getUpdateAt());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new UserResponseDto(key.longValue(), user.getName(), user.getEmail(), user.getPassword(), user.getCreateAt(), user.getUpdateAt());
    }

    @Override
    public User userPlanByIdOrElseThrow(Long id) {
        List<User> result = jdbcTemplate.query("select * from users where id = ?", userRowMapper(), id);
        return result.stream()
                .findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist userId = " + id));
    }

    private RowMapper<User> userRowMapper() {
        return new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new User(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getTimestamp("createAt").toLocalDateTime(),
                        rs.getTimestamp("updateAt").toLocalDateTime()
                );
            }
        };
    }
}
