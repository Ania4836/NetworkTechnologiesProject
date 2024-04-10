package com.example.anianetworkproject.controller.user;

import com.example.anianetworkproject.controller.user.dto.GetUserDto;
import com.example.anianetworkproject.controller.user.dto.PatchUserDto;
import com.example.anianetworkproject.controller.user.dto.PatchUserResponseDto;
import com.example.anianetworkproject.service.user.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api/users")
@PreAuthorize("isAuthenticated()")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/me")
    public ResponseEntity<GetUserDto> getMe(Principal principal) {
        String username = principal.getName();
        GetUserDto userDto = userService.getUserByUsername(username);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<GetUserDto> getOneById(@PathVariable long id) {
        GetUserDto dto = userService.getOneById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<List<GetUserDto>> getAllUsers() {
        List<GetUserDto> dto = userService.getAll();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })

    public ResponseEntity<Void> delete(@PathVariable long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<PatchUserResponseDto> update(@PathVariable long id, @RequestBody PatchUserDto dto) {
        PatchUserResponseDto responseDto = userService.update(id, dto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}