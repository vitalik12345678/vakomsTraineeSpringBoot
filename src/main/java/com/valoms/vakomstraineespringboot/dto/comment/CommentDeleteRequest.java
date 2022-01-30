package com.valoms.vakomstraineespringboot.dto.comment;

import lombok.Data;

@Data
public class CommentDeleteRequest {

    private Long userId;
    private Long postId;

}
