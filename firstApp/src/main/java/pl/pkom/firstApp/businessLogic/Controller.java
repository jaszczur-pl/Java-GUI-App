package pl.pkom.firstApp.businessLogic;

import java.sql.ResultSet;
import java.sql.SQLException;

import pl.pkom.firstApp.data.CustomerDAO;
import pl.pkom.firstApp.view.GraphicInterface;
import pl.pkom.firstApp.view.InsertUpdateException;

public class Controller {
    
    private CustomerDAO customerDAO;
    private GraphicInterface graphicInterface;
    private Validator validator;
    
    public Controller(final GraphicInterface graphicInterface) {
        this.graphicInterface = graphicInterface;
        customerDAO = new CustomerDAO();
    }

    public ResultSet executeSelect(final String name, final String surname, final String country, final String address, final String age) {
        ResultSet resultSet = null;
        
        customerDAO.init();
        
        try {
            resultSet = customerDAO.selectDAO(name, surname, country, address, age);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return resultSet;
    }
    
    public int executeInsert(final String name, final String surname, final String country, final String address, final String age) throws InsertUpdateException {
        
        int result = 1;
        
        validator = new Validator(name, surname, country, address, age);
        
        result = validator.validateFieldsFilling(graphicInterface);
        result = validator.validateIfAgeIsNumeric(graphicInterface);
        result = validator.validateIfNameSurnameCountryIsAlphabetical(graphicInterface);

        customerDAO.init();
        
        try {
            result = customerDAO.insertDAO(name, surname, country, address, age);
        } catch (SQLException e) {

            e.printStackTrace();
        }
        
        return result;
    }
    
    public int executeUpdate(int ID, final String name, final String surname, final String country, final String address, final String age) throws InsertUpdateException {
        
        int result = 1;
        
        validator = new Validator(name, surname, country, address, age);
        
        result = validator.validateFieldsFilling(graphicInterface);
        result = validator.validateIfAgeIsNumeric(graphicInterface);
        result = validator.validateIfNameSurnameCountryIsAlphabetical(graphicInterface);
        
        customerDAO.init();
        
        try {
            result = customerDAO.updateDAO(ID, name, surname, country, address, age);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return result;
    }
    
    public int executeDelete(int ID) {
        int result = 1;
        
        customerDAO.init();
        
        try {
            result = customerDAO.deleteDAO(ID);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return result;
    }


}
