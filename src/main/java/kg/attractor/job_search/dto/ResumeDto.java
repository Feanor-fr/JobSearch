package kg.attractor.job_search.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    private Integer id;
    private String applicant;

    @NotBlank(message = "{resume.name.notblank}")
    private String name;

    @NotNull(message = "{resume.categoryId.notnull}")
    private Integer categoryId;

    @NotNull(message = "{resume.salary.notnull}")
    @Positive(message = "{resume.salary.positive}")
    private Double salary;

    @NotNull(message = "{resume.isActive.notnull}")
    private Boolean isActive;

    private String categoryName;
    private LocalDate createdDate;
    private LocalDate updateDate;

    @Valid
    private List<EducationInfoDto> educationInfos;
    private List<WorkExperienceInfoDto> workExperienceInfos;
    private List<ContactInfoDto> contactInfos;
}
