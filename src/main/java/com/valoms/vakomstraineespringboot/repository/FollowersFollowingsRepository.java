package com.valoms.vakomstraineespringboot.repository;

import com.valoms.vakomstraineespringboot.model.FollowersFollowings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowersFollowingsRepository extends JpaRepository<FollowersFollowings, Long> {
}
