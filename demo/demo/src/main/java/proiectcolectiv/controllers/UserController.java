package proiectcolectiv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiectcolectiv.service.UserService;
import proiectcolectiv.dto.UserDto;
import proiectcolectiv.mapper.UserMapper;
import proiectcolectiv.model.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private UserMapper mapper;

    @PostMapping(value = "/")
    public UserDto addUser(@RequestBody UserDto userDto) {
        User model = mapper.toModel(userDto);
        User savedModel = service.save(model);
        return mapper.toDto(savedModel);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> getAllUsers() {
        List<User> users = service.findAll();
        return users.stream().map(elem -> mapper.toDto(elem)).collect(Collectors.toList());
    }

    @GetMapping(value = "/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUserByName(@PathVariable String userName) {
        User user = service.findByUserName(userName);
        if (Objects.isNull(user)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(mapper.toDto(user), HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public UserDto login(@RequestBody UserDto userDto) throws UserException {
        User model = mapper.toModel(userDto);
        User loginUser = service.login(model);
        if (Objects.isNull(loginUser)) {
            throw new UserException(HttpStatus.BAD_REQUEST,
                    "Invalid User Name or Password", null);
        }
        return mapper.toDto(loginUser);
    }
}
