package proiectcolectiv.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import proiectcolectiv.dto.HospitalsDto;
import proiectcolectiv.dto.ReviewsDto;
import proiectcolectiv.mapper.MyMapper;
import proiectcolectiv.model.Hospitals;
import proiectcolectiv.service.HospitalsService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hospitals")
@CrossOrigin(origins = "http://localhost:5173")
public class HospitalsController {
    @Autowired
    private HospitalsService service;
    @Autowired
    private MyMapper mapper;

    /**
     * This method handles adding a new hospital by replacing hyphens with spaces in the hospital name,
     * checking if the hospital already exists, and saving it if it does not.
     *
     * @param hospitalDTO the hospital DTO to be added
     * @return the added hospital DTO
     */
    @PostMapping(value = "/")
    public HospitalsDto addHospital(@RequestBody HospitalsDto hospitalDTO) {
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

    /**
     * Retrieves all hospitals from the database.
     *
     * @return a list of hospital DTOs
     */
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HospitalsDto> getAllHospitals() {
        List<Hospitals> hospitals = service.findAll();
        return hospitals.stream().map(elem -> mapper.toDto(elem)).collect(Collectors.toList());
    }

    /**
     * Retrieves a hospital by its name.
     *
     * @param name the name of the hospital
     * @return the hospital DTO if found, otherwise a NO_CONTENT status
     */
    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HospitalsDto> getHospitalByName(@PathVariable String name) {
        name = name.replace("-", " ");
        Hospitals hospital = service.findByName(name);
        if (Objects.isNull(hospital)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(mapper.toDto(hospital), HttpStatus.OK);
    }

    /**
     * Partially updates the details of an existing hospital.
     *
     * @param name the name of the hospital to be updated
     * @param hospitalsDto the hospital DTO containing updated fields
     * @return the updated hospital DTO
     */
    @PatchMapping(value = "/update/{name}")
    public ResponseEntity<HospitalsDto> partialUpdateHospital(@PathVariable String name, @RequestBody HospitalsDto hospitalsDto) {
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

    /**
     * Deletes a hospital by its name.
     *
     * @param name the name of the hospital to be deleted
     * @return a NO_CONTENT status if successful, otherwise NOT_FOUND
     */
    @DeleteMapping(value = "/delete/{name}")
    public ResponseEntity<Void> deleteHospital(@PathVariable String name) {
        name = name.replace("-", " ");
        Hospitals hospital = service.findByName(name);

        if (hospital == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        service.delete(hospital);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /**
     * Populates the database with hospital data from a JSON file. Deletes all existing hospitals.
     *
     * @return a response indicating the success or failure of the operation
     */

    @PatchMapping(value = "/review/add/{name}")
    public ResponseEntity<HospitalsDto> addReview(@PathVariable String name, @RequestBody ReviewsDto reviewsDto){
        //folosesti requestparam/ pathparam si requestbody
        if (service.checkHospitalExists(name)) {

            Hospitals model = service.addReviewToHospital(name, reviewsDto.getReview_text(), reviewsDto.getRating());
            return new ResponseEntity<>(mapper.toDto(model), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not exists");
        }
    }

    @PostMapping(value = "/hardcode")
    public ResponseEntity<String> populateDB() {
        try {
            File file = new File("spitale.json");
            ObjectMapper objectMapper = new ObjectMapper();

            List<HospitalsDto> hospitalsList = objectMapper.readValue(file, new TypeReference<List<HospitalsDto>>() {
            });

            List<Hospitals> hospitalsFromFile = hospitalsList.stream()
                    .map(mapper::toModel)
                    .toList();

            System.out.println("Deleting all existing hospitals...");

            List<Hospitals> allHopitals = service.findAll();
            for (Hospitals hospital : allHopitals) {
                service.deleteByName(hospital.name);
            }

            int lastId = 1;
            for (Hospitals hospital : hospitalsFromFile) {
                if (!service.checkHospitalExists(hospital.getName())) {
                    hospital.setId(lastId);
                    service.save(hospital);
                    lastId++;
                }
            }
            return new ResponseEntity<>("Database populated with data.", HttpStatus.OK);

        } catch (IOException e) {
            throw new RuntimeException("Error reading or processing the JSON file.", e);
        }
    }
}
