package kg.attractor.job_search.repository;

import kg.attractor.job_search.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Integer> {

    @Query("""
        select r from Resume r where r.category.id = :categoryId
    """)
    List<Resume> findByCategoryId(Integer categoryId);

    @Query("""
        select r from Resume r where r.applicant.email = :email
    """)
    List<Resume> findByAuthorEmail(String email);

}
