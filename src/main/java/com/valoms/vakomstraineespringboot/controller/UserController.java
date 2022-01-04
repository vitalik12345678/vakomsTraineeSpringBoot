package com.valoms.vakomstraineespringboot.controller;

import com.valoms.vakomstraineespringboot.dto.user.UserCreateRequest;
import com.valoms.vakomstraineespringboot.dto.user.UserProfileResponse;
import com.valoms.vakomstraineespringboot.dto.user.UserUpdateRequest;
import com.valoms.vakomstraineespringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/user/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("v1/all")
    public ResponseEntity<List<UserProfileResponse>> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("v1/{id}")
    public ResponseEntity<UserProfileResponse> getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("v1/")
    public ResponseEntity<UserProfileResponse> createUser(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        return userService.create(userCreateRequest);
    }

    @PutMapping("v1/")
    public ResponseEntity<UserProfileResponse> updateUser(@Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.update(userUpdateRequest);
    }


    @DeleteMapping("v1/{id}")
    public ResponseEntity<UserProfileResponse> deleteUser(@PathVariable("id") Long id) {
        return userService.delete(id);
    }

}
