package kg.attractor.job_search.dto.update;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ResumeForUpdateDto {

    private Integer id;
    private String name;

    private Integer categoryId;

    @Positive(message = "{resume.salary.positive}")
    private Double salary;

    @NotNull(message = "{resume.isActive.notnull}")
    private Boolean isActive;

    private List<EducationInfoForUpdateDto> educationInfos;
    private List<WorkExperienceInfoForUpdateDto> workExperienceInfos;
    private List<ContactInfoForUpdateDto> contactInfos;
}
