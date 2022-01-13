package com.valoms.vakomstraineespringboot.dto.like;

import lombok.Data;

@Data
public class LikeCreateRequest {

    private Long userId;
    private Long postId;

}
