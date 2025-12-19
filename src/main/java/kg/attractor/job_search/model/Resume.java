package kg.attractor.job_search.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Resume {
    private Integer id;
    private String applicant;
    private String name;
    private Integer categoryId;
    private Double salary;
    private Boolean isActive;
    private LocalDate createdDate;
    private LocalDate updateDate;
}
