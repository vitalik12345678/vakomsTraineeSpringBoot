package com.valoms.vakomstraineespringboot.repository;

import com.valoms.vakomstraineespringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    List<User> findAll();

    @Query("select u from User u where u.email =:email and u.phoneNumber =:phoneNumber")
    Optional<User> takeByUniqueValue(@Param("email") String email, @Param("phoneNumber") String phoneNumber);

}
