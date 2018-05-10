package graphics;

import domain.Mosaic;
import files.ProjectFile;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import tda.ListExeption;

/**
 *
 * @author 
 *
 */
public class RootPanel extends JPanel {

    private final MenuPanel menuPanel;
    private ImagePanel imagePanel;
    private MosaicPanel mosaicPanel;
    private JPanel spaceWorkPanel;

    //Constructor
    public RootPanel() throws IOException, FileNotFoundException, ClassNotFoundException, ListExeption {

        // Se define el layout del panel
        setLayout(new BorderLayout());
        setBackground(Color.darkGray);

        //Se agrega menuPanel a este panel
        menuPanel = new MenuPanel();
        add(menuPanel, BorderLayout.PAGE_START);

        // Se configura el panel de area de trabajo y se añade
        setSpaceWorkPanel();
        add(spaceWorkPanel, BorderLayout.CENTER);

    }//fin RootPanel

    public void setSpaceWorkPanel() throws IOException, FileNotFoundException, ClassNotFoundException, ListExeption {

        // Configuración del panel
        spaceWorkPanel = new JPanel();
        spaceWorkPanel.setBackground(Color.darkGray);
        spaceWorkPanel.setLayout(new BoxLayout(spaceWorkPanel, BoxLayout.X_AXIS));

        // Se obtienen las instancias de MosaicPanel y ImagePanel
        imagePanel = menuPanel.getImagePanel();
        mosaicPanel = menuPanel.getMosaicPanel();

        // Se carga el ultimo proyecto en el que se trabajó si este existe
        try {
            ProjectFile projectFile = new ProjectFile();
            
            // Carga el archivo que contiene la ruta del ultimo proyecto guardado
            String path = projectFile.chargeLastPath();
            
            // Carga el proyecto con la ruta indicada por el archivo
            projectFile.chargeProject(path);
            
            //Se hace visible el mosaico
            mosaicPanel.setBackground(Color.WHITE);
            
        } catch (ArithmeticException | InvalidClassException ae) {
            // Se ha movido o eliminado el archivo
        }

        // Se añaden componentes al panel. Box Funciona como una caja invisible 
        // que permite acomodar convenientemente los componentes dentro del panel
        spaceWorkPanel.add(Box.createRigidArea(new Dimension(100, 0)));
        spaceWorkPanel.add(imagePanel);
        spaceWorkPanel.add(Box.createRigidArea(new Dimension(120, 0)));
        spaceWorkPanel.add(mosaicPanel);

    }
}

/*  
    Nota: MenuPanel esta compuesto de ImagePanel y MosaicPanel. RootPanel simplemente
    se utiliza para indicar la posición en donde estos paneles estarán ubicados. 
    Sin embargo, es MenuPanel quien se encarga de cargar los dos paneles y 
    actualizarlos(revalidate(), repaint()) y pasarselos a RootPanel.
 */
