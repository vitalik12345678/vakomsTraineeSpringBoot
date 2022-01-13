package com.valoms.vakomstraineespringboot.repository;

import com.valoms.vakomstraineespringboot.model.FollowersFollowings;
import com.valoms.vakomstraineespringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowersFollowingsRepository extends JpaRepository<FollowersFollowings, Long> {

    @Modifying
    @Query(value = "delete from FollowersFollowings f where f.follower =:follower and f.following =:following")
    void deleteFollow(@Param("follower") User follower, @Param("following") User following);

    Long countByFollower(User follower);

    Long countByFollowing(User following);

}
