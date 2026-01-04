package kg.attractor.job_search.dao;

import kg.attractor.job_search.model.ContactInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ContactInfoDao {
    private final JdbcTemplate jdbcTemplate;

    public void save(ContactInfo contactInfo, Integer resumeId){
        String sql = """ 
                INSERT INTO CONTACTS_INFO
                (TYPE_ID, RESUME_ID, CONTACT_VALUE)
                VALUES(?,?,?)
                """;

        jdbcTemplate.update(sql,
                contactInfo.getTypeId(),
                resumeId,
                contactInfo.getContactValue()
                );
    }

    public void update( Integer integer, ContactInfo contactInfo){
        String sql = """
                UPDATE CONTACTS_INFO
                SET
                TYPE_ID = ?,
                CONTACT_VALUE = ?
                WHERE ID = ?
                """;

        jdbcTemplate.update(sql,
                contactInfo.getTypeId(),
                contactInfo.getContactValue()
                );
    }

    public void deleteById(Integer id){
        String sql = "DELETE FROM CONTACTS_INFO WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Optional<ContactInfo> findById(Integer id) {
        String sql = "SELECT * FROM CONTACTS_INFO " +
                "WHERE ID = ?";

        ContactInfo ci = DataAccessUtils.singleResult(
                jdbcTemplate.query(
                        sql,
                        new BeanPropertyRowMapper<>(ContactInfo.class),
                        id));

        return Optional.ofNullable(ci);
    }

    public Optional<List<ContactInfo>> findByResumeId(Integer resumeId) {
        String sql = "SELECT * FROM CONTACTS_INFO " +
                "where RESUME_ID = ?";

        return Optional.ofNullable(jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ContactInfo.class), resumeId));
    }
}
