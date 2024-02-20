package com.service.rating.services;

import com.service.rating.model.Rating;
import com.service.rating.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HotelServiceImple implements RatingServices{
    @Autowired
    private RatingRepository ratingRepository;
    @Override
    public Rating create(Rating rating) {
        return this.ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
        return this.ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return this.ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return this.ratingRepository.findByHotelId(hotelId);
    }
}
