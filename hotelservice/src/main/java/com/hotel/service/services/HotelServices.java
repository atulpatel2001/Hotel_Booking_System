package com.hotel.service.services;

import com.hotel.service.model.Hotel;
import  java.util.List;
public interface HotelServices {

    public Hotel create(Hotel hotel);

    public List<Hotel> getAll();

    public Hotel getHotelById(String hotelId);

}
