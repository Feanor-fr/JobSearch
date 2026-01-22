package kg.attractor.job_search.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "RESUMES")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Double salary;
    private Boolean isActive;
    private LocalDate createdDate;
    private LocalDate updateDate;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "APPLICANT")
    private User applicant;

    @OneToMany(mappedBy = "resume")
    private List<ContactInfo> contactInfos = new ArrayList<>();

    @OneToMany(mappedBy = "resume")
    private List<WorkExperienceInfo> workExperienceInfos = new ArrayList<>();

    @OneToMany(mappedBy = "resume")
    private List<EducationInfo> educationInfos = new ArrayList<>();

    @OneToMany(mappedBy = "resume")
    private List<RespondedApplicant> respondedApplicants = new ArrayList<>();

}
