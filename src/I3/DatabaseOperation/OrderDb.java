package I3.DatabaseOperation;

import I3.Classes.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Faysal
 */


/// ######                       DORKAR NAI EI DB ER , ETA PORE BAD DIYE DIBO
public class OrderDb {

    Connection conn = DataBaseConnection.connectTODB();
    PreparedStatement statement = null;
    ResultSet result = null;

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
            flushStatmentOnly();
        }

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

    private void flushStatmentOnly() {
        {
            try {
                statement.close();
            } catch (SQLException ex) {
                System.err.print(ex.toString() + " >> CLOSING DB");
            }
        }
    }

}
