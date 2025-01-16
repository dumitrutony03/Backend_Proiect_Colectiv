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

    /**
     * Saves an appointment to the database.
     *
     * @param appointments the appointment to save
     * @return the saved appointment
     */
    public Appointments save(Appointments appointments) {
        return mt.save(appointments);
    }

    /**
     * Retrieves the last appointment ID from the database.
     *
     * @return the last appointment ID
     */
    public int getLasId() {
        int lastID;
        List<Appointments> list = findAll();
        int size = list.size();
        System.out.println("size is: " + size);

        if (size == 0) {
            lastID = 0;
        } else {
            lastID = list.stream().toList().get(size - 1).getId();
        }
        System.out.println("Last id is: " + lastID);
        return lastID;
    }

    /**
     * Checks if both the doctor and patient exist based on their usernames.
     *
     * @param doctorUsername the doctor's username
     * @param pacientUsername the patient's username
     * @return true if both exist, false otherwise
     */
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

    /**
     * Checks if the doctor exists based on their username.
     *
     * @param doctorUsername the doctor's username
     * @return true if the doctor exists, false otherwise
     */
    public boolean check(String doctorUsername) {
        var doctor = doctorsService.findByUserName(doctorUsername);

        if (doctor == null) {
            return false;
        }

        return true;
    }

    /**
     * Finds an appointment by its details.
     *
     * @param appointment the appointment details to search for
     * @return the found appointment or null if not found
     */
    public Appointments findByAppointment(Appointments appointment) {
        Query query = new Query();
        query.addCriteria(where("doctorUsername").is(appointment.getDoctorUsername()));
        query.addCriteria(where("pacientUsername").is(appointment.getPacientUsername()));
        query.addCriteria(where("date").is(appointment.getDate()));
        query.addCriteria(where("begin").is(appointment.getBegin()));
        query.addCriteria(where("end").is(appointment.getEnd()));

        return mt.findOne(query, Appointments.class);
    }

    /**
     * Retrieves all appointments from the database.
     *
     * @return a list of all appointments
     */
    public List<Appointments> findAll() {
        return mt.findAll(Appointments.class);
    }

    /**
     * Filters appointments by a specific appointment's details.
     *
     * @param appointment the appointment details to filter by
     * @return a list of matching appointments
     */
    public List<Appointments> filterAppointmentsByAppointment(Appointments appointment) {
        var allAppointments = findAll();
        List<Appointments> appointments = null;
        allAppointments.forEach(e -> {
            boolean b = Objects.equals(e, appointment);
            if (appointments != null) {
                appointments.add(e);
            }
        });
        return appointments;
    }

    /**
     * Filters appointments by doctor and date range.
     *
     * @param doctor the doctor's username
     * @param beginDate the start date of the range
     * @param endDate the end date of the range
     * @return a list of matching appointments
     */
    public List<Appointments> filterAppointments(String doctor, String beginDate, String endDate) {
        return null;
    }

    /**
     * Checks if an appointment exists in the database.
     *
     * @param model the appointment to check
     * @return true if the appointment exists, false otherwise
     */
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

    /**
     * Filters appointments by doctor.
     *
     * @param doctor the doctor's username
     * @return a list of appointments for the specified doctor
     */
    public List<Appointments> filterAppointmentsByDoctor(String doctor) {
        try {
            Query query = getQueryByDoctorUsername(doctor);
            return mt.find(query, Appointments.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Filters appointments by doctor and a date range.
     *
     * @param doctor the doctor's username
     * @param startDate the start date of the range
     * @param endDate the end date of the range
     * @return a list of appointments for the specified doctor within the date range
     */
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

    /**
     * Creates a query to find appointments by doctor username.
     *
     * @param doctor the doctor's username
     * @return a query to find appointments for the specified doctor
     */
    private static Query getQueryByDoctorUsername(String doctor) {
        Query query = new Query();
        query.addCriteria(where("doctorUsername")
                .is(doctor));
        return query;
    }

    /**
     * Creates a query to find appointments by doctor and date range.
     *
     * @param doctor the doctor's username
     * @param startDate the start date of the range
     * @param endDate the end date of the range
     * @return a query to find appointments for the specified doctor within the date range
     */
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
