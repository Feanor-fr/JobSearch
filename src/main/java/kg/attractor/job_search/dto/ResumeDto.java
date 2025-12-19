package kg.attractor.job_search.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ResumeDto {
    private String applicant;
    private String name;
    private Integer categoryId;
    private Double salary;
    private Boolean isActive;
}
