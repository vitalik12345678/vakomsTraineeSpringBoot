package com.valoms.vakomstraineespringboot.dto.post;

import com.valoms.vakomstraineespringboot.model.LocationJSON;
import com.valoms.vakomstraineespringboot.model.mapper.Convertable;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PostCreateRequest implements Convertable {

    @NotNull
    private Long userId;

    @NotBlank
    private String title;

    @NotNull
    private LocationJSON locationJSON;

    private String description;
}
