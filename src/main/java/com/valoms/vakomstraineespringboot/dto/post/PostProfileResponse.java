package com.valoms.vakomstraineespringboot.dto.post;

import com.valoms.vakomstraineespringboot.model.mapper.Convertable;
import lombok.Data;

@Data
public class PostProfileResponse implements Convertable {

    private String title;
    private String description;
    private String username;

}
