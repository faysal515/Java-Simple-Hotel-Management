/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package I3.TableModel;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Faysal
 */
public class CustomCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        //System.out.println("workings");
/*
        
        if (value.equals("<<"))
        {
            c.setBackground(new java.awt.Color(0, 0, 255));
        }
        else 
        {
            c.setBackground(table.getBackground());
        }
        
        // for creating row header illusion
  
        */
        if(column == 0)
        {
            
            setForeground(table.getTableHeader().getForeground());
            setBackground(Color.LIGHT_GRAY);
            setFont(table.getTableHeader().getFont());
        }
        else
        {
            setBackground(table.getBackground());
        }
        
        
        if (value.equals(">"))
        {
            c.setBackground(new java.awt.Color(0, 0, 255));
        }
        else 
        {
            c.setBackground(table.getBackground());
        }
        

        return c;
    }

}
