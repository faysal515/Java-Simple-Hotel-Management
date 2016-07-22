package I3.Classes;

/**
 *
 * @author Faysal Ahmed
 */
public class Room {
    private int room_id;
    private String room_no;
    private int bed_number;
    
    private boolean hasTV;
    private boolean hasWIFI;
    private boolean hasGizer;
    private boolean hasPhone;
    
    
    private RoomFare room_class;
    
    
    public Room(String roomNo)
    {
        room_no = roomNo;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getRoom_no() {
        return room_no;
    }

    public void setRoom_no(String room_no) {
        this.room_no = room_no;
    }

    public int getBed_number() {
        return bed_number;
    }

    public void setBed_number(int bed_number) {
        this.bed_number = bed_number;
    }

    public boolean isHasTV() {
        return hasTV;
    }

    public void setHasTV(boolean hasTV) {
        this.hasTV = hasTV;
    }

    public boolean isHasWIFI() {
        return hasWIFI;
    }

    public void setHasWIFI(boolean hasWIFI) {
        this.hasWIFI = hasWIFI;
    }

    public boolean isHasGizer() {
        return hasGizer;
    }

    public void setHasGizer(boolean hasGizer) {
        this.hasGizer = hasGizer;
    }

    public boolean isHasPhone() {
        return hasPhone;
    }

    public void setHasPhone(boolean hasPhone) {
        this.hasPhone = hasPhone;
    }


    public RoomFare getRoom_class() {
        return room_class;
    }

    public void setRoom_class(RoomFare room_class) {
        this.room_class = room_class;
    }

  
    
    
    
    
}
