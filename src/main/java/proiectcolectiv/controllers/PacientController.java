package proiectcolectiv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import proiectcolectiv.dto.PacientDto;
import proiectcolectiv.dto.UserDataDto;
import proiectcolectiv.mapper.MyMapper;
import proiectcolectiv.mapper.UserMapper;
import proiectcolectiv.model.Pacient;
import proiectcolectiv.service.PacientService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pacient")
@CrossOrigin(origins = "http://localhost:5174")
public class PacientController {

    @Autowired
    private PacientService service;
    @Autowired
    private MyMapper mapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * Register pacient
     *
     * @param pacientDto
     * @return PacientDto
     */
    @PostMapping(value = "/")
    public PacientDto addPacient(@RequestBody UserDataDto pacientDto) {
        System.out.println(pacientDto.userName + " " + pacientDto.password);
        if (!service.checkPacientExists(pacientDto)) {
            Pacient pacient = userMapper.toModelPacient(pacientDto);
            pacient.setId(service.getLasId() + 1);
            Pacient savedModel = service.save(pacient);
            return mapper.toDto(savedModel);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already exists");
        }
    }

    /**
     * Find all pacients
     *
     * @return List<PacientDto>
     */
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PacientDto> getAllUsers() {
        List<Pacient> users = service.findAll();
        return users.stream().map(elem -> mapper.toDto(elem)).collect(Collectors.toList());
    }

    /**
     * add diagnosis to a pacient found by username
     * @param username
     * @param diagnosis
     * @return updated Pacient with a new diagnosis in list
     */
    @PatchMapping(value = "/diagnosis/{username}/{diagnosis}")
    public PacientDto addDiagnostic(@PathVariable String username, @PathVariable String diagnosis) {
        Pacient updatedPacient = service.addDiagnostic(username, diagnosis);

        if (updatedPacient == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }

        return mapper.toDto(updatedPacient);
    }


}
