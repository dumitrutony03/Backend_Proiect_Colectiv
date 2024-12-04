package proiectcolectiv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import proiectcolectiv.dto.UserDataDto;
import proiectcolectiv.model.Doctors;

import java.util.List;

@Service
public class DoctorsService {
    @Autowired
    MongoTemplate mt;

    public Doctors save(Doctors doctors) {
        return mt.save(doctors);
    }

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

    public Doctors findByUserName(String userName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        return mt.findOne(query, Doctors.class);
    }

    public List<Doctors> findAll() {
        return mt.findAll(Doctors.class);
    }

    //exists->return true, not exists->return false
    public boolean checkDoctorExists(UserDataDto model) {
        Doctors doctor = findByUserName(model.userName);
        if ( doctor== null) {
            return false;
        }
        String name = doctor.getUserName();
        boolean val = name.isEmpty();
        if (!val) {
            return true;
        }
        return true;
    }
}
