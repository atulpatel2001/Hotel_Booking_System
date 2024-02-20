package com.user.service.service;

import com.user.service.exception.ResourceNotFoundException;
import com.user.service.externalservice.HotelService;
import com.user.service.model.Hotel;
import com.user.service.model.Rating;
import com.user.service.model.User;
import com.user.service.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImple implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImple.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;

    @Override
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id not found on server!!:-" + userId));
        //fetch ranking using Ranking service
        //http://localhost:8083/rating/users/
        Rating[] ratingsOfUser = this.restTemplate.getForObject("http://RATING-SERVICE/rating/users/" + user.getUserId(), Rating[].class);

        List<Rating> list = Arrays.stream(ratingsOfUser).toList();
        List<Rating> ratingList = list.stream().map(rating -> {
            //api service get the hotel
//            ResponseEntity<Hotel> forEntity = this.restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+ rating.getHotelId(),Hotel.class);
//            Hotel hotel = forEntity.getBody();
            Hotel hotel = this.hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;

    }
}
