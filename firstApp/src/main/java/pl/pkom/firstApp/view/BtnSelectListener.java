package pl.pkom.firstApp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import pl.pkom.firstApp.businessLogic.Controller;

public class BtnSelectListener implements ActionListener{

    private Controller controller;
    private GraphicInterface graphicInterface;
    
    public BtnSelectListener(final GraphicInterface graphicInterface, final Controller controller) {
        this.graphicInterface = graphicInterface;
        this.controller = controller;
    }

    public void actionPerformed(final ActionEvent e) {  
        
        ResultSet resultSet = null;
        
        String name = graphicInterface.getName();
        String surname = graphicInterface.getSurname();
        String country = graphicInterface.getCountry();
        String address = graphicInterface.getAddress();
        String age = graphicInterface.getAge();
        
        resultSet = controller.executeSelect(name, surname, country, address, age);
        
        try {
            if (!resultSet.next()) {
                graphicInterface.showInfoDialog("Nie znaleziono odpowiadających rekordów");
            } else {
                graphicInterface.showResultInTable(resultSet);
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}
