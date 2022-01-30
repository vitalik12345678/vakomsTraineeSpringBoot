package com.valoms.vakomstraineespringboot.service.impl;

import com.valoms.vakomstraineespringboot.dto.user.UserCreateRequest;
import com.valoms.vakomstraineespringboot.dto.user.UserProfileResponse;
import com.valoms.vakomstraineespringboot.dto.user.UserUpdateRequest;
import com.valoms.vakomstraineespringboot.exception.ExistsException;
import com.valoms.vakomstraineespringboot.exception.NotExistsException;
import com.valoms.vakomstraineespringboot.model.User;
import com.valoms.vakomstraineespringboot.model.mapper.DTOConvertor;
import com.valoms.vakomstraineespringboot.repository.UserRepository;
import com.valoms.vakomstraineespringboot.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DTOConvertor dtoConvertor;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, DTOConvertor dtoConvertor) {
        this.userRepository = userRepository;
        this.dtoConvertor = dtoConvertor;
    }

    @Override
    public ResponseEntity<UserProfileResponse> getUserById(Long id) {
        User user = findUser(id);
        UserProfileResponse userResponse =  dtoConvertor.convertToDto(user,UserProfileResponse.class);
        return ResponseEntity.ok(userResponse);
    }

    @Override
    public ResponseEntity<UserProfileResponse> create(UserCreateRequest userCreateRequest) {
        Optional<User> optionalUser = userRepository.takeByUniqueValue(userCreateRequest.getEmail(), userCreateRequest.getPhoneNumber());
        if (optionalUser.isPresent()) {
            throw new ExistsException("user with such email or phone number is already exists");
        } else {
            User user = new User();
            BeanUtils.copyProperties(userCreateRequest, user);
            userRepository.save(user);
            UserProfileResponse userProfileResponse = new UserProfileResponse();
            BeanUtils.copyProperties(user, userProfileResponse);
            return ResponseEntity.ok(userProfileResponse);
        }
    }

    @Override
    public ResponseEntity<UserProfileResponse> update(UserUpdateRequest userUpdateRequest, Long id) {

        User currentUser = findUser(id);
        Optional<User> optionalUser = userRepository.takeByUniqueValue(userUpdateRequest.getEmail(), userUpdateRequest.getPhoneNumber());
        if(optionalUser.isPresent()){
            throw new ExistsException("User with such email or phone number already exist");
        }else {
            UserProfileResponse userProfileResponse = new UserProfileResponse();
            BeanUtils.copyProperties(userUpdateRequest, currentUser);
            BeanUtils.copyProperties(currentUser, userProfileResponse);
            userRepository.save(currentUser);
            return ResponseEntity.ok(userProfileResponse);
        }

    }

    @Override
    public ResponseEntity<UserProfileResponse> delete(Long id) {
        User user = findUser(id);
        userRepository.delete(user);
        UserProfileResponse userProfileResponse = new UserProfileResponse();
        BeanUtils.copyProperties(user, userProfileResponse);
        return ResponseEntity.ok(userProfileResponse);
    }

    @Override
    public ResponseEntity<List<UserProfileResponse>> getAll() {
        List<User> userList = userRepository.findAll();
        List<UserProfileResponse> userProfileResponseList = new ArrayList<>();
        userList.forEach((user) -> {
            UserProfileResponse userProfileResponse = new UserProfileResponse();
            BeanUtils.copyProperties(user, userProfileResponse);
            userProfileResponseList.add(userProfileResponse);
        });
        return ResponseEntity.ok(userProfileResponseList);
    }

    private User findUser(Long id){
      return userRepository.findById(id).orElseThrow(() -> {
            throw new NotExistsException("User not exist");
        });
    }

}
