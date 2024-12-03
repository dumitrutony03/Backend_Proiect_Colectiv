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


    @PostMapping(value = "/login")
    public UserDataDto login(@RequestBody UserDataDto userDto, @RequestParam String role) throws UserException {
        UserData model = mapper.toModel(userDto);
        UserData loginUser = null;
        if (Role.DOCTOR.name().equals(role)) {
            loginUser = service.loginDoctor(model);
        } else if (Role.PACIENT.name().equals(role)) {
            loginUser = service.loginPacient(model);
        }
        if (Objects.isNull(loginUser)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid User Name or Password");
        }
        return mapper.toDto(loginUser);
    }
}
