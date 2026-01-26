package kg.attractor.job_search.repository;

import kg.attractor.job_search.model.EducationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EducationInfoRepository extends JpaRepository<EducationInfo, Integer> {


    List<EducationInfo> findEducationInfosByResume_Id(Integer resumeId);

    @Query("""
        select e.resume.id from EducationInfo as e where e.id = :eduId
    """)
    Optional<Integer> findResumeIdByEduId(Integer eduId);
}
