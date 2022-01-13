package com.valoms.vakomstraineespringboot.controller;

import com.valoms.vakomstraineespringboot.dto.like.LikeCreateRequest;
import com.valoms.vakomstraineespringboot.dto.like.LikeProfileResponse;
import com.valoms.vakomstraineespringboot.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/like/")
public class LikeController {

    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("v1/")
    private ResponseEntity<LikeProfileResponse> create (
            @Valid @RequestBody
            LikeCreateRequest likeCreateRequest){
        return likeService.create(likeCreateRequest);
    }

    @GetMapping("v1/{id}")
    private ResponseEntity<Long> getCountOfLikes(@PathVariable("id") Long id){
        return likeService.likeCounts(id);
    }

}
