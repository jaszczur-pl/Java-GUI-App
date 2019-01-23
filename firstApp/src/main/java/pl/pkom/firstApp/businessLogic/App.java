package pl.pkom.firstApp.businessLogic;

import java.sql.SQLException;

public class App {

    public static void main(final String[] args) throws SQLException {

      /*  javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GraphicInterface();
            }
        }); */
        MainLogic mainLogic = new MainLogic();

    }

}
