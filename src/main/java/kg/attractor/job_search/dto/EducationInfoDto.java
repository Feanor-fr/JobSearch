package kg.attractor.job_search.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EducationInfoDto {

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

}
