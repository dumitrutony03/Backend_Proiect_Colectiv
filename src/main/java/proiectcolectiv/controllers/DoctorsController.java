package proiectcolectiv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import proiectcolectiv.dto.DoctorsDto;
import proiectcolectiv.dto.UserDataDto;
import proiectcolectiv.mapper.MyMapper;
import proiectcolectiv.mapper.UserMapper;
import proiectcolectiv.model.Doctors;
import proiectcolectiv.service.DoctorsService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctor")
public class DoctorsController {

    @Autowired
    private DoctorsService service;

    @Autowired
    private MyMapper mapper;
    @Autowired
    private UserMapper userMapper;
    @PostMapping(value = "/")
    public UserDataDto addDoctors(@RequestBody UserDataDto doctorsDto) {
        //Cand inregistram un doctor, o sa ii dam doar username si parola.
        // Pacientii si review urile le facem cu update.
        if(!service.checkDoctorExists(doctorsDto)){
            Doctors doctor = userMapper.toModelDoctors(doctorsDto);
            doctor.setId(service.getLasId() + 1);
            Doctors savedModel = service.save(doctor);
            return userMapper.toDto(savedModel);
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already exists");
        }
     }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DoctorsDto> getAllDoctors() {
        List<Doctors> users = service.findAll();
        return users.stream().map(elem -> mapper.toDto(elem)).collect(Collectors.toList());
    }

}
