package proiectcolectiv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import proiectcolectiv.model.Appointments;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class AppointmentsService {
    @Autowired
    MongoTemplate mt;
    @Autowired
    PacientService pacientService;
    @Autowired
    DoctorsService doctorsService;

    public Appointments save(Appointments appointments) {
        return mt.save(appointments);
    }

    public int getLasId() {
        // return last ID
        int lastID;
        List<Appointments> list = findAll();
        int size = list.size();
        System.out.println("size is: " + size);

        if (size == 0) {
            lastID = 0;
        } else {
            lastID = list.stream().toList().get(size - 1).getId();
        }
//        lastID = list.stream().toList().get(size - 1).getId();
        System.out.println("Last id is: " + lastID);
        return lastID;
    }

    public boolean check(String doctorUsername, String pacientUsername) {

        var doctor = doctorsService.findByUserName(doctorUsername);
        var pacient = pacientService.findByUserName(pacientUsername);

        if (doctor == null) {
            return false;
        }
        if (pacient == null) {
            return false;
        }

        return true;
    }

    public boolean check(String doctorUsername) {

        var doctor = doctorsService.findByUserName(doctorUsername);

        if (doctor == null) {
            return false;
        }


        return true;
    }

    public Appointments findByAppointment(Appointments appointment) {
        Query query = new Query();
        query.addCriteria(where("doctorUsername").is(appointment.getDoctorUsername()));
        query.addCriteria(where("pacientUsername").is(appointment.getPacientUsername()));
        query.addCriteria(where("date").is(appointment.getDate()));
        query.addCriteria(where("begin").is(appointment.getBegin()));
        query.addCriteria(where("end").is(appointment.getEnd()));

        return mt.findOne(query, Appointments.class);
    }

    public List<Appointments> findAll() {
        return mt.findAll(Appointments.class);
    }

    public List<Appointments> filterAppointmentsByAppointment(Appointments appointment) {
        var allAppointments = findAll();
        List<Appointments> appointments = null;
        allAppointments.forEach(e -> {
                    boolean b = Objects.equals(e, appointment);
                    if (appointments != null) {
                        appointments.add(e);
                    }
                }
        );
        return appointments;
    }

    public List<Appointments> filterAppointments(String doctor, String beginDate, String endDate) {
        return null;
    }

    public boolean checkAppointmentExists(Appointments model) {
        Appointments appointment = findByAppointment(model);
        if (appointment == null) {
            return false;
        }
        String name = appointment.toString();
        boolean val = name.isEmpty();
        if (!val) {
            return true;
        }
        return true;
    }

    public List<Appointments> filterAppointmentsByDoctor(String doctor) {

        try {
            Query query = getQueryByDoctorUsername(doctor);
            return mt.find(query, Appointments.class);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Appointments> filterAppointmentsByDoctorAndDate(String doctor, String startDate, String endDate) {

        try {
            Query query = getQueryByDoctorAndDate(doctor, startDate, endDate);
            var myList = mt.find(query, Appointments.class);
            return myList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    private static Query getQueryByDoctorUsername(String doctor) {
        Query query = new Query();
        query.addCriteria(where("doctorUsername")
                .is(doctor));
        return query;
    }

    private static Query getQueryByDoctorAndDate(String doctor, String startDate, String endDate) {
        Query query = new Query();

        if (startDate != null && endDate == null) {
            query.addCriteria(where("doctorUsername")
                    .is(doctor)
                    .andOperator(where("date")
                    .gte(startDate)
                    )
            );

        } else if (startDate == null && endDate != null) {
            query.addCriteria(where("doctorUsername")
                    .is(doctor)
                    .andOperator(where("date")
                    .lte(endDate))
                    );

        } else if (startDate != null && endDate != null) {
            query.addCriteria(where("doctorUsername")
                    .is(doctor)
                    .andOperator(
                            where("date").gte(startDate),
                            where("date").lte(endDate)
                    )
            );
        } else {
            query = getQueryByDoctorUsername(doctor);
        }
        return query;
    }
}
