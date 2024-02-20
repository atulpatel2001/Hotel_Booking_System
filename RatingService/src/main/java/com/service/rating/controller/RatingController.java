package com.service.rating.controller;

import com.service.rating.model.Rating;
import com.service.rating.services.RatingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    private RatingServices ratingServices;

    @PostMapping("/")
    public ResponseEntity<Rating> create(@RequestBody Rating rating) {

        return ResponseEntity.status(HttpStatus.CREATED).body(this.ratingServices.create(rating));
    }

    @GetMapping("/")
    public ResponseEntity<List<Rating>> getRatings() {
        return ResponseEntity.ok(this.ratingServices.getRatings());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(this.ratingServices.getRatingByUserId(userId));
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.ok(this.ratingServices.getRatingByHotelId(hotelId));
    }
}
