
package main;

import graphics.MainFrame;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tda.ListExeption;

/**
 *
 * @author 
 *         
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            @SuppressWarnings("unused")
                    MainFrame  main_window = new MainFrame();
            //main_window.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ListExeption ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
