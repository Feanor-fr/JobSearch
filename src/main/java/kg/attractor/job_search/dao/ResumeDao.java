package kg.attractor.job_search.dao;

import kg.attractor.job_search.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ResumeDao {
    private final JdbcTemplate jdbcTemplate;

    public void save(Resume resume) {
        String sql = """
            INSERT INTO RESUMES
            (applicant, name, category_id, salary, is_active, created_date, update_date)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(
                sql,
                resume.getApplicant(),
                resume.getName(),
                resume.getCategoryId(),
                resume.getSalary(),
                resume.getIsActive(),
                resume.getCreatedDate(),
                resume.getUpdateDate()
        );
    }

    public Integer saveAndReturnId(Resume resume) {
        String sql = """
        INSERT INTO RESUMES
        (applicant, name, category_id, salary, is_active, created_date, update_date)
        VALUES (?, ?, ?, ?, ?, ?, ?)
    """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, resume.getApplicant());
            ps.setString(2, resume.getName());
            ps.setInt(3, resume.getCategoryId());
            ps.setDouble(4, resume.getSalary());
            ps.setBoolean(5, resume.getIsActive());
            ps.setObject(6, resume.getCreatedDate());
            ps.setObject(7, resume.getUpdateDate());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }


    public void deleteById(Integer id) {
        String sql = "DELETE FROM RESUMES WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void update(Integer id, Resume resume) {
        String sql = """
        UPDATE resumes
        SET
            applicant = ?,
            name = ?,
            category_id = ?,
            salary = ?,
            is_active = ?,
            update_date = ?
        WHERE id = ?
    """;

        jdbcTemplate.update(
                sql,
                resume.getApplicant(),
                resume.getName(),
                resume.getCategoryId(),
                resume.getSalary(),
                resume.getIsActive(),
                resume.getUpdateDate(),
                id
        );
    }



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

    public List<Resume> getAllResumes(){
        String sql = "SELECT * FROM RESUMES";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

    public Resume getResumeById(Integer id) {
        String sql = "SELECT * FROM RESUMES " +
                "where ID = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Resume.class), id);
    }
}
