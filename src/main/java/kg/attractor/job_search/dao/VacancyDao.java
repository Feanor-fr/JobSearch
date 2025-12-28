package kg.attractor.job_search.dao;

import kg.attractor.job_search.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VacancyDao {
    private final JdbcTemplate jdbcTemplate;

    public void save(Vacancy vacancy) {
        String sql = """
            INSERT INTO VACANCIES
            (name, description, category_id, salary, exp_from, exp_to,
             is_active, author, created_date, update_date)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(
                sql,
                vacancy.getName(),
                vacancy.getDescription(),
                vacancy.getCategoryId(),
                vacancy.getSalary(),
                vacancy.getExpFrom(),
                vacancy.getExpTo(),
                vacancy.getIsActive(),
                vacancy.getAuthor(),
                vacancy.getCreatedDate(),
                vacancy.getUpdateDate()
        );
    }

    public void update(Integer id, Vacancy vacancy) {
        String sql = """
            UPDATE vacancies
            SET
                name = ?,
                description = ?,
                category_id = ?,
                salary = ?,
                exp_from = ?,
                exp_to = ?,
                is_active = ?,
                author = ?,
                update_date = ?
            WHERE id = ?
        """;

        jdbcTemplate.update(
                sql,
                vacancy.getName(),
                vacancy.getDescription(),
                vacancy.getCategoryId(),
                vacancy.getSalary(),
                vacancy.getExpFrom(),
                vacancy.getExpTo(),
                vacancy.getIsActive(),
                vacancy.getAuthor(),
                vacancy.getUpdateDate(),
                id
        );
    }

    public void deleteById(Integer id) {
        String sql = "DELETE FROM VACANCIES WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Optional<Vacancy> getVacancyById(Integer id) {
        String sql = "SELECT * FROM VACANCIES " +
                "where ID = ?";

        Vacancy vacancy = DataAccessUtils.singleResult(
                jdbcTemplate.query(
                        sql,
                        new BeanPropertyRowMapper<>(Vacancy.class),
                        id));

        return Optional.ofNullable(vacancy);

    }

    public Optional<List<Vacancy>> getVacancyByCategoryId(Integer categoryId) {
        String sql = "SELECT * FROM VACANCIES WHERE category_id = ?";


        return Optional.ofNullable(jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Vacancy.class),
                categoryId
        ));
    }

    public List<Vacancy> getVacancyByAuthor(String applicant) {
        String sql = "SELECT * FROM VACANCIES WHERE AUTHOR = ?";


        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Vacancy.class),
                applicant
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

    public Optional<List<Vacancy>> getAllUsersWhoResponded(String email){
        String sql = "SELECT * FROM VACANCIES as  v " +
                "inner join RESPONDED_APPLICANTS as rs on  v.ID = rs.VACANCY_ID " +
                "inner join RESUMES as r on rs.RESUME_ID = r.ID " +
                "inner join USERS u on r.APPLICANT = u.EMAIL " +
                "WHERE u.EMAIL = ?";

        return Optional.ofNullable(jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Vacancy.class),
                email
        ));
    }
}
