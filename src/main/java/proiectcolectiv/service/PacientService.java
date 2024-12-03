package proiectcolectiv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import proiectcolectiv.dto.UserDataDto;
import proiectcolectiv.model.Pacient;
import proiectcolectiv.model.UserData;

import java.util.List;

@Service
public class PacientService {
    @Autowired
    MongoTemplate mt;

    public Pacient save(Pacient pacient) {
        return mt.save(pacient);
    }

    public int getLasId() {
        // return last ID
        int lastID = 0;
        List<Pacient> list = findAll();
        int size = list.size();

        if (size > 0) {
            lastID = list.stream().toList().get(size - 1).getId();
        }
        System.out.println("Last id is: " + lastID);
        return lastID;
    }

    public Pacient findByUserName(String userName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        return mt.findOne(query, Pacient.class);
    }

    public List<Pacient> findAll() {
        return mt.findAll(Pacient.class);
    }

    public boolean checkPacientExists(UserDataDto model) {
        Pacient pacient = findByUserName(model.userName);
        if (pacient == null) {
            return false;
        }
        String name = pacient.getUserName();
        boolean val = name.isEmpty();
        if (!val) {
            return true;
        }
        return true;
    }
}
