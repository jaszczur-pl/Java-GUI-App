package pl.pkom.firstApp.view;

public class InsertUpdateException extends Exception{
    
    public InsertUpdateException(final GraphicInterface graphicInterface, int result) {
        switch(result) {
        case 2: 
            graphicInterface.showWarningDialog("Pole 'Name' nie może być puste!");
        break;    
        case 3: 
            graphicInterface.showWarningDialog("Pole 'Surname' nie może być puste!");
        break;
        case 4: 
            graphicInterface.showWarningDialog("Pole 'Country' nie może być puste!");
        break;
        case 5: 
            graphicInterface.showWarningDialog("Pole 'Address' nie może być puste!");
        break;     
        case 6: 
            graphicInterface.showWarningDialog("Pole 'Age' nie może być puste!");
        break;
        case 7: 
            graphicInterface.showWarningDialog("Pole 'Age' musi być numeryczne!");
        break;
        case 8: 
            graphicInterface.showWarningDialog("Pole 'Name' nie może posiadać cyfr!");
        break;
        case 9: 
            graphicInterface.showWarningDialog("Pole 'Surname' nie może posiadać cyfr!");
        break;
        case 10: 
            graphicInterface.showWarningDialog("Pole 'Country' nie może posiadać cyfr!");
        break;
        }
    }
    
}
