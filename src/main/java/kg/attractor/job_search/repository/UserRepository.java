package kg.attractor.job_search.repository;

import kg.attractor.job_search.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    List<User> findUserByName(String name);

    @Query("""
     select u from User as u where u.phoneNumber like concat('%', :phone, '%') 
    """)
    List<User> findUserByPhoneNumber(String phone);

    Page<User> findUsersByRole_RoleName(String roleRoleName, Pageable pageable);

    @Query("""
        select distinct u
        from User u
        join u.resumes r
        join r.respondedApplicants ra
        where ra.vacancy.id = :vacancyId
    """)
    List<User> findUsersWhoResponded(Integer vacancyId);
}
