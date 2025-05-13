package com.example.todoplanner.repository;

import com.example.todoplanner.dto.PlanResponseDto;
import com.example.todoplanner.entity.Plan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcTemplatePlanRepository implements PlanRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplatePlanRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PlanResponseDto savePlan(Plan plan) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("plan").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", plan.getTitle());
        parameters.put("userId", plan.getUserId());
        parameters.put("content", plan.getContent());
        parameters.put("createAt", plan.getCreateAt());
        parameters.put("updateAt", plan.getUpdateAt());
        String name = jdbcTemplate.queryForObject("select name from users where id = ?", String.class, plan.getUserId());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new PlanResponseDto(key.longValue(), plan.getTitle(), plan.getContent(), plan.getCreateAt(), plan.getUpdateAt(), name);
    }

    @Override
    public List<PlanResponseDto> findAllPlans() {
        return jdbcTemplate.query("select p.id, p.title, p.content, p.createAt, p.updateAt, u.name  from plan p inner join users u on p.userId=u.id", planRowMapper());
    }

    private RowMapper<PlanResponseDto> planRowMapper() {
        return new RowMapper<PlanResponseDto>() {
            @Override
            public PlanResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new PlanResponseDto(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getTimestamp("createAt").toLocalDateTime(),
                        rs.getTimestamp("updateAt").toLocalDateTime(),
                        rs.getString("name")
                );
            }
        };
    }
}
