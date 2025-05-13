package com.example.todoplanner.repository;

import com.example.todoplanner.dto.PageRequestDto;
import com.example.todoplanner.dto.PageResponseDto;
import com.example.todoplanner.dto.PlanResponseDto;
import com.example.todoplanner.dto.UserResponseDto;
import com.example.todoplanner.entity.Plan;
import com.example.todoplanner.entity.User;
import com.example.todoplanner.exception.PlanNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcTemplatePlanRepository implements PlanRepository {
    private final UserRepository userRepository;
    private final JdbcTemplate jdbcTemplate;

    // 생성자
    public JdbcTemplatePlanRepository(UserRepository userRepository, JdbcTemplate jdbcTemplate) {
        this.userRepository = userRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    // jdbc 일정테이블 객체 생성
    @Override
    public PlanResponseDto savePlan(Plan plan) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("plan").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", plan.getTitle());
        parameters.put("userId", plan.getUserId());
        parameters.put("password", plan.getPassword());
        parameters.put("content", plan.getContent());
        parameters.put("createAt", plan.getCreateAt());
        parameters.put("updateAt", plan.getUpdateAt());
        User user = userRepository.findUserByIdOrElseThrow(plan.getUserId());
        UserResponseDto userDto = new UserResponseDto(user);
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new PlanResponseDto(key.longValue(), plan.getTitle(), plan.getContent(), plan.getCreateAt(), plan.getUpdateAt(), userDto);
    }

    // jdbc 테이블 조회 및 PlanResponseDto 정보담기
    @Override
    public PageResponseDto findAllPlans(PageRequestDto dto) {
        Integer totalCount = jdbcTemplate.queryForObject("select count(*) from plan", Integer.class);
        List<PlanResponseDto> plans =
                jdbcTemplate.query("select p.id, p.userId, p.title, p.content, p.createat, p.updateat, u.name " +
                        "from plan p inner join users u on p.userid = u.id " +
                        "order by p.updateat desc " +
                        "limit ? offset ?", planRowMapper(), dto.getSize(), dto.getOffset());

        // PageResponseDto 반환
        return new PageResponseDto(plans, dto, totalCount);
    }

    @Override
    public PageResponseDto findPlanListUserByName(String name, PageRequestDto dto) {
        Integer totalCount = jdbcTemplate.queryForObject("select count(*) from plan p inner join users u on p.userId=u.id where u.name = ?", Integer.class, name);
        List<PlanResponseDto> plans =
                jdbcTemplate.query("select p.id, p.userId, p.title, p.content, p.createAt, p.updateAt, u.name  " +
                        "from plan p inner join users u on p.userId=u.id " +
                        "where u.name = ? " +
                        "order by p.updateAt desc " +
                        "limit ? offset ?", planRowMapper(), name, dto.getSize(), dto.getOffset());

        return new PageResponseDto(plans, dto, totalCount);
    }

    @Override
    public PageResponseDto findPlanListUserByUpdateAt(LocalDate updateAt, PageRequestDto dto) {
        Integer totalCount = jdbcTemplate.queryForObject("select count(*) from plan where date (updateAt) = ?", Integer.class, updateAt);
        List<PlanResponseDto> plans =
                jdbcTemplate.query("select p.id, p.userId, p.title, p.content, p.createAt, p.updateAt, u.name  " +
                        "from plan p inner join users u on p.userId=u.id " +
                        "where DATE (p.updateAt) = ? " +
                        "order by p.updateAt desc " +
                        "limit ? offset ?", planRowMapper(), updateAt, dto.getSize(), dto.getOffset());

        return new PageResponseDto(plans, dto, totalCount);
    }

    // plan id로 Plan 데이터 찾기 없을경우 오류 반환
    @Override
    public Plan findPlanByIdOrElseThrow(Long id) {
        List<Plan> result = jdbcTemplate.query("select * from plan where id = ?", planRowMapperV2(), id);
        return result.stream()
                .findAny()
                .orElseThrow(() -> new PlanNotFoundException(id));
    }

    @Override
    public void updatePlanContent(Long id, String content) {
        jdbcTemplate.update("update plan set  content = ?, updateAt = ? where id = ?", content, LocalDateTime.now(), id);
    }

    @Override
    public void deletePlan(Long id) {
        jdbcTemplate.update("delete from plan where id = ?", id);
    }

    // 쿼리 반환타입매서드
    private RowMapper<PlanResponseDto> planRowMapper() {
        return new RowMapper<PlanResponseDto>() {
            @Override
            public PlanResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = userRepository.findUserByIdOrElseThrow(rs.getLong("userId"));
                UserResponseDto userDto = new UserResponseDto(user);
                return new PlanResponseDto(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getTimestamp("createAt").toLocalDateTime(),
                        rs.getTimestamp("updateAt").toLocalDateTime(),
                        userDto
                );
            }
        };
    }

    // 쿼리 반환타입매서드
    private RowMapper<Plan> planRowMapperV2() {
        return new RowMapper<Plan>() {
            @Override
            public Plan mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Plan(
                        rs.getLong("id"),
                        rs.getLong("userId"),
                        rs.getString("password"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getTimestamp("createAt").toLocalDateTime(),
                        rs.getTimestamp("updateAt").toLocalDateTime()
                );
            }
        };
    }
}
