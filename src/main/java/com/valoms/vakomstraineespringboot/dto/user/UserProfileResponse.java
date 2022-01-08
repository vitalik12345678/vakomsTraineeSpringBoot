package com.valoms.vakomstraineespringboot.dto.user;

import com.valoms.vakomstraineespringboot.model.mapper.Convertable;
import lombok.Data;

@Data
public class UserProfileResponse implements Convertable {

    private String username;
    private String phoneNumber;
    private String email;

}
