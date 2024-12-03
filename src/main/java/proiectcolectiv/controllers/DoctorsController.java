package proiectcolectiv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiectcolectiv.dto.DoctorsDto;
import proiectcolectiv.mapper.MyMapper;
import proiectcolectiv.model.Doctors;
import proiectcolectiv.model.Hospitals;
import proiectcolectiv.service.DoctorsService;
import proiectcolectiv.service.HospitalsService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctors")
public class DoctorsController {

    @Autowired
    private DoctorsService service;

    @Autowired
    private MyMapper mapper;

    @PostMapping(value = "/")
    public DoctorsDto addDoctors(@RequestBody DoctorsDto doctorsDto) {
        //Cand inregistram un doctor, o sa ii dam doar username si parola.
        // Pacientii si review urile le facem cu update.

        System.out.println("a intrat in addDoctors");
        Doctors model = mapper.toModel(doctorsDto);
        if(!service.checkDoctorExists(model)){
            model.setId(service.getLasId()+1);
            Doctors savedModel = service.save(model);
            return mapper.toDto(savedModel);
        }
        DoctorsDto dto = new DoctorsDto();
        return new ResponseEntity<>(dto, HttpStatus.ALREADY_REPORTED).getBody();
     }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DoctorsDto> getAllDoctors() {
        List<Doctors> users = service.findAll();
        return users.stream().map(elem -> mapper.toDto(elem)).collect(Collectors.toList());
    }

}
