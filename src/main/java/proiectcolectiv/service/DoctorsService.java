package proiectcolectiv.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import proiectcolectiv.model.Doctors;
import proiectcolectiv.model.Reviews;

import java.util.ArrayList;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DoctorsService {
    @Autowired
    MongoTemplate mt;

    /**
     * Saves a Doctor to the database.
     *
     * @param doctors the doctor to save
     * @return the saved doctor
     */
    public Doctors save(Doctors doctors) {
        return mt.save(doctors);
    }

    /**
     * Adds a hospital name to the doctor's list of hospitals.
     *
     * @param doctorsName the name of the doctor
     * @param hospitalName the name of the hospital to add
     * @return the updated doctor
     */
    public Doctors addHospitalNameToDoctor(String doctorsName, String hospitalName){
        Doctors doctor = findByUserName(doctorsName);
        doctor.addHosptial(hospitalName);
        return mt.save(doctor);
    }

    /**
     * Removes a hospital name from the doctor's list of hospitals.
     *
     * @param doctorsName the name of the doctor
     * @param hospitalName the name of the hospital to remove
     * @return the updated doctor
     */
    public Doctors removeHospitalNameToDoctor(String doctorsName, String hospitalName){
        Doctors doctor = findByUserName(doctorsName);
        doctor.removeHospital(hospitalName);
        return mt.save(doctor);
    }

    /**
     * Retrieves the last doctor's ID from the database.
     *
     * @return the last doctor ID
     */
    public int getLastId() {
        int lastID;
        List<Doctors> list = findAll();
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
     * Finds a doctor by their username.
     *
     * @param userName the username of the doctor
     * @return the doctor if found, otherwise null
     */
    public Doctors findByUserName(String userName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        return mt.findOne(query, Doctors.class);
    }

    /**
     * Retrieves all doctors from the database.
     *
     * @return a list of all doctors
     */
    public List<Doctors> findAll() {
        return mt.findAll(Doctors.class);
    }

    /**
     * Checks if a doctor with the given username exists in the database.
     *
     * @param userName the username of the doctor
     * @return true if the doctor exists, otherwise false
     */
    public boolean checkDoctorExists(String userName) {
        Doctors doctor = findByUserName(userName);
        if (doctor == null) {
            return false;
        }
        String name = doctor.getUserName();
        boolean val = name.isEmpty();
        if (!val) {
            return true;
        }
        return true;
    }

    public Doctors addReviewToDoctor(String doctorsName, String review_text, float rating){
        Doctors doctor = findByUserName(doctorsName);

        int lastID;
        List<Reviews> list = doctor.getReviews();

        int size = 0;
        if (list != null) {
            size = list.size();
        }
        else doctor.setReviews(new ArrayList<>());

        if (size == 0) {
            lastID = 0;
        } else {
            lastID = list.stream().toList().get(size - 1).getId();
        }

        Reviews reviews = new Reviews(lastID + 1, review_text, rating);
        doctor.addReview(reviews);
        return mt.save(doctor);
    }


    /**
     * Hardcodes doctors into the database from a JSON file.
     * It checks if the database is empty before proceeding.
     */
    public void hardcodeDoctors() {
        if (!findAll().isEmpty()) {
            System.out.println("baza de date nu e goala");
            return;
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            DoctorsWrapper wrapper = objectMapper.readValue(
                    new ClassPathResource("doctori.json").getInputStream(),
                    DoctorsWrapper.class
            );

            List<Doctors> doctorsList = wrapper.getDoctors();

            Set<Integer> uniqueIds = new HashSet<>();
            Set<String> uniqueUsernames = new HashSet<>();

            for (Doctors doctor : doctorsList) {
                // daca id sau userName exista opreste procesul
                if (!uniqueIds.add(doctor.getId()) || !uniqueUsernames.add(doctor.getUserName())) {
                    System.out.println("eroare: doctorul cu ID " + doctor.getId() + " sau userName " + doctor.getUserName() + " este duplicat");
                    return;
                }
            }

            doctorsList.forEach(this::save);
            System.out.println("doctori salvati in db");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("eroare la citire fisier");
        }
    }

    /**
     * Wrapper class to hold a list of doctors.
     */
    public static class DoctorsWrapper {
        private List<Doctors> doctors;

        public List<Doctors> getDoctors() {
            return doctors;
        }

        public void setDoctors(List<Doctors> doctors) {
            this.doctors = doctors;
        }
    }


    public List<Doctors> getDoctorsFromHospital(String hospitalName) {
        String newName = hospitalName.replace("-", " ");
        Query query = new Query();
        query.addCriteria(Criteria.where("hospitals").is(newName));
        return mt.find(query, Doctors.class);
    }
}
