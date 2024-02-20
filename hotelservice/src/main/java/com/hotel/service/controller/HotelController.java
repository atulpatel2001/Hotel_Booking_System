package com.hotel.service.controller;

import com.hotel.service.model.Hotel;
import com.hotel.service.services.HotelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelServices hotelServices;

    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @PostMapping("/")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.hotelServices.create(hotel));
    }


    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotel(@PathVariable("id") String hotelId){
        return ResponseEntity.ok(this.hotelServices.getHotelById(hotelId));
    }

    @GetMapping("/")
    public ResponseEntity<List<Hotel>> getAllHotel(){
        return ResponseEntity.ok(this.hotelServices.getAll());
    }
}
