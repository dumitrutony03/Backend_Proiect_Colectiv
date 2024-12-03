package proiectcolectiv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiectcolectiv.dto.PacientDto;
import proiectcolectiv.mapper.MyMapper;
import proiectcolectiv.model.Pacient;
import proiectcolectiv.service.PacientService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pacient")
public class PacientController {

    @Autowired
    private PacientService service;
    @Autowired
    private MyMapper mapper;

    @PostMapping(value = "/")
    public PacientDto addPacient(@RequestBody PacientDto pacientDto) {
        System.out.println("a intrat in addPacient");
        Pacient model = mapper.toModel(pacientDto);
        if (!service.checkPacientExists(model)) {
            model.setId(service.getLasId() + 1);
            Pacient savedModel = service.save(model);
            return mapper.toDto(savedModel);
        }
        PacientDto dto = new PacientDto();
        return new ResponseEntity<>(dto, HttpStatus.ALREADY_REPORTED).getBody();
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PacientDto> getAllUsers() {
        List<Pacient> users = service.findAll();
        return users.stream().map(elem -> mapper.toDto(elem)).collect(Collectors.toList());
    }
}
