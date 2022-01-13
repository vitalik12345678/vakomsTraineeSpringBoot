package com.valoms.vakomstraineespringboot.service.impl;

import com.valoms.vakomstraineespringboot.dto.followersfollowings.FollowingsFollowersProfileResponse;
import com.valoms.vakomstraineespringboot.exception.NotExistsException;
import com.valoms.vakomstraineespringboot.model.FollowersFollowings;
import com.valoms.vakomstraineespringboot.model.User;
import com.valoms.vakomstraineespringboot.model.mapper.DTOConvertor;
import com.valoms.vakomstraineespringboot.repository.FollowersFollowingsRepository;
import com.valoms.vakomstraineespringboot.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final DTOConvertor dtoConvertor;


    @Autowired
    public FollowersFollowingsServiceImpl(FollowersFollowingsRepository followersFollowingsRepository, UserRepository userRepository, DTOConvertor dtoConvertor) {
        this.followersFollowingsRepository = followersFollowingsRepository;
        this.userRepository = userRepository;
        this.dtoConvertor = dtoConvertor;
    }

    @Override
    public ResponseEntity<FollowingsFollowersProfileResponse> follow(Long followerId, Long followingId) {
        User follower = findUser(followerId);
        User following = findUser(followingId);
        FollowersFollowings followersFollowings = new FollowersFollowings();
        followersFollowings.setFollower(follower);
        followersFollowings.setFollowing(following);
        followersFollowingsRepository.save(followersFollowings);
        FollowingsFollowersProfileResponse profileResponse = createProfileResponse(follower,following);
        return ResponseEntity.ok(profileResponse);
    }

    @Override
    public ResponseEntity<FollowingsFollowersProfileResponse> unfollow(Long followerId, Long followingId) {
        User follower = findUser(followerId);
        User following = findUser(followingId);
        followersFollowingsRepository.deleteFollow(follower,following);
        FollowingsFollowersProfileResponse profileResponse = createProfileResponse(follower,following);
        return ResponseEntity.ok(profileResponse);
    }

    @Override
    public ResponseEntity<List<FollowingsFollowersProfileResponse>> getListOfFollowers(Long userId) {
        return null;
    }

    @Override
    public Long getFollowCount(Long userId) {
        User follower = findUser(userId);
        return followersFollowingsRepository.countByFollower(follower);
    }

    @Override
    public Long getFollowingCount(Long userId) {
        User following = findUser(userId);
        return followersFollowingsRepository.countByFollowing(following);
    }

    @Override
    public ResponseEntity<List<FollowingsFollowersProfileResponse>> getListOfFollowings(Long userId) {
        return null;
    }

    private User findUser (Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->{
                    throw new NotExistsException( "User doesn't exist" );
                });
    }

    private FollowingsFollowersProfileResponse createProfileResponse(User follower,User following){
        FollowingsFollowersProfileResponse profileResponse = new FollowingsFollowersProfileResponse();
        profileResponse.setUsernameFollowing(following.getUsername());
        profileResponse.setUsernameFollower(follower.getUsername());
        return profileResponse;
    }
}
