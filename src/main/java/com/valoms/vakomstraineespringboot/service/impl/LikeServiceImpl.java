package com.valoms.vakomstraineespringboot.service.impl;

import com.valoms.vakomstraineespringboot.dto.like.LikeCreateRequest;
import com.valoms.vakomstraineespringboot.dto.like.LikeProfileResponse;
import com.valoms.vakomstraineespringboot.exception.ExistsException;
import com.valoms.vakomstraineespringboot.exception.NotExistsException;
import com.valoms.vakomstraineespringboot.model.Like;
import com.valoms.vakomstraineespringboot.model.Post;
import com.valoms.vakomstraineespringboot.model.User;
import com.valoms.vakomstraineespringboot.model.mapper.DTOConvertor;
import com.valoms.vakomstraineespringboot.repository.LikeRepository;
import com.valoms.vakomstraineespringboot.repository.PostRepository;
import com.valoms.vakomstraineespringboot.repository.UserRepository;
import com.valoms.vakomstraineespringboot.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final DTOConvertor dtoConvertor;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository, UserRepository userRepository, PostRepository postRepository, DTOConvertor dtoConvertor) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.dtoConvertor = dtoConvertor;
    }

    @Override
    public ResponseEntity<LikeProfileResponse> create(LikeCreateRequest likeCreateRequest) {

        User user = userRepository.findById(likeCreateRequest.getUserId())
                .orElseThrow(() -> {
                    throw new NotExistsException("User doesn't exist");
                });

        Post post = findPost(likeCreateRequest.getPostId());

        Optional<Like> optionalLike = likeRepository.checkOnLike(user, post);

        if (optionalLike.isPresent()) {
            throw new ExistsException("You were liked this post");
        } else {
            Like like = new Like();
            like.setPostLikes(post);
            like.setUserLikes(user);
            likeRepository.save(like);
            LikeProfileResponse likeProfileResponse = new LikeProfileResponse();
            likeProfileResponse.setPostname(like.getPostLikes().getTitle());
            likeProfileResponse.setUsername(like.getUserLikes().getUsername());
            return ResponseEntity.ok(likeProfileResponse);
        }
    }

    @Override
    public ResponseEntity<Long> likeCounts(Long id) {
        Post post = findPost(id);
        Long count = likeRepository.countByPostLikes(post);
        return ResponseEntity.ok(count);
    }

    private Post findPost(Long id){
       return postRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotExistsException("Post doesn't exist");
                });
    }

}
