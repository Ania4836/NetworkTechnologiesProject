package com.example.anianetworkproject.service;
import com.example.anianetworkproject.controller.dto.GetUserDto;
import com.example.anianetworkproject.controller.dto.UserDto;
import com.example.anianetworkproject.controller.dto.UserResponseDto;
import com.example.anianetworkproject.infrastructure.entity.UserEntity;
import com.example.anianetworkproject.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public GetUserDto getUserById(long id) {
        UserEntity user = userRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);

        return new GetUserDto(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getDateOfBirth(),
                user.getEmail()
        );
    }

    public List<GetUserDto> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();

        return users
                .stream()
                .map(user -> new GetUserDto(
                        user.getId(),
                        user.getName(),
                        user.getLastName(),
                        user.getDateOfBirth(),
                        user.getEmail()
                ))
                .collect(Collectors.toList());
    }

    public void deleteUserById(long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException();
        }
        userRepository.deleteById(id);
    }

}