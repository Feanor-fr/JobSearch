package kg.attractor.job_search.dto.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kg.attractor.job_search.customValidation.EducationDateValidHelper;
import kg.attractor.job_search.customValidation.ValidEducationDates;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EducationInfoForUpdateDto implements EducationDateValidHelper {

    @NotBlank(message = "{education.institution.notblank}")
    private String institution;

    @NotBlank(message = "{education.program.notblank}")
    private String program;

    @NotNull(message = "{education.startDate.notnull}")
    private LocalDate startDate;

    @NotNull(message = "{education.endDate.notnull}")
    private LocalDate endDate;

    @NotBlank(message = "{education.degree.notblank}")
    private String degree;
    private Integer id;
}
