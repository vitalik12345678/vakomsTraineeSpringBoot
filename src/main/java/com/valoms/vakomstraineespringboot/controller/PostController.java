package com.valoms.vakomstraineespringboot.controller;

import com.valoms.vakomstraineespringboot.dto.post.PostCreateRequest;
import com.valoms.vakomstraineespringboot.dto.post.PostProfileResponse;
import com.valoms.vakomstraineespringboot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/post/")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("all")
    public List<ResponseEntity<PostProfileResponse>> getAllPost(){
        return null;
    }

    @GetMapping("{id}")
    public ResponseEntity<PostProfileResponse> getPost(@PathVariable("id") Long id){
        return null;
    }

    @PostMapping("")
    public ResponseEntity<PostProfileResponse> createPost(@RequestBody PostCreateRequest postCreateRequest){
        return postService.create(postCreateRequest);
    }
}
