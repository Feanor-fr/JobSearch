package kg.attractor.job_search.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ContactInfoDto {

    @NotNull(message = "Поле id типа не может быть пустым!")
    @Positive(message = "Поле id типа не может быть отрицательным числом или равна 0!")
    private Integer typeId;

    @NotBlank(message = "Поле с контактной информацией не должно быть пустым!")
    private String contactValue;
}
