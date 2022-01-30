package com.valoms.vakomstraineespringboot.controller;

import com.valoms.vakomstraineespringboot.dto.post.PostCreateRequest;
import com.valoms.vakomstraineespringboot.dto.post.PostDeleteResponse;
import com.valoms.vakomstraineespringboot.dto.post.PostLocationResponse;
import com.valoms.vakomstraineespringboot.dto.post.PostProfileResponse;
import com.valoms.vakomstraineespringboot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/post/")
@CrossOrigin("*")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("v1/all")
    public ResponseEntity<List<PostProfileResponse>> getAllPost() {
        return postService.getAll();
    }

    @GetMapping("v1/{id}")
    public ResponseEntity<PostProfileResponse> getPost(@PathVariable("id") Long id) {
        return postService.getById(id);
    }

    @GetMapping("v1/myposts/{id}")
    public ResponseEntity<List<PostProfileResponse>> getMyPost(@PathVariable("id")Long id){
        return postService.getMyPosts(id);
    }

    @PostMapping("v1/")
    public ResponseEntity<PostProfileResponse> createPost(@Valid @RequestBody PostCreateRequest postCreateRequest) {
        return postService.create(postCreateRequest);
    }

    @DeleteMapping("v1/{id}")
    public ResponseEntity<PostDeleteResponse> deletePost(@PathVariable("id") Long id){
        return postService.delete(id);
    }

}
