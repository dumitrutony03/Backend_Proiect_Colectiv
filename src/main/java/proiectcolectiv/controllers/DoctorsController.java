package proiectcolectiv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import proiectcolectiv.dto.AddHospitalDto;
import proiectcolectiv.dto.DoctorsDto;
import proiectcolectiv.dto.ReviewsDto;
import proiectcolectiv.dto.UserDataDto;
import proiectcolectiv.mapper.MyMapper;
import proiectcolectiv.mapper.UserMapper;
import proiectcolectiv.model.Doctors;
import proiectcolectiv.service.DoctorsService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctor")
@CrossOrigin(origins = "http://localhost:5173")
public class DoctorsController {
    @Autowired
    private DoctorsService service;
    @Autowired
    private MyMapper mapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * endpoint pentru a initializa doctorii
     *
     * @return  mesaj confirmare
     */
    @GetMapping(value = "/hardcode")
    public String hardcodeDoctors() {
        service.hardcodeDoctors();
        return "hardcoded";
    }

    /**
     * Register doctor
     *
     * @param doctorsDto
     * @return UserDataDto
     */
    @PostMapping(value = "/")
    public UserDataDto addDoctors(@RequestBody UserDataDto doctorsDto) {
        if (!service.checkDoctorExists(doctorsDto.userName)) {
            Doctors doctor = userMapper.toModelDoctors(doctorsDto);
            doctor.setId(service.getLastId() + 1);
            Doctors savedModel = service.save(doctor);

            System.out.println("Doctor saved: " + savedModel);
            return userMapper.toDto(savedModel);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already exists");
        }
    }

    /**
     * Find all doctors
     *
     * @return List<DoctorsDto>
     */
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DoctorsDto> getAllDoctors() {
        List<Doctors> users = service.findAll();
        return users.stream().map(elem -> mapper.toDto(elem)).collect(Collectors.toList());
    }

    /**
     * Update doctor with given hospital
     *
     * @param addHospitalDto
     * @return DoctorsDto
     */
    @PostMapping(value = "/hospital/add")
    public DoctorsDto addHospital(@RequestBody AddHospitalDto addHospitalDto) {
        //TODO check if hospital name exists in DB
        //Cand inregistram un doctor, o sa ii dam doar username si parola.
        // Pacientii si review urile le facem cu update.
        if (service.checkDoctorExists(addHospitalDto.getDoctorUsername())) {
            Doctors model = service.addHospitalNameToDoctor(addHospitalDto.getDoctorUsername(), addHospitalDto.getHospitalName());
            return mapper.toDto(model);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not exists");
        }
    }

    /**
     * Update doctor removing given hospital
     *
     * @param addHospitalDto
     * @return DoctorsDto
     */
    @PostMapping(value = "/hospital/remove")
    public DoctorsDto removeHospital(@RequestBody AddHospitalDto addHospitalDto) {
        //TODO check if hospital name exists in DB
        //Cand inregistram un doctor, o sa ii dam doar username si parola.
        // Pacientii si review urile le facem cu update.
        if (service.checkDoctorExists(addHospitalDto.getDoctorUsername())) {
            Doctors model = service.removeHospitalNameToDoctor(addHospitalDto.getDoctorUsername(), addHospitalDto.getHospitalName());
            return mapper.toDto(model);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not exists");
        }
    }

    @PatchMapping(value = "/review/add/{name}")
    public DoctorsDto addReview(@PathVariable String name, @RequestBody ReviewsDto reviewsDto) {
        if (service.checkDoctorExists(name)) {
            Doctors model = service.addReviewToDoctor(name, reviewsDto.getReview_text(), reviewsDto.getRating());
            return mapper.toDto(model);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not exists");
        }
    }


    /**
     * Gets all doctors from a hospital.
     *
     * @param name the name of the hospital
     * @return a list of doctorDtos
     */
    @GetMapping(value = "/hospital/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DoctorsDto> getDoctorsFromHospital(@PathVariable String name) {
        List<Doctors> doctors = service.getDoctorsFromHospital(name);
        return doctors.stream().map(elem -> mapper.toDto(elem)).collect(Collectors.toList());
    }
    @GetMapping(value = "/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DoctorsDto> getDoctors(@PathVariable String userName) {
        Doctors doctor = service.findByUserName(userName);
        if (Objects.isNull(doctor)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(mapper.toDto(doctor), HttpStatus.OK);
     }
}
