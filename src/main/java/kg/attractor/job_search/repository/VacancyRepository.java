package kg.attractor.job_search.repository;

import kg.attractor.job_search.model.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Integer> {

    List<Vacancy> findVacanciesByCategory_Id(Integer categoryId);

    Page<Vacancy> findVacanciesByAuthorEmail(String authorEmail, Pageable pageable);

    List<Vacancy> findVacanciesByIsActive(Boolean isActive);

    @Query("""
        SELECT DISTINCT v FROM Vacancy v
        JOIN v.respondedApplicants ra
        JOIN ra.resume r
        JOIN r.applicant u
        WHERE u.email = :email
    """)
    List<Vacancy> findAllUsersWhoResponded(String email);

    @Query("""
        SELECT v FROM Vacancy v
        LEFT JOIN v.respondedApplicants ra
        WHERE v.isActive = true
        GROUP BY v
        ORDER BY COUNT(ra) DESC
    """)
    Page<Vacancy> findAllOrderByResponsesDescAndIsActiveTrue(Pageable pageable);

    List<Vacancy> findTop5ByNameContainingIgnoreCaseAndIsActiveTrue(String name);

    Page<Vacancy> findAllByIsActiveTrue(Pageable pageable);
}
