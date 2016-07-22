package I3.DatabaseOperation;
import I3.Classes.Food;
import I3.Classes.Item;
import I3.Classes.Room;
import I3.Classes.RoomFare;
import I3.Classes.UserInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Faysal Ahmed
 */
public class DatabaseOperation {

    Connection conn = DataBaseConnection.connectTODB();
    PreparedStatement statement = null;
    ResultSet result = null;

    public void insertCustomer(UserInfo user) throws SQLException {
        try {
            String insertQuery = "insert into userInfo"
                    + "('" + "name" + "'," + "'" + "address" + "','" + "phone" + "','" + "type" + "')"
                    + " values('"
                    + user.getName()
                    + "','" + user.getAddress() + "'"
                    + ",'" + user.getPhone_no() + "'"
                    + ",'" + user.getType() + "'"
                    + ")";

            //System.out.println(">>>>>>>>>> "+ insertQuery);
            statement = conn.prepareStatement(insertQuery);

            statement.execute();

            JOptionPane.showMessageDialog(null, "successfully inserted new Customer");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\n" + "InsertQuery Failed");
        }
        finally
        {
            flushStatmentOnly();
        }
        
        
    }
    public void flushAll()
    {
        {
                        try
                        {
                            statement.close();
                            result.close();
                        }
                        catch(SQLException ex)
                        {System.err.print(ex.toString()+" >> CLOSING DB");}
                    }
    }

    public void updateCustomer(UserInfo user) {
        // update userInfo set name = 'faysal' ,address = 'dhaka' where user_id = 3
        try {
            String updateQuery = "update userInfo set name = '"
                    + user.getName() + "',"
                    + "address = '" + user.getAddress() + "',"
                    + "phone = '" + user.getPhone_no() + "',"
                    + "type = '" + user.getType() + "' where user_id= "
                    + user.getCustomer_id();

            //System.out.println(">>>>>>>>>> "+ insertQuery);
            //System.out.println(updateQuery);
            statement = conn.prepareStatement(updateQuery);

            //System.out.println(updateQuery);
            statement.execute();

            JOptionPane.showMessageDialog(null, "successfully updated new Customer");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\n" + "Update query Failed");
        }

    }

    public void deleteCustomer(int userId) throws SQLException {
        try {
            String deleteQuery = "delete from userInfo where user_id=" + userId;
            statement = conn.prepareStatement(deleteQuery);
            statement.execute();
            JOptionPane.showMessageDialog(null, "Deleted user");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\n" + "Delete query Failed");
        }
        finally
        {
            flushStatmentOnly();
        }

    }

    public ResultSet getAllCustomer() {
        try {
            String query = "select * from userInfo";
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\n error coming from returning all customer DB Operation");
        }
        finally
        {
            flushAll();
        }

        return result;
    }

  
    /// ************************************************************************  SEARCH AND OTHERS ************************************************
    public ResultSet searchUser(String user) {
        try {
            String query = "select user_id,name,address from userInfo where name like '%"+user+"%'";
            
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\n error coming from search user function");
        }
            //System.out.println("fetching something");
        return result;
    }
    
    public ResultSet searchAnUser(int id)
    {
        try {
            String query = "select * from userInfo where user_id="+id;
            
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\n error coming from returning AN user function");
        }
            //System.out.println("fetching something");
        return result;
    }

    public ResultSet getAvailableRooms(long check_inTime)
    {
       try {
           
           ///SELECT room_no FROM room LEFT OUTER JOIN booking ON room.room_no = booking.rooms WHERE booking.rooms is null or booking.check_out < strftime('%s', 'now')
           // SELECT distinct room_no FROM room LEFT OUTER JOIN booking ON room.room_no = booking.rooms WHERE booking.rooms is null or booking.check_out < strftime('%s', 'now') order by check_out desc 
            String query = "SELECT room_no FROM room LEFT OUTER JOIN booking ON room.room_no = booking.booking_room WHERE booking.booking_room is null or "+check_inTime+"< booking.check_in " +"or booking.check_out <"+check_inTime+" group by room.room_no  order by room_no ";
            System.out.println(query);
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\n error coming from returning free rooms from getAvailable func.");
        }

      
        return result;
    }
    
    public ResultSet getBookingInfo(long start_date, long end_date,String roomNo)
    {
        try {
            /*
            
                 select * from booking where 
                (check_in <= start_date and (check_out=0 or check_out<= end_date ) )
                or
                (check_in>start_date and check_out< end_date)
                or
                (check_in <=end_date and (check_out =0 or check_out > end_date) ) 
            
                    */
            
            String query = "select * from booking where booking_room = '"+ roomNo+"' AND ("
                    +"( check_in <= "+start_date +" and ( check_out = 0 or check_out<= "+end_date+") ) or"
                    +"( check_in >"+start_date+" and check_out< "+end_date+" ) or"
                    +"( check_in <= "+end_date +" and ( check_out =0 or check_out> "+end_date+") ) )";
                    
                    
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\n error coming from returning booking info between two specific days");
        }
        

        return result;
    }
    
    public int getCustomerId(UserInfo user)
    { int id = -1;
        try {
            String query = "select user_id from userInfo where name='"+user.getName()+"' and phone ='"+user.getPhone_no()+"'";
            
            System.out.println(query +" <<<");
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
            //System.out.println(" user id "+ result.getInt("user_id"));
            
            id = result.getInt("user_id");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\n error coming from returning AN user function");
        }
       
        return id;
    }
    
    
    
    private void flushStatmentOnly()
    {
        {
                        try
                        {
                            statement.close();
                        }
                        catch(SQLException ex)
                        {System.err.print(ex.toString()+" >> CLOSING DB");}
                    }
    }
}
