package kg.attractor.job_search.dao;

import kg.attractor.job_search.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    public void update(String email, User user) {
        String sql = """
        UPDATE USERS
        SET
            name = ?,
            surname = ?,
            age = ?,
            password = ?,
            phone_number = ?,
            avatar = ?,
            role = ?
        WHERE email = ?
    """;

        jdbcTemplate.update(
                sql,
                user.getName(),
                user.getSurname(),
                user.getAge(),
                user.getPassword(),
                user.getPhoneNumber(),
                user.getAvatar(),
                user.getRole(),
                email
        );
    }

    public Optional<List<User>> getUserByName(String name){
        String sql = "SELECT * FROM users WHERE name like ?";

        List<User> users = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(User.class),
                "%" + name + "%"
        );

        return Optional.ofNullable(users);

    }

    public List<User> getAllUsers(){
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public Optional<List<User>> getUserByPhoneNumber(String phoneNumber){
        String sql = "SELECT * FROM users WHERE phone_number LIKE ?";

        List<User> users = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(User.class),
                "%" + phoneNumber + "%"
        );

        return Optional.ofNullable(users);
    }


    public Optional<List<User>> getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";


        return Optional.ofNullable(jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(User.class),
                email
        ));
    }

    public Boolean isUserAuthorized(String email) {
        String sql = "SELECT EXISTS(SELECT 1 FROM users WHERE email = ?)";

        return Boolean.TRUE.equals(
                jdbcTemplate.queryForObject(sql, Boolean.class,email)
        );
    }

    public Optional<List<User>> getAllUsersWhoResponded(Integer vacancyId){
        String sql = "SELECT * FROM USERS as  u " +
                "inner join RESUMES as r on u.email = r.APPLICANT " +
                "inner join RESPONDED_APPLICANTS as rs on r.ID = rs.RESUME_ID " +
                "WHERE VACANCY_ID = ?";

        return Optional.ofNullable(jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(User.class),
                vacancyId
        ));
    }

    public Optional<User> findOneUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";

            User user = DataAccessUtils.singleResult(jdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(User.class),
                    email
            ));

            return Optional.ofNullable(user);
    }
}
