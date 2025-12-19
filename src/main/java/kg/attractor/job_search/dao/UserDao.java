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
}
