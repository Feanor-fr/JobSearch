package kg.attractor.job_search.dao;

import kg.attractor.job_search.model.ContactType;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ContactTypesDao {
    private final JdbcTemplate jdbcTemplate;

    public Optional<ContactType> findById(Integer id) {
        String sql = "SELECT * FROM CONTACT_TYPES " +
                "WHERE ID = ?";

        ContactType ct = DataAccessUtils.singleResult(
                jdbcTemplate.query(
                        sql,
                        new BeanPropertyRowMapper<>(ContactType.class),
                        id));

        return Optional.ofNullable(ct);
    }
}
