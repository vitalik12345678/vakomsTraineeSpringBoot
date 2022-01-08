package com.valoms.vakomstraineespringboot.service.impl;

import com.valoms.vakomstraineespringboot.dto.followersfollowings.FollowingsFollowersProfileResponse;
import com.valoms.vakomstraineespringboot.repository.FollowersFollowingsRepository;
import com.valoms.vakomstraineespringboot.service.FollowersFollowingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FollowersFollowingsServiceImpl implements FollowersFollowingsService {

    private final FollowersFollowingsRepository followersFollowingsRepository;

    @Autowired
    public FollowersFollowingsServiceImpl(FollowersFollowingsRepository followersFollowingsRepository) {
        this.followersFollowingsRepository = followersFollowingsRepository;
    }

    @Override
    public ResponseEntity<FollowingsFollowersProfileResponse> follow(Long followerId, Long followingId) {
        return null;
    }

    @Override
    public ResponseEntity<FollowingsFollowersProfileResponse> unfollow(Long followerId, Long followingId) {
        return null;
    }

    @Override
    public ResponseEntity<List<FollowingsFollowersProfileResponse>> getListOfFollowers(Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<Long> getFollowersCount(Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<List<FollowingsFollowersProfileResponse>> getListOfFollowings(Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<Long> getFollowingsCount(Long userId) {
        return null;
    }
}
