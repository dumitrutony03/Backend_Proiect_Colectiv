package proiectcolectiv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import proiectcolectiv.dto.*;
import proiectcolectiv.mapper.MyMapper;
import proiectcolectiv.model.Appointments;
import proiectcolectiv.service.AppointmentsService;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/appointment")
public class AppointmentsController {
    @Autowired
    AppointmentsService appointmentsService;
    @Autowired
    MyMapper mapper;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Add appointment wich involves an existing pacient and an existing doctor
     *
     * @param appointmentsDto
     * @return AppointmentsDto
     */
    @PostMapping(value = "/")
    public AppointmentsDto addAppointment(@RequestBody AppointmentsDto appointmentsDto) {
        if (appointmentsService.check(appointmentsDto.getDoctorUsername(), appointmentsDto.getPacientUsername())) {
            try {
                String date = checkDate(appointmentsDto.getDate());//verificam daca e data valida
                String begin = checkTime(appointmentsDto.getBegin());
                String end = checkTime(appointmentsDto.getEnd());
                if (date == null || begin == null || end == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date or time");
                }
                Appointments model = new Appointments();
                model.setDoctorUsername(appointmentsDto.getDoctorUsername());
                model.setPacientUsername(appointmentsDto.getPacientUsername());
                model.setDate(date);
                model.setBegin(begin);
                model.setEnd(end);
                //checking if model already exists in db
                if (!appointmentsService.checkAppointmentExists(model)) {
                    int lastId = appointmentsService.getLasId();
                    model.setId(lastId + 1);
                    Appointments savedModel = appointmentsService.save(model);
                    return mapper.toDto(savedModel);
                } else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already exists");
                }
            } catch (DateTimeException e) {
                System.out.println(e.getMessage());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date or time");
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid pacient or doctor");
    }

    /**
     * Find all appointments
     *
     * @return List<AppointmentsDto>
     */
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AppointmentsDto> getAllAppointment() {
        List<Appointments> appointments = appointmentsService.findAll();
        return appointments.stream().map(elem -> mapper.toDto(elem)).collect(Collectors.toList());
    }

    /**
     * Filter appointments by doctor, startDate, endDate of appointments
     *
     * @param model
     * @param doctor
     * @param startDate
     * @param endDate
     * @return List<AppointmentsDto>
     */
    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AppointmentsDto> filterAppointmentsByDoctor(Model model,
                                                            @RequestParam String doctor,
                                                            @RequestParam String startDate,
                                                            @RequestParam String endDate) {
        if (doctor.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing doctor");
        }
        if (appointmentsService.check(doctor)) {
            try {
                String start = null;
                if (!startDate.isEmpty()) {
                    start = startDate;
                }
                String end = null;
                if (!endDate.isEmpty()) {
                    end = endDate;
                }
                String data1 = checkDate(start);//verificam daca e data valida
                String data2 = checkDate(end);//verificam daca e data valida
                return appointmentsService
                        .filterAppointmentsByDoctorAndDate(doctor, data1, data2)
                        .stream()
                        .map(elem -> mapper.toDto(elem))
                        .collect(Collectors
                                .toList());
            } catch (DateTimeException e) {
                System.out.println(e.getMessage());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date or time");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid doctor");
        }
    }

    /**
     * Check given date is a date format
     *
     * @param date
     * @return String
     */
    private String checkDate(String date) {
        if (date != null) {
            LocalDate begin = LocalDate.parse(date, dateFormatter);
            return begin.format(dateFormatter);
        }
        return null;
    }

    /**
     * Check given time is a time format
     *
     * @param time
     * @return String
     */
    private String checkTime(String time) {
        if (time != null) {
            LocalTime myTime = LocalTime.parse(time, timeFormatter);
            return myTime.format(timeFormatter);
        }
        return null;
    }
}
