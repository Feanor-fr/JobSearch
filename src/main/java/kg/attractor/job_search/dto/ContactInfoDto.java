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

    @NotNull(message = "{contact.typeId.notnull}")
    @Positive(message = "{contact.typeId.positive}")
    private Integer typeId;

//    @NotBlank(message = "{contact.contactValue.notblank}")
    private String contactValue;
}
