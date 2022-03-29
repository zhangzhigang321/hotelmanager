package com.hotel.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public enum  Hotel {
    INSTANCE;
    private int roomNos;
    private static ConcurrentHashMap<Room,List<BookingInfo>> rooms;


    void initRooms() {
        rooms = new ConcurrentHashMap<>(roomNos);
        int i=1;
        while (i<=roomNos){
            rooms.put(new Room(i),new ArrayList<>());
            i++;
        }
        System.out.println("Hotel room size:"+rooms.size());
    }

    public ConcurrentHashMap<Room, List<BookingInfo>> getRooms() {
        return rooms;
    }

    public void setRoomNos(int roomNos) {
        this.roomNos = roomNos;
        initRooms();
    }

    public static void main(String[] args) {
        Hotel.INSTANCE.setRoomNos(6);
        Map<Room,List<BookingInfo>> rooms = Hotel.INSTANCE.getRooms();
        System.out.println(rooms.size());

        for(Map.Entry<Room,List<BookingInfo>> entry:rooms.entrySet()){
            List<BookingInfo> infoList = entry.getValue();
        }
        booking(new Guest("andrew"),1,"01-01");
        findAvailableRoomsByDate("01-01");
        List<BookingInfo> list = findAllBookingInfoByName("andrew");
        System.out.println(list.size());

    }

    private static boolean booking(Guest customer, int roomNo, String orderDate) {
        Map<Room,List<BookingInfo>> rooms = Hotel.INSTANCE.getRooms();
        List<BookingInfo> infoList = rooms.get(roomNo);
        for (BookingInfo info:infoList){
            if (orderDate.equals(info.getOrderDate())){
                return false;
            }
        }
        BookingInfo info = new BookingInfo(customer,roomNo,orderDate);
        infoList.add(info);
        return true;
    }

    private static void findAvailableRoomsByDate(String date) {
         List<Room> avalableRooms = new ArrayList<>();
        for(Map.Entry<Room,List<BookingInfo>> entry:rooms.entrySet()){
            List<BookingInfo> infoList = entry.getValue();
            if (!infoList.contains(new BookingInfo(date))){
                avalableRooms.add(entry.getKey());
            }
        }
        System.out.println(avalableRooms.size());
    }

    private static List<BookingInfo> findAllBookingInfoByName(String name) {
        List<BookingInfo> orderList =  new ArrayList<BookingInfo>();
        for(Map.Entry<Room,List<BookingInfo>> entry:rooms.entrySet()){
            List<BookingInfo> infoList = entry.getValue();
            List<BookingInfo> list = infoList.stream().filter(a->(name.equals(a.getGuest().getName()))).collect(Collectors.toList());
            orderList.addAll(list);

        }
        return orderList;
    }
}
