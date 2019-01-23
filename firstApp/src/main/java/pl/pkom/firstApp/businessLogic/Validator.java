package pl.pkom.firstApp.businessLogic;

import pl.pkom.firstApp.view.GraphicInterface;
import pl.pkom.firstApp.view.InsertUpdateException;

public class Validator {

    private String name;
    private String surname;
    private String country;
    private String address;
    private String age;
    
    public Validator(final String name, final String surname, final String country, final String address, final String age) {
        super();
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.address = address;
        this.age = age;
    }
    
    public int validateFieldsFilling(final GraphicInterface graphicInterface) throws InsertUpdateException {
        
        if (name.isEmpty()) {
            throw new InsertUpdateException(graphicInterface, 2);
        } else if (surname.isEmpty()) {
            throw new InsertUpdateException(graphicInterface, 3);
        } else if (country.isEmpty()) {
            throw new InsertUpdateException(graphicInterface, 4);
        } else if (address.isEmpty()) {
            throw new InsertUpdateException(graphicInterface, 5);
        } else if (age.isEmpty()) {
            throw new InsertUpdateException(graphicInterface, 6);
        } else {
            return 1;
        }
   }
   
   public int validateIfAgeIsNumeric(final GraphicInterface graphicInterface) throws InsertUpdateException {
       
       try {
           Double.parseDouble(age);
       } catch (NumberFormatException e) {
           throw new InsertUpdateException(graphicInterface, 7);
       }
       
       return 1;
   }
   
   public int validateIfNameSurnameCountryIsAlphabetical(final GraphicInterface graphicInterface) throws InsertUpdateException {
       
       boolean isNameAlphabetical = true;
       boolean isSurnameAlphabetical = true;
       boolean isCountryAlphabetical = true;
       
       for (int i=0; i < name.length(); i++) {
           char ch = name.charAt(i);
           if (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || ch == ' ' || ch == '-')) {
               isNameAlphabetical = false;
           }
       }
       
       for (int i=0; i < surname.length(); i++) {
           char ch = surname.charAt(i);
           if (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || ch == ' ' || ch == '-')) {
               isSurnameAlphabetical = false;
           }
       }
       
       for (int i=0; i < country.length(); i++) {
           char ch = country.charAt(i);
           if (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || ch == ' ' || ch == '-')) {
               isCountryAlphabetical = false;
           }
       }
       
       
       if (!isNameAlphabetical) {
           throw new InsertUpdateException(graphicInterface, 8); 
       } else if (!isSurnameAlphabetical) {
           throw new InsertUpdateException(graphicInterface, 9); 
       } else if (!isCountryAlphabetical) {
           throw new InsertUpdateException(graphicInterface, 10); 
       } else {
           return 1;
       } 
       
   }
}
