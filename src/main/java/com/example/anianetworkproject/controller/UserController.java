package com.example.anianetworkproject.controller;

import com.example.anianetworkproject.controller.dto.UserDto;
import com.example.anianetworkproject.controller.dto.UserResponseDto;
import com.example.anianetworkproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

//    @PostMapping("/add")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserDto userDto) {
//        String encryptedPassword = passwordEncoder.encode(userDto.getPassword());
//        userDto.setPassword(encryptedPassword);
//
//        UserResponseDto responseDto = userService.addUser(userDto);
//        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
//    }

//    @DeleteMapping("/delete/{userId}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<Void> deleteUser(@PathVariable long userId) {
//        userService.deleteUser(userId);
//        return ResponseEntity.noContent().build();
//    }
}
