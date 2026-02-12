package kg.attractor.job_search.dto.update;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserForUpdateDto {


    private String name;
    private String surname;


    @Min(value = 18, message = "{user.age.min}")
    @Max(value = 60, message = "{user.age.max}")
    private Integer age;


    @Email(message = "{user.email.invalid}")
    private String email;

    @Pattern(regexp = "^$|^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$",
            message = "{user.password.pattern}")
    private String password;


    @Pattern(regexp = "^$|^\\+996[0-9]{9}$",
            message = "{user.phone.pattern}")
    private String phoneNumber;
    private String avatar;

}
