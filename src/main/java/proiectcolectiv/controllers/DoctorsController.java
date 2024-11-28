package proiectcolectiv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import proiectcolectiv.dto.DoctorsDto;
import proiectcolectiv.dto.PacientDto;
import proiectcolectiv.mapper.MyMapper;
import proiectcolectiv.model.Doctors;
import proiectcolectiv.model.Hospitals;
import proiectcolectiv.model.Pacient;
import proiectcolectiv.service.DoctorsService;
import proiectcolectiv.service.HospitalsService;
import proiectcolectiv.service.PacientService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctors")
public class DoctorsController {

    @Autowired
    private DoctorsService service;
    @Autowired
    private HospitalsService hospitalsService;
    @Autowired
    private MyMapper mapper;

    @PostMapping(value = "/")
    public DoctorsDto addDoctors(@RequestBody DoctorsDto doctorsDto) {
        //Cand inregistram un doctor, o sa ii dam doar username si parola.
        // Pacientii si review urile le facem cu update.
        System.out.println("a intrat in addDoctors");
        Doctors model = mapper.toModel(doctorsDto);
        model.setId(service.getLasId()+1);
        Doctors savedModel = service.save(model);
        return mapper.toDto(savedModel);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DoctorsDto> getAllDoctors() {
        List<Doctors> users = service.findAll();
        return users.stream().map(elem -> mapper.toDto(elem)).collect(Collectors.toList());
    }
    @GetMapping(value = "/hospitals", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Hospitals> hospitals() {
        if (hospitalsService.findAll().isEmpty()) {
            String json = "{\n" +
                    "  \"hospitals\": [\n" +
                    "    {\n" +
                    "      \"id\": 1,\n" +
                    "      \"name\": \"Spitalul Clinic Județean de Urgență Cluj-Napoca\",\n" +
                    "      \"adress\": \"Strada Clinicilor, nr. 3-5\",\n" +
                    "      \"latitude\": 46.7659412,\n" +
                    "      \"longitude\": 23.5831731\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 2,\n" +
                    "      \"name\": \"Spitalul Clinic de Recuperare\",\n" +
                    "      \"adress\": \"Strada Viilor, nr. 46-50\",\n" +
                    "      \"latitude\": 46.7549178,\n" +
                    "      \"longitude\": 23.5820064\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 3,\n" +
                    "      \"name\": \"Spitalul Clinic Municipal Cluj-Napoca\",\n" +
                    "      \"adress\": \"Strada Tăbăcarilor, nr. 11\",\n" +
                    "      \"latitude\": 46.7893118,\n" +
                    "      \"longitude\": 23.6061074\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 4,\n" +
                    "      \"name\": \"Spitalul Clinic de Boli Infecțioase\",\n" +
                    "      \"adress\": \"Strada Iuliu Moldovan, nr. 23\",\n" +
                    "      \"latitude\": 46.7638027,\n" +
                    "      \"longitude\": 23.5786053\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 5,\n" +
                    "      \"name\": \"Spitalul Clinic de Pneumoftiziologie Leon Daniello\",\n" +
                    "      \"adress\": \"Strada B.P. Hașdeu, nr. 6\",\n" +
                    "      \"latitude\": 46.7639388,\n" +
                    "      \"longitude\": 23.580387\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"name\": \"Institutul Inimii \\\"Niculae Stancioiu\\\"\",\n" +
                    "      \"adress\": \"Strada Moților, nr. 19-21\",\n" +
                    "      \"latitude\": 46.7669628,\n" +
                    "      \"longitude\": 23.5800418\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 6,\n" +
                    "      \"name\": \"Spitalul Militar de Urgență \\\"Dr. Constantin Papilian\\\"\",\n" +
                    "      \"adress\": \"Strada General Traian Moșoiu, nr. 22\",\n" +
                    "      \"latitude\": 46.7669628,\n" +
                    "      \"longitude\": 23.5800418\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 7,\n" +
                    "      \"name\": \"Spitalul Clinic de Pediatrie 1\",\n" +
                    "      \"adress\": \"Calea Moților, nr. 68\",\n" +
                    "      \"latitude\": 46.7660097,\n" +
                    "      \"longitude\": 23.5761302\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 8,\n" +
                    "      \"name\": \"Spitalul Clinic de Pediatrie 2\",\n" +
                    "      \"adress\": \"Strada Crișan, nr. 3-5\",\n" +
                    "      \"latitude\": 46.777645,\n" +
                    "      \"longitude\": 23.5833794\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 9,\n" +
                    "      \"name\": \"Spitalul Clinic de Obstetrică-Ginecologie \\\"Dominic Stanca\\\"\",\n" +
                    "      \"adress\": \"Bulevardul 21 Decembrie 1989, nr. 57\",\n" +
                    "      \"latitude\": 46.7744383,\n" +
                    "      \"longitude\": 23.5977866\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 10,\n" +
                    "      \"name\": \"Institutul Oncologic \\\"Prof. Dr. Ion Chiricuță\\\"\",\n" +
                    "      \"adress\": \"Strada Republicii, nr. 34-36\",\n" +
                    "      \"latitude\": 46.7634268,\n" +
                    "      \"longitude\": 23.5848328\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 11,\n" +
                    "      \"name\": \"Institutul Regional de Gastroenterologie și Hepatologie \\\"Prof. Dr. Octavian Fodor\\\"\",\n" +
                    "      \"adress\": \"Strada Croitorilor, nr. 19-21\",\n" +
                    "      \"latitude\": 46.7761376,\n" +
                    "      \"longitude\": 23.5898055\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 12,\n" +
                    "      \"name\": \"Spitalul Clinic de Psihiatrie\",\n" +
                    "      \"adress\": \"Strada Decebal, nr. 126\",\n" +
                    "      \"latitude\": 46.7847717,\n" +
                    "      \"longitude\": 23.5911481\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 13,\n" +
                    "      \"name\": \"Spitalul Clinic de Neurologie\",\n" +
                    "      \"adress\": \"Strada Victor Babeș, nr. 43\",\n" +
                    "      \"latitude\": 46.7638293,\n" +
                    "      \"longitude\": 23.5793742\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 14,\n" +
                    "      \"name\": \"Spitalul Clinic de Chirurgie\",\n" +
                    "      \"adress\": \"Strada Croitorilor, nr. 19-21\",\n" +
                    "      \"latitude\": 46.7638293,\n" +
                    "      \"longitude\": 23.5793742\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}\n";
            hospitalsService.saveHospitals(json);
        }
        return hospitalsService.findAll();
    }
}
