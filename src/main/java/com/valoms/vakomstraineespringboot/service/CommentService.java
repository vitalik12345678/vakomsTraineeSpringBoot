package com.valoms.vakomstraineespringboot.service;

import com.valoms.vakomstraineespringboot.dto.comment.CommentCreateRequest;
import com.valoms.vakomstraineespringboot.dto.comment.CommentProfileResponse;
import org.apache.catalina.LifecycleState;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {

    ResponseEntity<CommentProfileResponse> create(CommentCreateRequest commentCreateRequest);

    ResponseEntity<CommentProfileResponse> delete(Long id);

    ResponseEntity<CommentProfileResponse> getById(Long id);

    ResponseEntity<List<CommentProfileResponse>> getByUserId(Long id);

    ResponseEntity<List<CommentProfileResponse>> getByPostId(Long id);
}
