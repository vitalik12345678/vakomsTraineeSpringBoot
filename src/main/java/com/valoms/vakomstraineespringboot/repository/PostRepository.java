package com.valoms.vakomstraineespringboot.repository;

import com.valoms.vakomstraineespringboot.model.Post;
import com.valoms.vakomstraineespringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findById(Long id);

    List<Post> findByUser(User user);
}
