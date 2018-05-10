package graphics;

import domain.Images;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author 
 */
//Esta clase representa el panel en el que se cargará la imagen
//que el usuario necesite para trabajar
public class ImagePanel extends JPanel {

    BufferedImage img;

    public ImagePanel() {
        // Color color = new Color(12, 74, 95);
        setBackground(Color.darkGray);

        setPreferredSize(new Dimension(500, 500));
        setMaximumSize(new Dimension(500, 500));
    }

    // Implementación del método paintComponent
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        // Se dibuja la imagen en el panel
        Images myImage = Images.getInstance();

        //int pixels = myImage.getN();
        try {
            g.drawImage(myImage.getImage(), 0, 0, null);
            myImage.arrayOfImages();

            // Se dibujan las lineas sobre el panel
            for (int i = 0; i < myImage.getImage().getWidth(); i += myImage.getN()) {
                if (i < myImage.getImage().getHeight()) {
                    g.drawLine(0, i, 600, i);
                }
                g.drawLine(i, 0, i, myImage.getImage().getHeight());
            }

            // Se obtiene la imagen según la posicion del mouse
            addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    myImage.copyImage(e.getX(), e.getY());
                }
            });
        } catch (NullPointerException | ArithmeticException e) {

        }
    }// fin paintComponent 

}
