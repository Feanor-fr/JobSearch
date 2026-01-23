package kg.attractor.job_search.repository;

import kg.attractor.job_search.model.WorkExperienceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkExperienceInfoRepository extends JpaRepository<WorkExperienceInfo, Integer> {

    @Query("""
        select w from WorkExperienceInfo as w where w.resume.id = :resumeId
    """)
    List<WorkExperienceInfo> findAllByResume(Integer resumeId);

    @Query("""
        select w.resume.id from WorkExperienceInfo as w where w.id = :workId
    """)
    Optional<Integer> findResumeIdByWorkId(Integer workId);

}
