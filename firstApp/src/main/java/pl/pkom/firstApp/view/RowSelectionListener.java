package pl.pkom.firstApp.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;
import javax.swing.JTextField;

public class RowSelectionListener implements MouseListener{
    
    private JTable table;
    private GraphicInterface graphicInterface;
    
    public RowSelectionListener(final GraphicInterface graphicInterface, final JTable table) {
        super();
        this.graphicInterface = graphicInterface;
        this.table = table;
    }

    public void jTableMouseClicked(final MouseEvent evt) {
        int selectedID = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()); 
        String name = table.getValueAt(table.getSelectedRow(), 1).toString();
        String surname = table.getValueAt(table.getSelectedRow(), 2).toString();
        String country = table.getValueAt(table.getSelectedRow(), 3).toString();
        String address = table.getValueAt(table.getSelectedRow(), 4).toString();
        String age = table.getValueAt(table.getSelectedRow(), 5).toString();
        
        graphicInterface.setTextFieldName(name);
        graphicInterface.setTextFieldSurname(surname);
        graphicInterface.setTextFieldCountry(country);
        graphicInterface.setTextFieldAddress(address);
        graphicInterface.setTextFieldAge(age);
        graphicInterface.setSelectedID(selectedID);
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(final MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(final MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(final MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
