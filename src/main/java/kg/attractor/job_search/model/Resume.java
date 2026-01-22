package kg.attractor.job_search.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
    private Integer categoryId;
    private Double salary;
    private Boolean isActive;
    private LocalDate createdDate;
    private LocalDate updateDate;

    @ManyToOne
    @JoinColumn(name = "APPLICANT")
    private User applicant;
}
