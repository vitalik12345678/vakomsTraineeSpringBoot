package com.valoms.vakomstraineespringboot.dto.comment;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CommentCreateRequest {

    @NotNull
    private Long postId;
    @NotNull
    private Long userId;
    @NotBlank
    private String description;

}
