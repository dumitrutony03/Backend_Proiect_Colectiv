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

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Saves a review to the database.
     *
     * @param review the review to save
     * @return the saved review
     */
    public Reviews saveReview(Reviews review) {
        return mongoTemplate.save(review);
    }

    /**
     * Retrieves all reviews from the database.
     *
     * @return a list of all reviews
     */
    public List<Reviews> findAll() {
        return mongoTemplate.findAll(Reviews.class);
    }

    /**
     * Finds a review by its ID.
     *
     * @param id the ID of the review
     * @return an Optional containing the review if found, or an empty Optional if not
     */
    public Optional<Reviews> findReviewById(int id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Reviews review = mongoTemplate.findOne(query, Reviews.class);
        return Optional.ofNullable(review);
    }

    /**
     * Updates an existing review based on its ID.
     *
     * @param id the ID of the review to update
     * @param reviewDetails the new details for the review
     * @return the updated review
     * @throws RuntimeException if the review with the specified ID does not exist
     */
    public Reviews updateReview(int id, Reviews reviewDetails) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        // Find the existing review
        Reviews existingReview = mongoTemplate.findOne(query, Reviews.class);
        if (existingReview != null) {
            Update update = new Update();
            update.set("review_text", reviewDetails.getReview_text());
            update.set("rating", reviewDetails.getRating());
            mongoTemplate.updateFirst(query, update, Reviews.class);

            // Update and return the review
            existingReview.setReview_text(reviewDetails.getReview_text());
            existingReview.setRating(reviewDetails.getRating());
            return existingReview;
        } else {
            throw new RuntimeException("Review not found with id: " + id);
        }
    }

    /**
     * Deletes a review by its ID.
     *
     * @param id the ID of the review to delete
     */
    public void deleteReview(int id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Reviews.class);
    }

    /**
     * Checks if a review exists by its ID.
     *
     * @param id the ID of the review to check
     * @return true if the review exists, otherwise false
     */
    public boolean checkReviewExists(int id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.exists(query, Reviews.class);
    }

    /**
     * Retrieves the last review ID from the database.
     *
     * @return the last review ID
     */
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
