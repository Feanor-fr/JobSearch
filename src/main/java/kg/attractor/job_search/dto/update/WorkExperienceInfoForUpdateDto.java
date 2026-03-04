package kg.attractor.job_search.dto.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import kg.attractor.job_search.customValidation.ValidWorkExperienceYears;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class WorkExperienceInfoForUpdateDto {

    @NotNull(message = "{experience.years.notnull}")
    @Positive(message = "{experience.years.positive}")
    @ValidWorkExperienceYears(message = "{experience.years.correct_interval}")
    private Integer years;

    @NotBlank(message = "{experience.companyName.notblank}")
    private String companyName;

    @NotBlank(message = "{experience.position.notblank}")
    private String position;

    @NotBlank(message = "{experience.responsibilities.notblank}")
    private String responsibilities;
    private Integer id;
}
