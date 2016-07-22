/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package I3.DatabaseOperation;

import I3.Classes.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Faysal Ahmed
 */
public class ItemDb {
    Connection conn = DataBaseConnection.connectTODB();
    PreparedStatement statement = null;
    ResultSet result = null;
    
     public void insertItem(Item item) {
        try {
            String insertItem = "insert into item('name','description','price') values('" + item.getItem_name() + "','" + item.getDescription() + "'," + item.getPrice() + ")";

            // System.out.println(">>>>>>>>>> "+ insertRoomTypeQuery);
            statement = conn.prepareStatement(insertItem);

            statement.execute();

            JOptionPane.showMessageDialog(null, "successfully inserted a new insertItem");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\n" + "InsertQuery of insertItem Failed");
        }
        finally
        {
            flushStatmentOnly();
        }
    }

    public void updateItem(Item item) {
        try {
            String updateFood = "update food set name= '" + item.getItem_name() + "', price= " + item.getPrice() + "description = '" + item.getDescription() + "' where item_id = " + item.getItem_id();

            // System.out.println(">>>>>>>>>> "+ insertRoomTypeQuery);
            statement = conn.prepareStatement(updateFood);

            statement.execute();

            JOptionPane.showMessageDialog(null, "successfully updateitem ");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\n" + "Update Item failed");
        }
        finally
        {
            flushStatmentOnly();
        }
    }

    public ResultSet getItems() {
        try {
            String query = "select * from item";
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\n error coming from returning all item DB Operation");
        }

        return result;
    }

    public void deleteItem(int itemId) {
        try {
            String deleteQuery = "delete from item where item_id=" + itemId;
            statement = conn.prepareStatement(deleteQuery);
            statement.execute();
            JOptionPane.showMessageDialog(null, "Deleted item");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\n" + "Delete query Item Failed");
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
