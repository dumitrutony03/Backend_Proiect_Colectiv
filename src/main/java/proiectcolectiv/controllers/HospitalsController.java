package proiectcolectiv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import proiectcolectiv.dto.HospitalsDto;
import proiectcolectiv.dto.ReviewsDto;
import proiectcolectiv.mapper.MyMapper;
import proiectcolectiv.model.Reviews;
import proiectcolectiv.service.HospitalsService;
import proiectcolectiv.model.Hospitals;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hospitals")
public class HospitalsController {
    @Autowired
    private HospitalsService service;
    @Autowired
    private MyMapper mapper;

    @PostMapping(value = "/")
    public HospitalsDto addHospital(@RequestBody HospitalsDto hospitalDTO) {
        // Replace hyphens with spaces in the hospital name
        hospitalDTO.setName(hospitalDTO.getName().replace("-", " "));
        if (!service.checkHospitalExists(hospitalDTO.getName())) {
            Hospitals model = mapper.toModel(hospitalDTO);
            model.setId(service.getLastId() + 1);
            Hospitals savedModel = service.save(model);
            return mapper.toDto(savedModel);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already exists");
        }
    }
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HospitalsDto> getAllHospitals() {
        List<Hospitals> hospitals = service.findAll();
        return hospitals.stream().map(elem -> mapper.toDto(elem)).collect(Collectors.toList());
    }

    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HospitalsDto> getHospitalByName(@PathVariable String name) {
        // Replace hyphens with spaces in the hospital name
        name = name.replace("-", " ");
        Hospitals hospital = service.findByName(name);
        if (Objects.isNull(hospital)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(mapper.toDto(hospital), HttpStatus.OK);
    }

    @PatchMapping(value = "/update/{name}")
    public ResponseEntity<HospitalsDto> partialUpdateHospital(@PathVariable String name, @RequestBody HospitalsDto hospitalsDto) {
        // Replace hyphens with spaces in the hospital name
        name = name.replace("-", " ");
        Hospitals existingHospital = service.findByName(name);

        if (existingHospital == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (hospitalsDto.getAdress() != null) {
            existingHospital.setAdress(hospitalsDto.getAdress());
        }
        if (hospitalsDto.getLatitude() != 0) {
            existingHospital.setLatitude(hospitalsDto.getLatitude());
        }
        if (hospitalsDto.getLongitude() != 0) {
            existingHospital.setLongitude(hospitalsDto.getLongitude());
        }
        if (hospitalsDto.getReviews() != null) {
            existingHospital.setReviews(mapper.toModelList(hospitalsDto.getReviews()));
        }

        Hospitals updatedHospital = service.save(existingHospital);
        return new ResponseEntity<>(mapper.toDto(updatedHospital), HttpStatus.OK);
    }
    @DeleteMapping(value = "/delete/{name}")
    public ResponseEntity<Void> deleteHospital(@PathVariable String name) {
        // Replace hyphens with spaces in the hospital name
        name = name.replace("-", " ");
        Hospitals hospital = service.findByName(name);

        if (hospital == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        service.delete(hospital);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
