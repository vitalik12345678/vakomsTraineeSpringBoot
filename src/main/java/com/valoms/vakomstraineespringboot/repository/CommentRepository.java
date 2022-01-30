package com.valoms.vakomstraineespringboot.repository;

import com.valoms.vakomstraineespringboot.model.Comment;
import com.valoms.vakomstraineespringboot.model.Post;
import com.valoms.vakomstraineespringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByUserComment(User user);

    List<Comment> findByPostComment(Post post);

    Optional<Comment> findByUserCommentAndPostComment(User user, Post post);

}
