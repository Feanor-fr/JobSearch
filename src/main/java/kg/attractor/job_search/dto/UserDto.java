package kg.attractor.job_search.dto;


import jakarta.validation.constraints.*;
import kg.attractor.job_search.customValidation.ValidUserAge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ValidUserAge
public class UserDto {

    @NotBlank(message = "{user.name.notblank}")
    private String name;

    private String surname;

    @NotNull(message = "{user.age.notnull}")
    @Positive(message = "Не допустимый формат")
    private Integer age;

    @NotBlank(message = "{user.email.notblank}")
    @Email(message = "{user.email.invalid}")
    private String email;

    @NotBlank(message = "{user.password.notblank}")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$",
            message = "{user.password.pattern}")
    private String password;

    @NotBlank
    @Pattern(regexp = "^\\+996[0-9]{9}$",
            message = "{user.phone.pattern}")
    private String phoneNumber;
    private String avatar;

    @NotNull(message = "{user.role.notnull}")
    private Integer role;

}
