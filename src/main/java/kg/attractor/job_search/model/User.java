package kg.attractor.job_search.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "USERS")
public class User {
    private String name;
    private String surname;
    private Integer age;

    @Id
    private String email;
    private String password;
    private String phoneNumber;
    private String avatar;

    @ManyToOne
    @JoinColumn(name = "ROLE")
    private  Role role;

    @OneToMany(mappedBy = "author")
    private List<Vacancy> vacancies = new ArrayList<>();

    @OneToMany(mappedBy = "applicant")
    private List<Resume> resumes = new ArrayList<>();
}
