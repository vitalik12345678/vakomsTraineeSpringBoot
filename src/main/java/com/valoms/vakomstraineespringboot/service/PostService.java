package com.valoms.vakomstraineespringboot.service;

import com.valoms.vakomstraineespringboot.dto.post.PostCreateRequest;
import com.valoms.vakomstraineespringboot.dto.post.PostDeleteResponse;
import com.valoms.vakomstraineespringboot.dto.post.PostProfileResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {

    ResponseEntity<List<PostProfileResponse>> getAll();

    ResponseEntity<PostProfileResponse> create(PostCreateRequest postCreateRequest);

    ResponseEntity<PostProfileResponse> getById(Long id);

    ResponseEntity<PostDeleteResponse> delete(Long id);

}
