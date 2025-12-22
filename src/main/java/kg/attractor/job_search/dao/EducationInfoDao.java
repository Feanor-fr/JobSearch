package kg.attractor.job_search.dao;

import kg.attractor.job_search.model.EducationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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

    public void deleteAllByResumeId(Integer resumeId){
        String sql = "DELETE FROM EDUCATION_INFO WHERE resume_id = ?";
        jdbcTemplate.update(sql, resumeId);
    }

    public void deleteById(Integer id){
        String sql = "DELETE FROM EDUCATION_INFO WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }


}
