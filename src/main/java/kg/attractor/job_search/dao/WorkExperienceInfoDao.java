package kg.attractor.job_search.dao;

import kg.attractor.job_search.model.WorkExperienceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WorkExperienceInfoDao {
    private final JdbcTemplate jdbcTemplate;

    public void save(WorkExperienceInfo workExperienceInfo, Integer resumeId){
        String sql = """ 
                 INSERT INTO WORK_EXPERIENCE_INFO 
                 (resume_id, years, company_name, position, responsibilities) 
                  VALUES (?,?,?,?,?)
               """;

        jdbcTemplate.update(sql,
                resumeId,
                workExperienceInfo.getYears(),
                workExperienceInfo.getCompanyName(),
                workExperienceInfo.getPosition(),
                workExperienceInfo.getResponsibilities());

    }

    public void update(Integer id, WorkExperienceInfo workExperienceInfo){
        String sql = """
                UPDATE WORK_EXPERIENCE_INFO
                SET
                years = ?,
                company_name = ?,
                position = ?,
                responsibilities = ?
                WHERE id = ?
                """;
        jdbcTemplate.update(sql,
                workExperienceInfo.getYears(),
                workExperienceInfo.getCompanyName(),
                workExperienceInfo.getPosition(),
                workExperienceInfo.getResponsibilities(),
                id
                );


    }

//    public void deleteAllByResumeId(Integer resumeId){
//        String sql = "DELETE FROM WORK_EXPERIENCE_INFO WHERE resume_id = ?";
//        jdbcTemplate.update(sql, resumeId);
//    }

    public void deleteById(Integer id){
        String sql = "DELETE FROM WORK_EXPERIENCE_INFO WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Optional<WorkExperienceInfo> findById(Integer id){
        String sql = "SELECT * FROM WORK_EXPERIENCE_INFO " +
                "where ID = ?";

        WorkExperienceInfo we = DataAccessUtils.singleResult(
                jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(WorkExperienceInfo.class),
                id));

        return Optional.ofNullable(we);
    }

    public List<WorkExperienceInfo> findByResumeId(Integer resumeId){
        String sql = "SELECT * FROM WORK_EXPERIENCE_INFO " +
                "where RESUME_ID = ?";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WorkExperienceInfo.class), resumeId);
    }


}
