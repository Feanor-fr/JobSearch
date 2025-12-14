package kg.attractor.job_search.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VacancyDto {
    private String name;
    private String description;
    private Integer categoryId;
    private Double salary;
    private Integer expFrom;
    private Integer expTo;
    private Boolean isActive;
}
