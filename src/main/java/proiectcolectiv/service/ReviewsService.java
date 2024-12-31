package proiectcolectiv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import proiectcolectiv.model.Doctors;
import proiectcolectiv.model.Reviews;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewsService {
//    @Autowired
//    MongoTemplate mt;
//
//    public  Reviews save( Reviews  reviews) {
//        return mt.save(reviews);
//    }
//
//    public int getLasId() {
//        // return last ID
//        int lastID;
//        List<Reviews> list = findAll();
//        int size = list.size();
//        System.out.println("size is: " + size);
//
//        if (size == 0) {
//            lastID = 0;
//        } else {
//            lastID = list.stream().toList().get(size).getId();
//        }
////        lastID = list.stream().toList().get(size - 1).getId();
//        System.out.println("Last id is: " + lastID);
//        return lastID;
//    }
//
//    public List<Reviews> findAll() {
//        return mt.findAll(Reviews.class);
//    }

    @Autowired
    private MongoTemplate mongoTemplate;

    // CREATE
    public Reviews saveReview(Reviews review) {
        return mongoTemplate.save(review);
    }

    // READ (all)
    public List<Reviews> findAll() {
        return mongoTemplate.findAll(Reviews.class);
    }

    // READ (by ID)
    public Optional<Reviews> findReviewById(int id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Reviews review = mongoTemplate.findOne(query, Reviews.class);
        return Optional.ofNullable(review);
    }

    // UPDATE
    public Reviews updateReview(int id, Reviews reviewDetails) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        // Găsirea review-ului existent
        Reviews existingReview = mongoTemplate.findOne(query, Reviews.class);
        if (existingReview != null) {
            Update update = new Update();
            update.set("review_text", reviewDetails.getReview_text());
            update.set("rating", reviewDetails.getRating());
            mongoTemplate.updateFirst(query, update, Reviews.class);

            // Returnează obiectul actualizat
            existingReview.setReview_text(reviewDetails.getReview_text());
            existingReview.setRating(reviewDetails.getRating());
            return existingReview;
        } else {
            throw new RuntimeException("Review not found with id: " + id);
        }
    }

    // DELETE
    public void deleteReview(int id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Reviews.class);
    }

    // Verifică dacă un review există după ID
    public boolean checkReviewExists(int id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.exists(query, Reviews.class);
    }

    public int getLastId() {
        int lastID;
        List<Reviews> list = findAll();
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
}
