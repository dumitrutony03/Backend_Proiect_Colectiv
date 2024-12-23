package proiectcolectiv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import proiectcolectiv.model.Reviews;

import java.util.List;

@Service
public class ReviewsService {
    @Autowired
    MongoTemplate mt;

    public  Reviews save( Reviews  reviews) {
        return mt.save(reviews);
    }

    public int getLasId() {
        // return last ID
        int lastID;
        List<Reviews> list = findAll();
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

    public List<Reviews> findAll() {
        return mt.findAll(Reviews.class);
    }
}
