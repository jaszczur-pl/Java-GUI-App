package pl.pkom.firstApp.businessLogic;

import java.sql.SQLException;

import pl.pkom.firstApp.view.GraphicInterface;

public class MainLogic {
    
    public MainLogic() throws SQLException {
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                
                new GraphicInterface();
            }
        }); 
    }
     
}
