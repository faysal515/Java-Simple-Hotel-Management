package I3.DatabaseOperation;

import I3.Classes.Booking;
import I3.Classes.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class BookingDb {

    Connection conn;
    PreparedStatement statement = null;
    ResultSet result = null;

    public BookingDb() {
        conn = DataBaseConnection.connectTODB();
    }

    public void insertBooking(Booking booking) {
        for (int i = 0; i < booking.getRooms().size(); i++) {
            try {
                String insertQuery = "insert into booking"
                        + "('customer_id','booking_room','guests','check_in','check_out','booking_type','has_checked_out')"
                        + " values("
                        + booking.getCustomer().getCustomer_id()
                        + ",'" + booking.getRooms().get(i).getRoom_no() + "'"
                        + "," + booking.getPerson() + ""
                        + "," + booking.getCheckInDateTime() + ""
                        + "," + booking.getCheckOutDateTime() + ""
                        + ",'" + booking.getBookingType() + "',"
                        + 0
                        + " )";

                // ^^^ 0 for has_checked_out
                statement = conn.prepareStatement(insertQuery);
                //System.out.println(">>>>>>>>>> " + insertQuery);
                statement.execute();

                JOptionPane.showMessageDialog(null, "successfully inserted new Booking");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.toString() + "\n" + "InsertQuery  booking Failed");
            } finally {
                flushStatementOnly();
            }
        }

    }

    public ResultSet getBookingInformation() {
        try {
            String query = "select * from booking";
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\n error coming from returning all booking DB Operation");
        }

        return result;
    }

    public ResultSet getABooking(int bookingId) {
        try {
            String query = "select * from booking where booking_id = " + bookingId;
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\n error coming from returning A booking DB Operation");
        }

        return result;
    }

    public ResultSet bookingsReadyForOrder(String roomName) {
        try {
           // flushAll();
            String query = "select booking_id,booking_room,name from booking join userInfo on booking.customer_id = userInfo.user_id where booking_room like '%" + roomName + "%' and has_checked_out = 0 order by booking_id desc";
            System.out.println(query);
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\n error coming from returning bookingsReadyForOrder method,BookingDb");
        }

        return result;
    }

    public void updateCheckOut(int bookingId, long checkOutTime) {
        try {
            String updateFood = "update booking set has_checked_out= 1, check_out = " + checkOutTime + " where booking_id = " + bookingId;

            // System.out.println(">>>>>>>>>> "+ insertRoomTypeQuery);
            statement = conn.prepareStatement(updateFood);

            statement.execute();

            JOptionPane.showMessageDialog(null, "successfully update Check Out ");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\n" + "updateCheckOut of BookingDB Failed");
        } finally {
            flushStatementOnly();
        }
    }

    public int getRoomPrice(int bookingId) {

        int price = -1;
        try {

            String query = "select price from booking join room on booking_room = room_no join roomType on type= room_class where booking_id=" + bookingId;
            System.out.println(query);
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
            price = result.getInt("price");
            //flushAll();
            System.out.println(price);
            flushAll();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\n error coming from returning price getRoomPrice,bookingDB");
        }

        return price;
    }
    
    public void insertOrder(Order order) {
        try {
            String insertOrder = "insert into orderItem('booking_id','item_food','price','quantity','total') values(" + order.getBookingId() + ",'" + order.getFoodItem() + "'," + order.getPrice() + "," + order.getQuantity() + "," + order.getTotal() + ")";

            statement = conn.prepareStatement(insertOrder);
            System.out.println(">>>>>>>>>> " + insertOrder);
            statement.execute();

            JOptionPane.showMessageDialog(null, "successfully inserted a new Order");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\n" + "Order Failed");
        } finally {
            flushStatementOnly();
        }

    }

    public ResultSet getAllPaymentInfo(int bookingId)
    {
        try {

            String query = "select * from orderItem where booking_id=" + bookingId;
            System.out.println(query);
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
           // price = result.getInt("price");
            //flushAll();
           // System.out.println(price);
           // flushAll();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\n error coming from returning payment getAllPaymentInfo,bookingDB");
        }
        return result;
    }

    public void flushAll() {
        {
            try {
                statement.close();
                result.close();
            } catch (SQLException ex) {
                System.err.print(ex.toString() + " >> CLOSING DB");
            }
        }
    }

    public void flushStatementOnly() {
        {
            try {
                statement.close();
                //conn.close();
            } catch (SQLException ex) {
                System.err.print(ex.toString() + " >> CLOSING DB");
            }
        }
    }

}
