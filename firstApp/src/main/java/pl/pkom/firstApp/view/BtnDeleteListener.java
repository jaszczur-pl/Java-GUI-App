package pl.pkom.firstApp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import pl.pkom.firstApp.businessLogic.Controller;

public class BtnDeleteListener implements ActionListener{

    private GraphicInterface graphicInterface;
    private Controller controller;

    public BtnDeleteListener(final GraphicInterface graphicInterface, final Controller controller) {
        this.graphicInterface = graphicInterface;
        this.controller = controller;
    }
    
    public void actionPerformed(final ActionEvent e) {  
        
        boolean deleteExecution = graphicInterface.chooseOption("Czy na pewno chcesz usunąć rekord?", "Delete Option");
        
        if (deleteExecution) {
            int result;
            
            int ID = graphicInterface.getSelectedID();
            
            result = controller.executeDelete(ID);
            
            if (result == 1) {
                new BtnClearListener(graphicInterface).actionPerformed(e);
                new BtnSelectListener(graphicInterface, controller).actionPerformed(e);
                graphicInterface.showInfoDialog("Pomyślnie usunięto rekord");
            } else {
                graphicInterface.showWarningDialog("Nie udało się usunąć rekordu");
            }
        }

    }

}
