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
@Table(name = "VACANCIES")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private Double salary;
    private Integer expFrom;
    private Integer expTo;
    private Boolean isActive;
    private LocalDate createdDate;
    private LocalDate updateDate;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "AUTHOR")
    private User author;

    @OneToMany(mappedBy = "vacancy")
    private List<RespondedApplicant> respondedApplicants = new ArrayList<>();


}
