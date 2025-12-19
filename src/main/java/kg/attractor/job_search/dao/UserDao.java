package kg.attractor.job_search.dao;

import kg.attractor.job_search.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void save(User user) {
        String sql = """
            INSERT INTO users
            (name, surname, age, email, password, phone_number, avatar, role)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(
                sql,
                user.getName(),
                user.getSurname(),
                user.getAge(),
                user.getEmail(),
                user.getPassword(),
                user.getPhoneNumber(),
                user.getAvatar(),
                user.getRole()
        );
    }

    public List<User> getUserByName(String name){
        String sql = "SELECT * FROM users WHERE name LIKE ?";

        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(User.class),
                "%" + name + "%"
        );

    }

    public List<User> getAllUsers(){
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> getUserByPhoneNumber(String phoneNumber){
        String sql = "SELECT * FROM users WHERE phone_number LIKE ?";


        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(User.class),
                "%" + phoneNumber + "%"
        );
    }


    public List<User> getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email LIKE ?";


        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(User.class),
                "%" + email + "%"
        );
    }

    public Boolean isUserAuthorized(String email) {
        String sql = "SELECT EXISTS(SELECT 1 FROM users WHERE email LIKE ?)";


        return Boolean.TRUE.equals(
                jdbcTemplate.queryForObject(sql, Boolean.class, "%" + email + "%")
        );
    }

    public List<User> getAllUsersWhoResponded(Integer vacancyId){
        String sql = "SELECT * FROM USERS as  u " +
                "inner join RESUMES as r on u.email = r.APPLICANT " +
                "inner join RESPONDED_APPLICANTS as rs on r.ID = rs.RESUME_ID " +
                "WHERE VACANCY_ID like ?";

        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(User.class),
                "%" + vacancyId + "%"
        );
    }
}
