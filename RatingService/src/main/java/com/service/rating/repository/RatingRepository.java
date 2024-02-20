package com.service.rating.repository;

import com.service.rating.model.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating,String> {

    //custome find method

    public List<Rating> findByUserId(String userId);


    public List<Rating> findByHotelId(String hotelId);


}
