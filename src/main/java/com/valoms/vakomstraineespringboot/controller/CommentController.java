package com.valoms.vakomstraineespringboot.controller;

import com.valoms.vakomstraineespringboot.dto.comment.CommentCreateRequest;
import com.valoms.vakomstraineespringboot.dto.comment.CommentProfileResponse;
import com.valoms.vakomstraineespringboot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/comment/")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("v1/")
    public ResponseEntity<CommentProfileResponse> create(
            @Valid @RequestBody
            CommentCreateRequest commentCreateRequest){
        return commentService.create(commentCreateRequest);
    }

    @DeleteMapping("v1/{id}")
    public ResponseEntity<CommentProfileResponse> delete(@PathVariable("id")Long id){
        return commentService.delete(id);
    }

    @GetMapping("v1/{id}")
    public ResponseEntity<CommentProfileResponse> getById(@PathVariable("id")Long id){
        return commentService.getById(id);
    }

    @GetMapping("v1/user/{userId}")
    public ResponseEntity<List<CommentProfileResponse>> getByUserId(@PathVariable("userId")Long id){
        return commentService.getByUserId(id);
    }

    @GetMapping("v1/post/{postId}")
    public ResponseEntity<List<CommentProfileResponse>> getByPostId(@PathVariable("postId")Long id){
    return commentService.getByPostId(id);
    }

}