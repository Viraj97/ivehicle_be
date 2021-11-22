package com.ivehicle.controller;

import com.ivehicle.dto.LoginDTO;
import com.ivehicle.dto.UserDTO;
import com.ivehicle.entity.UserEntity;
import com.ivehicle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(maxAge = 3600, origins = "*", exposedHeaders = "**")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/userSignup")
    public ResponseEntity<?> hello(@RequestBody UserDTO userDTO) {

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setPassWord(userDTO.getPassword());
        userEntity.setUserName(userDTO.getUsername());
        return ResponseEntity.ok(userRepository.save(userEntity));
    }

    @PostMapping("/userLogin")
    public ResponseEntity<?> hello(@RequestBody LoginDTO loginDTO) {
        UserDTO userDTO = new UserDTO();
        userRepository.findByEmail(loginDTO.getUsername()).ifPresent(userEntity -> {
            userDTO.setUsername(userEntity.getUserName());
            userDTO.setPassword(userEntity.getPassWord());
            userDTO.setEmail(userEntity.getEmail());
            userDTO.setFirstName(userEntity.getFirstName());
            userDTO.setLastName(userEntity.getLastName());
        });
        if(loginDTO.getPassword().equals(userDTO.getPassword())) {
            return ResponseEntity.ok(userRepository.save(userDTO));
        }
        return ResponseEntity.ok(null);
    }
}
