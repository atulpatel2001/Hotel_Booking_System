package com.service.rating.services;

import com.service.rating.model.Rating;

import java.util.List;

public interface RatingServices {


   public Rating create(Rating rating);


   List<Rating>  getRatings();

   List<Rating> getRatingByUserId(String userId);

   List<Rating> getRatingByHotelId(String hotelId);


}
