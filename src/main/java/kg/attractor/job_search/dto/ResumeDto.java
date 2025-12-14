package kg.attractor.job_search.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResumeDto {
    private String applicant;
    private String name;
    private Integer categoryId;
    private Double salary;
    private Boolean isActive;
}
