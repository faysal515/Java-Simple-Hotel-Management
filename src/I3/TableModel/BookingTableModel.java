package I3.TableModel;

import I3.DatabaseOperation.BookingDb;
import I3.DatabaseOperation.DatabaseOperation;
import I3.DatabaseOperation.RoomDb;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Faysal Ahmed
 */
public class BookingTableModel extends AbstractTableModel {

    private String[] columnNames;
    private Date date;
    private Object[][] data;

    public BookingTableModel(long start ,long end) {
        iniColNames();
        fetchDataFromDB(start, end);
        
       // System.out.println("today > "+Calendar.DAY_OF_MONTH+"\n 10 day later"+(Calendar.DAY_OF_MONTH+10));
       
    }

    public void iniColNames() {

        date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("d");
        // -1 , because date starts with 0
        int today = ( Integer.parseInt(ft.format(date))-1 )%getMonthLimit(date);
        //System.out.println(today+", today");
        columnNames = new String[11];
        columnNames[0] = "#";
        for(int i =1;i<11;i++)
        {
            
            today = today%getMonthLimit(date);
            today ++;
          //  System.out.println(today+" , loop today");
            columnNames[i] = today+"";
        }
    }

    public int getMonthLimit(Date x)
    {
        SimpleDateFormat ft = new SimpleDateFormat("M");
        int y = Integer.parseInt(ft.format(x));
        //System.err.println("dsfsd>>> "+ y);
        if(y ==2)
            return 28;
        else if (y ==1|| y ==3|| y ==5|| y ==7|| y ==8|| y ==10 || y== 12)
            return 31;
        else return 30;
    }
    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public void fetchDataFromDB(long start, long end) {

        try {
            int rows = new RoomDb().getNoOfRooms();

            // 11 columns, because i want to display only 10 days record
            data = new Object[rows][11];
            // initialization
            for(int i=0;i<rows;i++)
            {
                for(int j=0;j<data[0].length;j++)
                {
                    data[i][j]= "";
                }
            }
            ResultSet result;
            ResultSet roomNames;
            roomNames = new RoomDb().getAllRoomNames();
            //result = new DatabaseOperation().getBookingInfo(43243214, 4321432);

            for (int i = 0; i < rows; i++) {
                if (roomNames.next()) {
                    String roomName = roomNames.getString("room_no");
                    data[i][0] = roomName;
                    result = new DatabaseOperation().getBookingInfo(start, end, roomName);
                    while (result.next()) {
                        System.out.println("coming here for " + roomName);
                        long check_in = Long.parseLong(result.getString("check_in"));
                        long check_out = Long.parseLong(result.getString("check_out"));
                        System.out.println("check in  " + new Date(check_in*1000).toString() + " .... check out   "+ new Date(check_out*1000).toString());
                        if(check_in<= (start) && ( check_out == 0 || check_out<= end))
                        {
                            System.out.println("first LOOP " + roomName);
                            data[i][1] = "<<";
                                
                        }
                        else if(check_in > start &&  check_out< end)
                                {
                                    int checkIn = Integer.parseInt(new SimpleDateFormat("d").format(new Date(check_in*1000)));
                                    int checkOut = Integer.parseInt(new SimpleDateFormat("d").format(new Date(check_out*1000)));
                                    int getToday =   Integer.parseInt(new SimpleDateFormat("d").format(new Date(start*1000)));
                                    System.out.println("xxxxxxxxx "+ getToday+"........ "+ checkIn);
                                    data[i][(checkIn-getToday)+1] = ">";
                                    data[i][(checkOut-getToday)+1] = "<";
                                }
                        
                        else if(check_in<= end && ( check_out == 0 || check_out > end))
                        {
                            int xx = Integer.parseInt(new SimpleDateFormat("d").format(new Date(check_in*1000))); 
                            int getToday =  Integer.parseInt(new SimpleDateFormat("d").format(new Date(start*1000)));
                            System.out.println("..... "+getToday+ " ...........  "+xx);
                            data[i][(xx-getToday)+1] =">>";
                        }
                    }
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "from Booking table model class\n " + ex.toString());
        }

    }
}
