package com.hotel.test;

import com.hotel.api.HotelManager;
import com.hotel.api.HotelManagerImpl;
import com.hotel.entity.BookingInfo;
import com.hotel.entity.Guest;
import com.hotel.entity.Hotel;
import com.hotel.entity.Room;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

public class UnitTest {
    @Test
    public void testHotelMgr(){
        //set hotel room no as 6
        Hotel.INSTANCE.setRoomNos(6);
        HotelManager hotelMgr = new HotelManagerImpl();
        //1. Test storing booking info
        System.out.println("------1. Test storing booking info:");
        hotelMgr.booking(new Guest("Andrew"),3,"01-01");
        hotelMgr.booking(new Guest("Andrew"),3,"01-03");
        hotelMgr.booking(new Guest("Tom"),4,"01-01");
        hotelMgr.booking(new Guest("Jerry"),4,"01-01");
        hotelMgr.booking(new Guest("Lucy"),4,"01-02");
        hotelMgr.booking(new Guest("Lily"),9,"01-02");
        //2. Test finding available Rooms by Date
        System.out.println("------2. Test finding available Rooms by Date:");
        List<Room> rooms01 = hotelMgr.findAvailableRoomsByDate("01-01");
        rooms01.stream().sorted(Comparator.comparing(Room::getNo)).forEach(room->{
            System.out.print(room.getNo()+", ");
        });
        List<Room> rooms02 = hotelMgr.findAvailableRoomsByDate("01-02");
        rooms02.stream().sorted(Comparator.comparing(Room::getNo)).forEach(room->{
            System.out.print(room.getNo()+", ");
        });
        //3. Test finding all booking info by name
        System.out.println("\n------3. Test finding all booking info by name:");
        List<BookingInfo> infosAndrew = hotelMgr.findAllBookingInfoByName("Andrew");
        infosAndrew.stream().forEach(info->{
            System.out.print("["+info.getOrderDate()+", room no: "+info.getRoomNo()+"] ");
        });
        System.out.println();
        List<BookingInfo> infosTom = hotelMgr.findAllBookingInfoByName("Tom");
        infosTom.stream().forEach(info->{
            System.out.print("["+info.getOrderDate()+", room no: "+info.getRoomNo()+"] ");
        });
    }
}
