package kg.attractor.job_search.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RespondedApplicantDao {
    private final JdbcTemplate jdbcTemplate;

    public void create(Integer vacancyId, Integer resumeId){
        String sql = """
            INSERT INTO responded_applicants (resume_id, vacancy_id, confirmation)
            VALUES (?, ?, false)
        """;

        jdbcTemplate.update(sql, resumeId, vacancyId);
    }
}
