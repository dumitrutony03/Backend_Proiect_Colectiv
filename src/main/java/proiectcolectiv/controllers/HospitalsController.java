package proiectcolectiv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        // When registering a hospital, we will pass its name, address, coordinates, etc.
        Hospitals model = mapper.toModel(hospitalDTO);
        Hospitals savedModel = service.save(model);
        return mapper.toDto(savedModel);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HospitalsDto> getAllHospitals() {
        List<Hospitals> hospitals = service.findAll();
        return hospitals.stream().map(elem -> mapper.toDto(elem)).collect(Collectors.toList());
    }

    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HospitalsDto> getHospitalByName(@PathVariable String name) {
        Hospitals hospital = service.findByName(name);
        if (Objects.isNull(hospital)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(mapper.toDto(hospital), HttpStatus.OK);
    }

    @PatchMapping(value = "/update/{name}")
    public ResponseEntity<HospitalsDto> partialUpdateHospital(@PathVariable String name, @RequestBody HospitalsDto hospitalsDto) {
        // Find the existing hospital by name
        Hospitals existingHospital = service.findByName(name);

        if (existingHospital == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Update only the fields that are provided in the request
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

        // Save the updated hospital
        Hospitals updatedHospital = service.save(existingHospital);

        // Return the updated hospital
        return new ResponseEntity<>(mapper.toDto(updatedHospital), HttpStatus.OK);
    }


    @DeleteMapping(value = "/delete/{name}")
    public ResponseEntity<Void> deleteHospital(@PathVariable String name) {
        // First, find the hospital by name
        Hospitals hospital = service.findByName(name);

        if (hospital == null) {
            // If the hospital doesn't exist, return a NOT FOUND response
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Delete the hospital
        service.delete(hospital);

        // Return a NO CONTENT response (successful deletion)
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
