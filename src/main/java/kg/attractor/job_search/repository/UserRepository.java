package kg.attractor.job_search.repository;

import kg.attractor.job_search.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findUserByEmail(String email);

    List<User> findUserByName(String name);

    List<User> findUsersByPhoneNumberContaining(String phoneNumber);

    Page<User> findUsersByRole_RoleName(String roleRoleName, Pageable pageable);

    @Query("""
        select distinct u
        from User u
        join u.resumes r
        join r.respondedApplicants ra
        where ra.vacancy.id = :vacancyId
    """)
    List<User> findUsersWhoResponded(Integer vacancyId);

    Optional<User> findByResetPasswordToken(String token);
}
