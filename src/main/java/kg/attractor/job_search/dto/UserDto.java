package kg.attractor.job_search.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDto {

    @NotBlank(message = "{user.name.notblank}")
    private String name;

    @NotBlank(message = "{user.surname.notblank}")
    private String surname;

    @NotNull(message = "{user.age.notnull}")
    @Min(value = 18, message = "{user.age.min}")
    @Max(value = 60, message = "{user.age.max}")
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
