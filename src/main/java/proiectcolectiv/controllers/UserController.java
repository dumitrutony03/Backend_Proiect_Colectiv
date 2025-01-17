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

import java.util.Objects;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private UserMapper mapper;

    /**
     * User login with given role
     *
     * @param userDto
     * @param role
     * @return UserDataDto
     */
    @PostMapping(value = "/login")
    public UserDataDto login(@RequestBody UserDataDto userDto, @RequestParam String role) {
        UserData model = mapper.toModel(userDto);
        UserData loginUser = null;
        System.out.println("Role: " + role + " " + model);
        if (Role.DOCTOR.name().equals(role)) {
            System.out.println("Are role doctor" + model);
            loginUser = service.loginDoctor(model);
        } else if (Role.PACIENT.name().equals(role)) {
            System.out.println("Are role pacient" + model);
            loginUser = service.loginPacient(model);
        }
        if (Objects.isNull(loginUser)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid User Name or Password");
        }
        return mapper.toDto(loginUser);
    }
}
