package kg.attractor.job_search.repository;

import kg.attractor.job_search.model.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfo, Integer> {

    @Query("""
            select c from ContactInfo c where c.resume.id = :resumeId
     """)
    List<ContactInfo> findAllByResumeId(Integer resumeId);

    @Query("""
        select c.resume.id from ContactInfo c where c.id = :contactId
    """)
    Optional<Integer> findResumeIdByContactInfoId(Integer contactId);
}
