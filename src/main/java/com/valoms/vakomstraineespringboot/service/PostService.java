package com.valoms.vakomstraineespringboot.service;

import com.valoms.vakomstraineespringboot.dto.post.PostCreateRequest;
import com.valoms.vakomstraineespringboot.dto.post.PostProfileResponse;
import org.springframework.http.ResponseEntity;

public interface PostService {

    ResponseEntity<PostProfileResponse> create(PostCreateRequest postCreateRequest);

}
