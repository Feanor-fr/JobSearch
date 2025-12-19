package kg.attractor.job_search.dao;

import kg.attractor.job_search.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VacancyDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Vacancy> getVacancyByCategoryId(Integer categoryId) {
        String sql = "SELECT * FROM VACANCIES WHERE category_id LIKE ?";


        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Vacancy.class),
                "%" + categoryId + "%"
        );
    }

    public List<Vacancy> getVacancyByAuthor(String applicant) {
        String sql = "SELECT * FROM VACANCIES WHERE AUTHOR LIKE ?";


        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Vacancy.class),
                "%" + applicant + "%"
        );
    }

    public List<Vacancy> getAllVacancies(){
        String sql = "SELECT * FROM VACANCIES";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public List<Vacancy> getAllActiveVacancies(){
        String sql = "SELECT * FROM VACANCIES where IS_ACTIVE = TRUE";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }
}
