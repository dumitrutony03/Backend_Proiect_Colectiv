package proiectcolectiv.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import proiectcolectiv.model.HospitalRoot;
import proiectcolectiv.model.Hospitals;

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

    public int getLasId() {
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
//        lastID = list.stream().toList().get(size - 1).getId();
        System.out.println("Last id is: " + lastID);
        return lastID;
    }

    public List<Hospitals> findAll() {
        return mt.findAll(Hospitals.class);
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

}
