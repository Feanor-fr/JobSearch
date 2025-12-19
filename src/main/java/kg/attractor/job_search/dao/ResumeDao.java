package kg.attractor.job_search.dao;

import kg.attractor.job_search.model.Resume;
import kg.attractor.job_search.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ResumeDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Resume> getResumeByCategoryId(Integer categoryId) {
        String sql = "SELECT * FROM resumes WHERE category_id LIKE ?";


        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Resume.class),
                "%" + categoryId + "%"
        );
    }

    public List<Resume> getResumeByAuthor(String applicant) {
        String sql = "SELECT * FROM resumes WHERE applicant LIKE ?";


        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Resume.class),
                "%" + applicant + "%"
        );
    }
}
