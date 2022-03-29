package com.hotel.api;

import com.hotel.entity.BookingInfo;
import com.hotel.entity.Guest;
import com.hotel.entity.Room;

import java.util.List;

public interface HotelManager {

    boolean booking(Guest customer, int roomNo, String orderDate);

    List<Room> findAvailableRoomsByDate(String date);

    List<BookingInfo> findAllBookingInfoByName(String name);
}
