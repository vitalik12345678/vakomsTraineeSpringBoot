package com.valoms.vakomstraineespringboot.service;

import com.valoms.vakomstraineespringboot.dto.post.PostCreateRequest;
import com.valoms.vakomstraineespringboot.dto.post.PostProfileResponse;
import com.valoms.vakomstraineespringboot.model.Post;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface PostService {

    ResponseEntity<PostProfileResponse> create(PostCreateRequest postCreateRequest);

}
