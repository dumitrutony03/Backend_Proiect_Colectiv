package proiectcolectiv.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import proiectcolectiv.model.Doctors;
import proiectcolectiv.model.HospitalRoot;
import proiectcolectiv.model.Hospitals;
import org.springframework.data.mongodb.core.query.Query;


import java.util.List;

@Service
public class HospitalsService {
    @Autowired
    MongoTemplate mt;
    @Autowired
    ObjectMapper objectMapper;

    public Hospitals save(Hospitals hospitals) {
        return mt.save(hospitals);
    }

    public Hospitals findByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return mt.findOne(query, Hospitals.class);
    }

    public int getLastId() {
        // return last ID
        int lastID;
        List<Hospitals> list = findAll();
        int size = list.size();
        System.out.println("size is: " + size);

        if (size == 0) {
            lastID = 0;
        } else {
            lastID = list.stream().toList().get(size).getId();
        }

        System.out.println("Last id is: " + lastID);
        return lastID;
    }

    public List<Hospitals> findAll() {
        return mt.findAll(Hospitals.class);
    }

    public void deleteByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        mt.remove(query, Hospitals.class);
    }

    public Hospitals updateByName(String name, String newAddress, List<Double> newCoordinates) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));

        Update update = new Update();
        update.set("address", newAddress);
        update.set("coordinates", newCoordinates);

        return mt.findAndModify(query, update, Hospitals.class);
    }

    /*
     * Saves from given json file
     * */
    public void saveHospitals(String json) {
        try {
            HospitalRoot val = objectMapper.readValue(json, HospitalRoot.class);
            val.getHospitals().forEach(this::save);

        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
    }

    public Hospitals update(Hospitals hospital) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(hospital.getName()));

        Update update = new Update();
        update.set("address", hospital.getAdress());
        update.set("name", hospital.getName());
        update.set("latitude", hospital.getName());
        update.set("longitude", hospital.getName());
        update.set("reviews", hospital.getReviews());

        return mt.findAndModify(query, update, Hospitals.class);
    }

    public void delete(Hospitals hospital) {
        mt.remove(hospital);
    }

    public boolean checkHospitalExists(String name) {
        Hospitals hospital = findByName( name);
        if ( hospital== null) {
            return false;
        }

        String namec = hospital.getName();
        boolean val = namec.isEmpty();
        if (!val) {
            return true;
        }
        return true;
    }
}
