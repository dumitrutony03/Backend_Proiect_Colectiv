package proiectcolectiv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import proiectcolectiv.dto.UserDataDto;
import proiectcolectiv.model.Role;
import proiectcolectiv.model.UserData;
import proiectcolectiv.service.UserService;
import proiectcolectiv.mapper.UserMapper;

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
    public UserDataDto addUser(@RequestBody UserDataDto userDto) {
        UserData model = mapper.toModel(userDto);
        UserData savedModel = service.save(model);
        return mapper.toDto(savedModel);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDataDto> getAllUsers() {
        List<UserData> users = service.findAll();
        return users.stream().map(elem -> mapper.toDto(elem)).collect(Collectors.toList());
    }

    @GetMapping(value = "/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDataDto> getUserByName(@PathVariable String userName) {
        UserData user = service.findByUserName(userName);
        if (Objects.isNull(user)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(mapper.toDto(user), HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public UserDataDto login(@RequestBody UserDataDto userDto) throws UserException {
        UserData model = mapper.toModel(userDto);
        UserData loginUser = null;
        if(Role.DOCTOR.name().equals(userDto.role)){
             loginUser = service.loginDoctor(model);
        } else if (Role.PACIENT.name().equals(userDto.role)) {
             loginUser = service.loginPacient(model);
        }

        if (Objects.isNull(loginUser)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid User Name or Password");
        }
        return mapper.toDto(loginUser);
    }
}
