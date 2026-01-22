package kg.attractor.job_search.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "WORK_EXPERIENCE_INFO")
public class WorkExperienceInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer years;
    private String companyName;
    private String position;
    private String responsibilities;

    @ManyToOne
    @JoinColumn(name = "RESUME_ID")
    private Resume resume;
}
