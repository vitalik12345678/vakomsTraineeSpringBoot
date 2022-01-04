package com.valoms.vakomstraineespringboot.repository;

import com.valoms.vakomstraineespringboot.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
