package com.valoms.vakomstraineespringboot.repository;

import com.valoms.vakomstraineespringboot.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {



}
