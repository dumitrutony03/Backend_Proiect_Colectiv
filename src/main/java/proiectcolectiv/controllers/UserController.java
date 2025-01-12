package proiectcolectiv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import proiectcolectiv.dto.UserDataDto;
import proiectcolectiv.mapper.UserMapper;
import proiectcolectiv.model.UserData;
import proiectcolectiv.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    /**
     * Register a new user
     *
     * @param userDto User registration details
     * @return ResponseEntity with the saved user
     */
    @PostMapping("/register")
    public ResponseEntity<UserDataDto> registerUser(@RequestBody UserDataDto userDto) {
        // Check if username already exists
        if (userService.findByUserName(userDto.userName) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        UserData user = userMapper.toModel(userDto);
        if (userDto.role == null || userDto.role.isEmpty()) {
            user.setRole("PACIENT"); // Default role
        } else {
            user.setRole(userDto.role);
        }

        UserData savedUser = userService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toDto(savedUser));
    }

    /**
     * Login endpoint
     *
     * @param userDto User credentials
     * @return ResponseEntity with user details
     */
    @PostMapping("/login")
    public ResponseEntity<UserDataDto> login(@RequestBody UserDataDto userDto) {
        UserData user = userService.findByUserName(userDto.userName);

        // Validate user existence and password
        if (user == null || !userService.validatePassword(userDto.password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }

        // Return user details on successful authentication
        return ResponseEntity.ok(userMapper.toDto(user));
    }
}
