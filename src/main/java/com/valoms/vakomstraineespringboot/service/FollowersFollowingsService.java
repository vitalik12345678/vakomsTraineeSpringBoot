package com.valoms.vakomstraineespringboot.service;

import com.valoms.vakomstraineespringboot.dto.followersfollowings.FollowingsFollowersProfileResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FollowersFollowingsService {

    ResponseEntity<FollowingsFollowersProfileResponse> follow(Long followerId,Long followingId);

    ResponseEntity<FollowingsFollowersProfileResponse> unfollow(Long followerId, Long followingId);

    ResponseEntity<List<FollowingsFollowersProfileResponse>> getListOfFollowers(Long userId);

    ResponseEntity<Long> getFollowersCount(Long userId);

    ResponseEntity<List<FollowingsFollowersProfileResponse>> getListOfFollowings(Long userId);

    ResponseEntity<Long> getFollowingsCount(Long userId);
}
