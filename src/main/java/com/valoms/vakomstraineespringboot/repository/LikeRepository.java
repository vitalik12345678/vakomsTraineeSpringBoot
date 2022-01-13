package com.valoms.vakomstraineespringboot.repository;

import com.valoms.vakomstraineespringboot.model.Like;
import com.valoms.vakomstraineespringboot.model.Post;
import com.valoms.vakomstraineespringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    Long countByPostLikes(Post post);

    @Query(value = "select u from Like u where u.postLikes =:postId and u.userLikes =:userId")
    Optional<Like> checkOnLike(@Param("userId") User userId, @Param("postId") Post postId);

}
