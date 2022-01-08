package com.valoms.vakomstraineespringboot.dto.user;

import com.valoms.vakomstraineespringboot.model.SexEnum;
import com.valoms.vakomstraineespringboot.model.mapper.Convertable;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class UserCreateRequest implements Convertable {

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]{3,15}+$", message = "Username can consist of english letters only")
    private String username;

    @NotBlank
    @Length(min = 8, max = 20)
    private String password;

    @Email
    private String email;

    @Pattern(regexp = "^[0-9]{10}+$")
    private String phoneNumber;

    private SexEnum sex;

    private LocalDate birthday;
}
