package com.valoms.vakomstraineespringboot.service;

import com.valoms.vakomstraineespringboot.dto.user.UserCreateRequest;
import com.valoms.vakomstraineespringboot.dto.user.UserProfileResponse;
import com.valoms.vakomstraineespringboot.dto.user.UserUpdateRequest;
import com.valoms.vakomstraineespringboot.dto.user.UserUpdateResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<UserProfileResponse> getUserById(Long id) ;

    ResponseEntity<UserProfileResponse> create(UserCreateRequest userCreateRequest);

    ResponseEntity<UserUpdateResponse> update(UserUpdateRequest userUpdateRequest);

    ResponseEntity<UserProfileResponse> delete(Long id);

    List<ResponseEntity<UserProfileResponse>> getAll();
}
