package pl.pkom.firstApp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pl.pkom.firstApp.businessLogic.Controller;

public class BtnInsertListener implements ActionListener{

    private GraphicInterface graphicInterface;
    private Controller controller;
    
    public BtnInsertListener(final GraphicInterface graphicInterface, final Controller controller) {
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
        
        try {
            result = controller.executeInsert(name, surname, country, address, age);
            if (result == 1) {
                new BtnClearListener(graphicInterface).actionPerformed(e);
                new BtnSelectListener(graphicInterface, controller).actionPerformed(e);
                graphicInterface.showInfoDialog("Pomyślnie wprowadzono rekord");
            } else {
                graphicInterface.showWarningDialog("Nie udało się wprowawdzić rekordu");
            }
        } catch (InsertUpdateException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}