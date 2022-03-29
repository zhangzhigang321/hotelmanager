package com.hotel.entity;

public class BookingInfo {
    private Guest guest;
    private int roomNo;
    private String orderDate;

    public BookingInfo(Guest guest, int roomNo, String orderDate) {
        this.guest=guest;
        this.roomNo = roomNo;
        this.orderDate = orderDate;
    }

    public BookingInfo(String orderDate){
        this.orderDate = orderDate;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj){
            return true;
        }
        if (obj==null){
            return false;
        }
        if (obj instanceof BookingInfo){
            BookingInfo other = (BookingInfo) obj;
            String date1 = this.orderDate;
            String date2 = other.getOrderDate();
            if(date1.equals(date2)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (orderDate == null ? 0 : orderDate.hashCode());
        return result;
    }
}
