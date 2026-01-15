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


    @Min(value = 18, message = "Возраст должен быть не менее 18 лет")
    @Max(value = 60, message = "Возраст должен быть не более 60 лет")
    private Integer age;


    @Email(message = "Поле должно быть указанно в формате qwe@.qwe")
    private String email;

    @Pattern(regexp = "^$|^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$",
            message = "Пароль должен содержать хотя бы одну заглавную букву и одну цифру!")
    private String password;


    @Pattern(regexp = "^$|^\\+996[0-9]{9}$",
            message = "Номер телефона должен быть в формате +996XXXXXXXXX (9 цифр после +996)")
    private String phoneNumber;
    private String avatar;

}
