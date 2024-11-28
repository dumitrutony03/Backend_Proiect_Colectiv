package proiectcolectiv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import proiectcolectiv.model.Appointments;
import proiectcolectiv.model.Doctors;
import proiectcolectiv.model.Pacient;


import java.util.List;

@Service
public class DoctorsService {@Autowired
MongoTemplate mt;
    public Doctors save(Doctors doctors) {
        return mt.save(doctors);
    }

    public int getLasId() {
        int lastID;
        List<Doctors> list = findAll();
        int size = list.size();
        System.out.println("size is: " + size);

        if (size == 0) {
            lastID = 0;
        } else {
            lastID = list.stream().toList().get(size-1).getId();
        }
//        lastID = list.stream().toList().get(size ).getId();
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
}
