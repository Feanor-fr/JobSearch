package kg.attractor.job_search.dto;

import jakarta.validation.constraints.Email;
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

    @NotBlank(message = "Укажите автора резюме!!")
    @Email(message = "Автор должен быть указан в формате Email")
    private String applicant;

    @NotBlank(message = "Название не должно быть пустым!")
    private String name;

    @NotNull(message = "Поле категория не может быть пустым!")
    private Integer categoryId;

    @NotNull(message = "Поле зарпалата не может быть пустым!")
    @Positive(message = "Зарплата не может быть отрицательным числом или равна 0!")
    private Double salary;

    @NotNull(message = "Укажите активно резюме или нет!")
    private Boolean isActive;


    private LocalDate createdDate;
    private LocalDate updateDate;
    private List<EducationInfoDto> educationInfos;
    private List<WorkExperienceInfoDto> workExperienceInfos;
    private List<ContactInfoDto> contactInfos;
}
