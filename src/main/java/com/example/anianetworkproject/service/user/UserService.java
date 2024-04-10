package com.example.anianetworkproject.service.user;
import com.example.anianetworkproject.controller.user.dto.GetUserDto;
import com.example.anianetworkproject.controller.user.dto.PatchUserDto;
import com.example.anianetworkproject.controller.user.dto.PatchUserResponseDto;
import com.example.anianetworkproject.infrastructure.entity.AuthEntity;
import com.example.anianetworkproject.infrastructure.entity.UserEntity;
import com.example.anianetworkproject.infrastructure.repository.AuthRepository;
import com.example.anianetworkproject.infrastructure.repository.UserRepository;
import com.example.anianetworkproject.service.auth.OwnershipService;
import com.example.anianetworkproject.service.user.error.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService extends OwnershipService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, AuthRepository authRepository) {
        super(authRepository);
        this.userRepository = userRepository;
    }

    public GetUserDto getUserByUsername(String username) {
        AuthEntity auth = authRepository
                .findByUsername(username)
                .orElseThrow(() -> UserNotFound.createWithUsername(username));
        UserEntity user = auth.getUser();
        return mapUser(user);
    }
    public GetUserDto getOneById(long id) {
        UserEntity user = userRepository
                .findById(id)
                .orElseThrow(() -> UserNotFound.createWithId(id));
        return mapUser(user);
    }
    public List<GetUserDto> getAll() {
        List<UserEntity> users = userRepository.findAll();
        return users
                .stream()
                .map(this::mapUser)
                .collect(Collectors.toList());
    }
    public void delete(long id) {
        if (!userRepository.existsById(id)) {
            throw UserNotFound.createWithId(id);
        }
        userRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name, #id)")
    public PatchUserResponseDto update(long id, PatchUserDto dto) {
        UserEntity user = userRepository
                .findById(id)
                .orElseThrow(() -> UserNotFound.createWithId(id));

        dto.getName().ifPresent(user::setName);
        dto.getLastName().ifPresent(user::setLastName);
        dto.getDateOfBirth().ifPresent(user::setDateOfBirth);
        dto.getEmail().ifPresent(user::setEmail);

        userRepository.save(user);

        return new PatchUserResponseDto(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getDateOfBirth(),
                user.getEmail()
        );
    }

    private GetUserDto mapUser(UserEntity user) {
        return new GetUserDto(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getDateOfBirth(),
                user.getEmail()
        );
    }
}