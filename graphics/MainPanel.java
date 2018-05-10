
package graphics;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author 
 *         
 */
public class MainPanel extends JPanel {
    
    private MenuPanel menuPanel;

    public MainPanel() {       
        
        // Se define el layout para este panel
        setLayout(new BorderLayout());
        
        menuPanel = new MenuPanel();
        
        // Se a�ade la barra del menu principal
        add(menuPanel, BorderLayout.PAGE_START);
        
        // Se instancia el contenedor de imagenes
        ImagePanel ic = menuPanel.getImagePanel();
        
        add(ic,  BorderLayout.CENTER);     
        
    } 
}
