package proiectcolectiv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import proiectcolectiv.dto.ReviewsDto;
import proiectcolectiv.mapper.MyMapper;
import proiectcolectiv.model.Reviews;
import proiectcolectiv.service.ReviewsService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {

    @Autowired
    private ReviewsService service;

    @Autowired
    private MyMapper mapper;

    /**
     * Add a new review.
     */
    @PostMapping(value = "/")
    public ResponseEntity<ReviewsDto> addReviews(@RequestBody ReviewsDto reviewsDto) {

        Reviews reviews = mapper.toModel(reviewsDto);
        reviews.setId(service.getLastId() + 1);
        Reviews savedModel = service.saveReview(reviews);
        return new ResponseEntity<>(mapper.toDto(savedModel), HttpStatus.CREATED);
    }

    /**
     * Get all reviews.
     */
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReviewsDto> getAllReviews() {
        List<Reviews> reviews = service.findAll();
        return reviews.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    /**
     * Update a review.
     */
    @PatchMapping(value = "/update/{id}")
    public ResponseEntity<ReviewsDto> updateReviews(@PathVariable int id, @RequestBody ReviewsDto reviewsDto) {
        Optional<Reviews> optionalReview = service.findReviewById(id);

        if (optionalReview.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Reviews reviews = optionalReview.get();
        if (reviewsDto.getReview_text() != null) {
            reviews.setReview_text(reviewsDto.getReview_text());
        }
        if (reviewsDto.getRating() > 0) { // Assuming rating is between 0 and 5
            reviews.setRating(reviewsDto.getRating());
        }

        Reviews updatedReview = service.saveReview(reviews);
        return new ResponseEntity<>(mapper.toDto(updatedReview), HttpStatus.OK);
    }

    /**
     * Delete a review.
     */
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable int id) {
        Optional<Reviews> optionalReview = service.findReviewById(id);

        if (optionalReview.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found.");
        }

        service.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //
    //
}
