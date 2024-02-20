package com.hotel.service.services;

import com.hotel.service.exception.ResourceNotFoundException;
import com.hotel.service.model.Hotel;
import com.hotel.service.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HotelServicelImple implements HotelServices{
    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel create(Hotel hotel) {
       return  this.hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return this.hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(String hotelId) {
        return this.hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("hotel with given id not found"));
    }
}
