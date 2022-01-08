package com.valoms.vakomstraineespringboot.dto.user;

import com.valoms.vakomstraineespringboot.model.SexEnum;
import com.valoms.vakomstraineespringboot.model.mapper.Convertable;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserUpdateRequest implements Convertable {

    @NotBlank
    private String username;

    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10}+$")
    private String phoneNumber;

    @NotNull
    private SexEnum sex;
}
