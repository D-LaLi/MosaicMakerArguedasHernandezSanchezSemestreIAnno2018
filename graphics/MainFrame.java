package graphics;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;
import tda.ListExeption;

/**
 *
 * @author
 *
 */

/*
    Esta clase define el comportamiento de la ventana  principal
    en la cual iran todos lo componentes de la aplicacion.
*/
public class MainFrame extends JFrame {

    private final RootPanel root;

    //Constructor
    public MainFrame() throws IOException, FileNotFoundException, ClassNotFoundException, ListExeption {

        super("MosaicMomentum");

        // Serie de métodos para establecer el programa a pantalla completa
        // y que no se pueda modificar el tamaño de la ventana
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // Se define como se comporta el momento de cerrar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Se captura el dispositivo de salida(monitor)
        Toolkit myScreen = Toolkit.getDefaultToolkit();

        // Se define un icono que represente la aplicación
        Image myIcon = myScreen.getImage("collage.png");
        setIconImage(myIcon);

        root = new RootPanel();

        // Se carga el panel principal
        add(root);
        
        // Para que la interfaz se ajuste a los componentes
        pack();
        
        setVisible(true);

    }//fin constructor   

}
