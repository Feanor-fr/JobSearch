package kg.attractor.job_search.repository;

import kg.attractor.job_search.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Integer> {

    @Query("""
      select v from Vacancy as v where v.category.id = :categoryId
     """)
    List<Vacancy> findByCategoryId(Integer categoryId);


    @Query("""
      select v from Vacancy as v where v.author.email = :email
     """)
    List<Vacancy> findByAuthorEmail(String email);

    @Query("""
      select v from Vacancy as v where v.isActive = true 
     """)
    List<Vacancy> findAllActive();

    @Query("""
        SELECT DISTINCT v FROM Vacancy v
        JOIN v.respondedApplicants ra
        JOIN ra.resume r
        JOIN r.applicant u
        WHERE u.email = :email
    """)
    List<Vacancy> findAllUsersWhoResponded(String email);

}
