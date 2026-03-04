package kg.attractor.job_search.dto.update;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "{resume.name.notblank}")
    private String name;

    @NotNull(message = "{resume.categoryId.notnull}")
    private Integer categoryId;

    @Positive(message = "{resume.salary.positive}")
    private Double salary;

    @NotNull(message = "{resume.isActive.notnull}")
    private Boolean isActive;

    @Valid
    private List<EducationInfoForUpdateDto> educationInfos;

    @Valid
    private List<WorkExperienceInfoForUpdateDto> workExperienceInfos;

    @Valid
    private List<ContactInfoForUpdateDto> contactInfos;
}
