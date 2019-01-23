package pl.pkom.firstApp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pl.pkom.firstApp.businessLogic.Controller;

public class BtnClearListener implements ActionListener {
    private GraphicInterface graphicInterface;

    public BtnClearListener(final GraphicInterface graphicInterface) {
        this.graphicInterface = graphicInterface;
    }
    
    public void actionPerformed(final ActionEvent e) {  
        graphicInterface.setTextFieldName("");
        graphicInterface.setTextFieldSurname("");
        graphicInterface.setTextFieldCountry("");
        graphicInterface.setTextFieldAddress("");
        graphicInterface.setTextFieldAge("");
        graphicInterface.setSelectedID(0);
    }
}
