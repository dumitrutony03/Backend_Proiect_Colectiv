package proiectcolectiv.service;

import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import proiectcolectiv.model.Pacient;

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
        int lastID;
        List<Pacient> list = findAll();
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

    public List<Pacient> findAll() {
        return mt.findAll(Pacient.class);
    }
}
