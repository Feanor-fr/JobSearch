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

    @NotBlank(message = "Поле с именем не должно быть пустым!")
    private String name;

    @NotBlank(message = "Поле с фамилией не должно быть пустым!")
    private String surname;

    @NotNull(message = "Возраст обязателен")
    @Min(value = 18, message = "Возраст должен быть не менее 18 лет")
    @Max(value = 60, message = "Возраст должен быть не более 60 лет")
    private Integer age;

    @NotBlank(message = "Поле email не может быть пустым!")
    @Email(message = "Поле должно быть указанно в формате qwe@.qwe")
    private String email;

    @NotBlank(message = "Поле пароль не должно быть пустым!")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$",
            message = "Пароль должен содержать хотя бы одну заглавную букву и одну цифру!")
    private String password;

    @NotBlank
    @Pattern(regexp = "^\\+996[0-9]{9}$",
            message = "Номер телефона должен быть в формате +996XXXXXXXXX (9 цифр после +996)")
    private String phoneNumber;
    private String avatar;

    @NotBlank(message = "Тип аккаунта не может быть пустым! Доступные типы: applicant или employer")
    private String role;

}
