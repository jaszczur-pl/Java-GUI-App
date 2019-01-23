package pl.pkom.firstApp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pl.pkom.firstApp.businessLogic.Controller;

public class BtnUpdateListener implements ActionListener{

    private GraphicInterface graphicInterface;
    private Controller controller;
    
    public BtnUpdateListener(final GraphicInterface graphicInterface, final Controller controller) {
        this.graphicInterface = graphicInterface;
        this.controller = controller;
    }

    public void actionPerformed(final ActionEvent e) {  
        int result;
        
        String name = graphicInterface.getName();
        String surname = graphicInterface.getSurname();
        String country = graphicInterface.getCountry();
        String address = graphicInterface.getAddress();
        String age = graphicInterface.getAge();
        int ID = graphicInterface.getSelectedID();
        
        try {
            result = controller.executeUpdate(ID, name, surname, country, address, age);
            if (result == 1) {
                new BtnClearListener(graphicInterface).actionPerformed(e);
                new BtnSelectListener(graphicInterface, controller).actionPerformed(e);
                graphicInterface.showInfoDialog("Pomyślnie zaktualizowano rekord");
            } else {
                graphicInterface.showWarningDialog("Nie udało się zaktualizować rekordu");
            }
        } catch (InsertUpdateException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}
