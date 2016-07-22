package I3.Classes;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Faysal Ahmed
 */
public class Booking {
    
   // int CONFIRMED = 0;
   // int RESERVED = 1;   
    
    // required Object
    
    private UserInfo customer;
    ArrayList<Room> rooms;
    
    
    
    private int booking_id;
    private long checkInDateTime;
    private long checkOutDateTime;
    private String bookingType;
    private int person;
    //private int roomsFare;
    
    
    
    public Booking()
    {
        customer = new UserInfo();
        rooms = new ArrayList<>();
        booking_id = -1;
        bookingType = "Reserved";
        
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

   
    
    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }
    
    
    
    
    public void addRoom(String roomNo)
    {
        rooms.add(new Room(roomNo));
        
    }
    
    public void removeRoom(String roomNo)
    {
        for(Room a: rooms)
        {
            if(a.getRoom_no().equals(roomNo))
            {
                rooms.remove(a);
            }
        }
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }
    
    public int getRoomsFare()
    {
        int total = 0;
        for(Room room:rooms)
        {
            total += room.getRoom_class().getPricePerDay();
        }
        return total;
    }

    public UserInfo getCustomer() {
        return customer;
    }

    public void setCustomer(UserInfo customer) {
        this.customer = customer;
    }

    

    public void setCheckOutDateTime(int checkOutDateTime) {
        this.checkOutDateTime = checkOutDateTime;
    }

    public long getCheckInDateTime() {
        return checkInDateTime;
    }

    public void setCheckInDateTime(long checkInDateTime) {
        this.checkInDateTime = checkInDateTime;
    }

    public long getCheckOutDateTime() {
        return checkOutDateTime;
    }

    public void setCheckOutDateTime(long checkOutDateTime) {
        this.checkOutDateTime = checkOutDateTime;
    }

 
    
    
    
            
    
}
