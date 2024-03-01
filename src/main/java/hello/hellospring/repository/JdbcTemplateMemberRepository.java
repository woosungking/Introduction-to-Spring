package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Repository
public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);
        return result.stream().findAny();
    }
    @Override
    public List<Member> findAll() {

        return jdbcTemplate.query("select * from member", memberRowMapper());
    }

    @Override
    public void clean() {

    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
        return result.stream().findAny();
    }
    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        }; }
}



//rs와 rowNum은 RowMapper 인터페이스의 mapRow 메서드의 매개변수입니다.
//
//rs: ResultSet의 약어로, 데이터베이스 쿼리 결과의 한 행을 나타냅니다. 이 객체를 사용하여 결과 세트의 열에서 데이터를 추출합니다. ResultSet는 데이터베이스로부터 가져온 데이터를 포함하고 있으며, 각 열에 대한 데이터를 가져올 수 있는 메서드를 제공합니다. 코드에서는 rs.getLong("id")와 rs.getString("name")와 같이 사용되었습니다.
//
//rowNum: 현재 행의 인덱스를 나타냅니다. RowMapper가 호출될 때마다 증가하며, 각 행의 번호를 나타냅니다. 주로 순서대로 데이터베이스 결과를 처리할 때 유용합니다. mapRow 메서드 내에서 사용될 필요가 없는 경우 무시해도 됩니다.