package com.valoms.vakomstraineespringboot.service.impl;

import com.valoms.vakomstraineespringboot.dto.post.PostCreateRequest;
import com.valoms.vakomstraineespringboot.dto.post.PostProfileResponse;
import com.valoms.vakomstraineespringboot.model.Post;
import com.valoms.vakomstraineespringboot.model.User;
import com.valoms.vakomstraineespringboot.repository.PostRepository;
import com.valoms.vakomstraineespringboot.repository.UserRepository;
import com.valoms.vakomstraineespringboot.service.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<PostProfileResponse> create(PostCreateRequest postCreateRequest) {
        Optional<User> optionalUser = userRepository.findById(postCreateRequest.getUserId());
        Post post = new Post();
        post.setUser(optionalUser.get());
        post.setLocation(postCreateRequest.getLocationJSON());
        BeanUtils.copyProperties(postCreateRequest, post);
        postRepository.save(post);
        return ResponseEntity.ok(null);
    }
}
