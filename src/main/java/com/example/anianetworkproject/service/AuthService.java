package com.example.anianetworkproject.service;

import com.example.anianetworkproject.controller.dto.LoginDto;
import com.example.anianetworkproject.controller.dto.LoginResponseDto;
import com.example.anianetworkproject.controller.dto.RegisterDto;
import com.example.anianetworkproject.controller.dto.RegisterResponseDto;
import com.example.anianetworkproject.infrastructure.entity.AuthEntity;
import com.example.anianetworkproject.infrastructure.entity.UserEntity;
import com.example.anianetworkproject.infrastructure.repository.AuthRepository;
import com.example.anianetworkproject.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AuthService {

    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AuthRepository authRepository, UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }



    @Transactional
    public RegisterResponseDto register(RegisterDto dto) {

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(dto.getEmail());
        userRepository.save(userEntity);

        AuthEntity authEntity = new AuthEntity();
        authEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        authEntity.setUsername(dto.getUsername());
        authEntity.setRole(dto.getRole());
        authEntity.setUser(userEntity);

        authRepository.save(authEntity);

        return new RegisterResponseDto(userEntity.getId(), authEntity.getUsername(), authEntity.getRole());
    }


    public LoginResponseDto login(LoginDto dto) {
        AuthEntity authEntity = authRepository.findByUsername(dto.getUsername()).orElseThrow(RuntimeException::new);

        String token = jwtService.generateToken(authEntity);

        if (!passwordEncoder.matches(dto.getPassword(), authEntity.getPassword())) {
            throw new RuntimeException();
        }

        return new LoginResponseDto(token);
    }
}


