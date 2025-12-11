package kg.attractor.job_search.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Vacancy {
    private Integer id;
    private String name;
    private String description;
    private Integer categoryId;
    private Double salary;
    private Integer expFrom;
    private Integer expTo;
    private Boolean isActive;
    private String author;
    private LocalDate createdDate;
    private LocalDate updateDate;

}
