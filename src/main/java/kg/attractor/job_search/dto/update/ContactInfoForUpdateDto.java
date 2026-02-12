package kg.attractor.job_search.dto.update;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ContactInfoForUpdateDto {

    @Positive(message = "{contact.typeId.positive}")
    private Integer typeId;
    private String contactValue;
}
