package kg.attractor.job_search.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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
    private LocalDate createdDate;
    private LocalDate updateDate;
    private List<EducationInfoDto> educationInfos;
    private List<WorkExperienceInfoDto> workExperienceInfos;
}
