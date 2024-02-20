package com.user.service.controller;

import com.user.service.model.User;
import com.user.service.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = this.userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);

    }

    int retryCount = 1;

    @GetMapping("/{userId}")
//    @CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallBack")
   // @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallBack")
    @RateLimiter(name = "userratelimiter",fallbackMethod = "ratingHotelFallBack")
    public ResponseEntity<User> getSingleUser(@PathVariable("userId") String userId) {
        logger.info("Retry Count {}", retryCount);

        retryCount++;
        User user = this.userService.getUserById(userId);


        return ResponseEntity.ok(user);
    }


    public ResponseEntity<User> ratingHotelFallBack(String userId, Exception ex) {
            ex.printStackTrace();
        logger.info("The FallBack is Executed Services is down", ex.getMessage());

        User user = User.builder().name("Dammy").email("Damy@gamil.com").about("helooo").build();
        return ResponseEntity.ok(user);

    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getrAllUser() {
        List<User> allUser = this.userService.getAllUser();
        return ResponseEntity.ok(allUser);

    }

}
