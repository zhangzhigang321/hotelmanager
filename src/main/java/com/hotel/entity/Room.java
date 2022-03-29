package com.hotel.entity;

public class Room {
    private int no;//roomNo

    public Room(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + result;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj){
            return true;
        }
        if (obj==null){
            return false;
        }
        if (obj instanceof Room){
            Room other = (Room) obj;
            int no1 = this.no;
            int no2 = other.getNo();
            if(no1==no2){
                return true;
            }
        }
        return false;
    }
}
