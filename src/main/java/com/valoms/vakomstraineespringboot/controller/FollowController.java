package com.valoms.vakomstraineespringboot.controller;

import com.valoms.vakomstraineespringboot.dto.followersfollowings.FollowingsFollowersProfileResponse;
import com.valoms.vakomstraineespringboot.service.FollowersFollowingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/follow/")
public class FollowController {

    private final FollowersFollowingsService followingsService;

    @Autowired
    public FollowController(FollowersFollowingsService followingsService) {
        this.followingsService = followingsService;
    }

    @PostMapping("v1/{followerId}/{followingId}")
    public ResponseEntity<FollowingsFollowersProfileResponse> follow(@PathVariable(name = "followerId")Long followerId,
                                                                     @PathVariable(name = "followingId")Long followingId){
        return followingsService.follow(followerId,followingId);
    }

    @DeleteMapping("v1/{followerId}/{followingId}")
    public ResponseEntity<FollowingsFollowersProfileResponse> unfollow(@PathVariable(name = "followerId")Long followerId,
                                                                     @PathVariable(name = "followingId")Long followingId){
        return followingsService.unfollow(followerId,followingId);
    }

    @GetMapping("v1/followercount/{userId}")
    public Long countFollower(@PathVariable("userId")Long id){
        return followingsService.getFollowCount(id);
    }

    @GetMapping("v1/followingcount/{userId}")
    public Long countFollowing(@PathVariable("userId")Long id){
        return followingsService.getFollowCount(id);
    }

}
