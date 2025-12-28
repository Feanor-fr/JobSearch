package kg.attractor.job_search.dao;

import kg.attractor.job_search.model.EducationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EducationInfoDao {
    private final JdbcTemplate jdbcTemplate;

    public void save( EducationInfo educationInfo, Integer resumeId){
        String sql = """
                 INSERT INTO EDUCATION_INFO
                 (resume_id, institution, program, start_date, end_date, degree)
                  VALUES (?,?,?,?,?, ?)
                """;
        jdbcTemplate.update(sql,
                resumeId,
                educationInfo.getInstitution(),
                educationInfo.getProgram(),
                educationInfo.getStartDate(),
                educationInfo.getEndDate(),
                educationInfo.getDegree()
        );
    }

    public void update(Integer id, EducationInfo educationInfo){
        String sql = """
                UPDATE EDUCATION_INFO
                SET
                institution = ?,
                program = ?,
                start_date = ?,
                end_date = ?,
                degree = ?
                WHERE id = ?
                """;

        jdbcTemplate.update(sql,
                educationInfo.getInstitution(),
                educationInfo.getProgram(),
                educationInfo.getStartDate(),
                educationInfo.getEndDate(),
                educationInfo.getDegree(),
                id);
    }

//    public void deleteAllByResumeId(Integer resumeId){
//        String sql = "DELETE FROM EDUCATION_INFO WHERE resume_id = ?";
//        jdbcTemplate.update(sql, resumeId);
//    }

    public void deleteById(Integer id){
        String sql = "DELETE FROM EDUCATION_INFO WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Optional<EducationInfo> findById(Integer id) {
        String sql = "SELECT * FROM EDUCATION_INFO " +
                "WHERE ID = ?";

        EducationInfo ed = DataAccessUtils.singleResult(
                jdbcTemplate.query(
                        sql,
                        new BeanPropertyRowMapper<>(EducationInfo.class),
                        id));

           return Optional.ofNullable(ed);
    }

    public Optional<List<EducationInfo>> findByResumeId(Integer resumeId) {
        String sql = "SELECT * FROM EDUCATION_INFO " +
                "where RESUME_ID = ?";

           return Optional.ofNullable(jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EducationInfo.class), resumeId));
    }


}
