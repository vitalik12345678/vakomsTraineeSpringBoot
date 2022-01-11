package com.valoms.vakomstraineespringboot.dto.comment;

import lombok.Data;

@Data
public class CommentCreateRequest {

    private Long postId;
    private Long userId;
    private String description;

}
