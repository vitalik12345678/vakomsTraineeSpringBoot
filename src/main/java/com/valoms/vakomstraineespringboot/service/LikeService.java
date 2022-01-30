package com.valoms.vakomstraineespringboot.service;

import com.valoms.vakomstraineespringboot.dto.like.LikeCreateRequest;
import com.valoms.vakomstraineespringboot.dto.like.LikeProfileResponse;
import com.valoms.vakomstraineespringboot.model.Post;
import org.springframework.http.ResponseEntity;

public interface LikeService {

    ResponseEntity<LikeProfileResponse> create(LikeCreateRequest likeCreateRequest);

    ResponseEntity<Long> likeCounts(Long id);

    ResponseEntity<Boolean> delete(LikeCreateRequest likeCreateRequest);
}
