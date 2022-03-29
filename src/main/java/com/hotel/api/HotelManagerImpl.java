package com.hotel.api;

import com.hotel.entity.BookingInfo;
import com.hotel.entity.Guest;
import com.hotel.entity.Hotel;
import com.hotel.entity.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class HotelManagerImpl implements HotelManager {

    @Override
    public boolean booking(Guest guest, int roomNo, String orderDate) {
        Room room = new Room(roomNo);
        ConcurrentHashMap<Room,List<BookingInfo>> rooms = Hotel.INSTANCE.getRooms();
        List<BookingInfo> infoList = rooms.get(room);
        if (null==infoList){
            System.out.println(guest.getName()+", booking result: fail, room no does not exists");
            return false;
        }
        if (!infoList.isEmpty()) {
            for (BookingInfo info : infoList) {
                if (orderDate.equals(info.getOrderDate())) {
                    System.out.println(guest.getName()+", booking result: fail, "+orderDate+" room not available");
                    return false;
                }
            }
        }
        BookingInfo info = new BookingInfo(guest,roomNo,orderDate);
        infoList.add(info);
        rooms.put(room,infoList);
        System.out.println(guest.getName()+", booking result: success, booking info:"+orderDate+",room no:"+roomNo);
        return true;
    }

    @Override
    public List<Room> findAvailableRoomsByDate(String date) {
        System.out.print("Date "+date+", available rooms:");
        Hotel hotel = Hotel.INSTANCE;
        ConcurrentHashMap<Room,List<BookingInfo>> rooms = hotel.getRooms();
        List<Room> availableRooms = rooms.entrySet().stream().filter(e->{
            return !(e.getValue()).contains(new BookingInfo(date));
        }).map(e->{return e.getKey();}).collect(Collectors.toList());
        return availableRooms;
    }

    @Override
    public List<BookingInfo> findAllBookingInfoByName(String name) {
        System.out.println("Guest:"+name+", booking info:");
        Hotel hotel = Hotel.INSTANCE;
        Map<Room,List<BookingInfo>> rooms = hotel.getRooms();
        List<BookingInfo> orderList =  new ArrayList<BookingInfo>();
        for(Map.Entry<Room,List<BookingInfo>> entry:rooms.entrySet()){
            List<BookingInfo> infoList = entry.getValue();
            List<BookingInfo> list = infoList.stream().filter(a->(name.equals(a.getGuest().getName()))).collect(Collectors.toList());
            orderList.addAll(list);

        }
        return orderList;
    }
}
