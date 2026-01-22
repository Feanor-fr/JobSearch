package kg.attractor.job_search.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RESPONDED_APPLICANTS")
public class RespondedApplicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean confirmation;

    @ManyToOne
    @JoinColumn(name = "RESUME_ID")
    private Resume resume;

    @ManyToOne
    @JoinColumn(name = "VACANCY_ID")
    private Vacancy vacancy;

    @OneToMany(mappedBy = "respondedApplicant")
    private List<Message> messages = new ArrayList<>();

}
