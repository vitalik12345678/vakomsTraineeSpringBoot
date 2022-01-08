package com.valoms.vakomstraineespringboot.service.impl;

import com.valoms.vakomstraineespringboot.dto.post.PostCreateRequest;
import com.valoms.vakomstraineespringboot.dto.post.PostDeleteResponse;
import com.valoms.vakomstraineespringboot.dto.post.PostProfileResponse;
import com.valoms.vakomstraineespringboot.exception.NotExistsException;
import com.valoms.vakomstraineespringboot.model.Post;
import com.valoms.vakomstraineespringboot.model.User;
import com.valoms.vakomstraineespringboot.model.mapper.DTOConvertor;
import com.valoms.vakomstraineespringboot.repository.PostRepository;
import com.valoms.vakomstraineespringboot.repository.UserRepository;
import com.valoms.vakomstraineespringboot.service.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final DTOConvertor dtoConvertor;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, DTOConvertor dtoConvertor) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.dtoConvertor = dtoConvertor;
    }

    @Override
    public ResponseEntity<List<PostProfileResponse>> getAll() {

        List<Post> postList = postRepository.findAll();
        List<PostProfileResponse> profileList = new ArrayList<>();
        postList.forEach( x -> {
            PostProfileResponse profileResponse = new PostProfileResponse();
            BeanUtils.copyProperties(x,profileResponse);
            profileList.add(profileResponse);
        });
        return ResponseEntity.ok(profileList);
    }

    @Override
    public ResponseEntity<PostProfileResponse> create(PostCreateRequest postCreateRequest) {
        User optionalUser = userRepository.findById(postCreateRequest.getUserId()).orElseThrow(()->{
            throw new NotExistsException("User don't exists");
        });
        Post post = new Post();
        post.setUser(optionalUser);
        post.setLocation(postCreateRequest.getLocationJSON());
        BeanUtils.copyProperties(postCreateRequest, post);
        PostProfileResponse profileResponse = dtoConvertor.convertToDto(post,PostProfileResponse.class);
        profileResponse.setUsername(optionalUser.getUsername());
        postRepository.save(post);
        return ResponseEntity.ok(profileResponse);
    }

    @Override
    public ResponseEntity<PostProfileResponse> getById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotExistsException("Post doesn't exists");
                });
        User user = userRepository.getById(post.getUser().getId());
        PostProfileResponse profileResponse = new PostProfileResponse();
        profileResponse.setUsername(user.getUsername());
        BeanUtils.copyProperties(post,profileResponse);
        return ResponseEntity.ok(profileResponse);
    }

    @Override
    public ResponseEntity<PostDeleteResponse> delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() ->{
            throw new NotExistsException("Post doesn't exists");
                });
        PostDeleteResponse postDeleteResponse = new PostDeleteResponse();
        BeanUtils.copyProperties(post,postDeleteResponse);
        return ResponseEntity.ok(postDeleteResponse);
    }
}
