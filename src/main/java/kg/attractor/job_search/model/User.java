package kg.attractor.job_search.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "USERS")
public class User implements UserDetails {
    private String name;
    private String surname;
    private Integer age;

    @Id
    private String email;
    private String password;
    private String phoneNumber;
    private String avatar;
    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "ROLE")
    private  Role role;

    @OneToMany(mappedBy = "author")
    private List<Vacancy> vacancies = new ArrayList<>();

    @OneToMany(mappedBy = "applicant")
    private List<Resume> resumes = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getRoleName()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
