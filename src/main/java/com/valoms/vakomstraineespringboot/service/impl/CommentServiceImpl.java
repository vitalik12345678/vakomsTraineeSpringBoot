package com.valoms.vakomstraineespringboot.service.impl;

import com.valoms.vakomstraineespringboot.dto.comment.CommentCreateRequest;
import com.valoms.vakomstraineespringboot.dto.comment.CommentDeleteRequest;
import com.valoms.vakomstraineespringboot.dto.comment.CommentProfileResponse;
import com.valoms.vakomstraineespringboot.exception.NotExistsException;
import com.valoms.vakomstraineespringboot.model.Comment;
import com.valoms.vakomstraineespringboot.model.Post;
import com.valoms.vakomstraineespringboot.model.User;
import com.valoms.vakomstraineespringboot.model.mapper.DTOConvertor;
import com.valoms.vakomstraineespringboot.repository.CommentRepository;
import com.valoms.vakomstraineespringboot.repository.PostRepository;
import com.valoms.vakomstraineespringboot.repository.UserRepository;
import com.valoms.vakomstraineespringboot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final DTOConvertor dtoConvertor;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository, DTOConvertor dtoConvertor) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.dtoConvertor = dtoConvertor;
    }

    @Override
    public ResponseEntity<CommentProfileResponse> create(CommentCreateRequest commentCreateRequest) {
        User user = findUser(commentCreateRequest.getUserId());
        Post post = findPost(commentCreateRequest.getPostId());
        Comment comment = new Comment();
        comment.setDescription(commentCreateRequest.getDescription());
        comment.setPostComment(post);
        comment.setUserComment(user);
        commentRepository.save(comment);
        CommentProfileResponse commentResponse = createProfileResponse(user,post,commentCreateRequest.getDescription());
        return ResponseEntity.ok(commentResponse);
    }

    @Override
    public ResponseEntity<CommentProfileResponse> delete(CommentDeleteRequest commentDeleteRequest) {
        User user = findUser(commentDeleteRequest.getUserId());
        Post post = findPost(commentDeleteRequest.getPostId());
        Comment comment = commentRepository.findByUserCommentAndPostComment(user,post)
                .orElseThrow( () ->{
                    throw new NotExistsException("You are not writing a comment");
                });
        commentRepository.delete(comment);
        CommentProfileResponse commentProfileResponse = createProfileResponse(comment.getUserComment(),comment.getPostComment(),comment.getDescription());
        return ResponseEntity.ok(commentProfileResponse);
    }

    @Override
    public ResponseEntity<CommentProfileResponse> getById(Long id) {
        Comment comment = findComment(id);
        CommentProfileResponse commentProfileResponse = createProfileResponse(comment.getUserComment(),comment.getPostComment(),comment.getDescription());
        return ResponseEntity.ok(commentProfileResponse);
    }

    @Override
    public ResponseEntity<List<CommentProfileResponse>> getByUserId(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->{
            throw new NotExistsException("User doesn't exist");
                });
        List<Comment> commentList = commentRepository.findByUserComment(user);
        List<CommentProfileResponse> responseList = new ArrayList<>();
        commentList.forEach( x -> {
            responseList.add(createProfileResponse(x.getUserComment(),x.getPostComment(),x.getDescription()));
        });
        return ResponseEntity.ok(responseList);
    }

    @Override
    public ResponseEntity<List<CommentProfileResponse>> getByPostId(Long id) {
        Post post = findPost(id);
        List<Comment> commentList = commentRepository.findByPostComment(post);
        List<CommentProfileResponse> responseList = new ArrayList<>();
        commentList.forEach( x -> {
            responseList.add(createProfileResponse(x.getUserComment(),x.getPostComment(),x.getDescription()));
        });
        return ResponseEntity.ok(responseList);
    }

    private CommentProfileResponse createProfileResponse(User user, Post post,String description){
        CommentProfileResponse commentResponse = new CommentProfileResponse();
        commentResponse.setDescription(description);
        commentResponse.setUsername(user.getUsername());
        commentResponse.setPostname(post.getTitle());
        return commentResponse;
    }

    private Comment findComment(Long id){
       return commentRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotExistsException("Comment doesn't exist");
                });
    }

    private Post findPost(Long id){
        return  postRepository.findById(id).orElseThrow(
                () -> {
                    throw new NotExistsException("Post doesn't exist");
                });
    }

    private User findUser(Long id){
        return userRepository.findById(id)
                .orElseThrow( () -> {
                    throw new NotExistsException("User doesn't exist");
                });
    }
}
