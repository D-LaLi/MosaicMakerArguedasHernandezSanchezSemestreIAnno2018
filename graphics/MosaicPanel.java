package graphics;

import domain.Images;
import domain.Mosaic;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author
 */
public class MosaicPanel extends JPanel {

    private BufferedImage subImage;
    private int mX, mY; //codenadas donde fue dado el click

    // Constructor
    public MosaicPanel() {
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(500, 500));
        setMaximumSize(new Dimension(500, 500));
        subImage = null;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Images myImage = Images.getInstance();
        Mosaic myMosaic = Mosaic.getInstance();

        //pixels = myImage.getN();
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                mX = e.getX() / myImage.getN();
                mY = e.getY() / myImage.getN();
                myMosaic.setSelectedBufered(mX, mY);
                myMosaic.listOfMasaicImages();
                revalidate();
                repaint();
            }
        });

        //Se dibujan las imagenes de la matriz en el mosaico
        if (myMosaic.getSubImageMosaic() != null) {
            //Se recorre la matriz de imagenes
            for (int i = 0; i < myMosaic.getSubImageMosaic().length; i++) {
                for (int j = 0; j < myMosaic.getSubImageMosaic()[0].length; j++) {
                    g.drawImage(myMosaic.getSelectedBufered(i, j), j * myImage.getN(), i * myImage.getN(), this);
                }
            }
        }

        // Se dibujan las lineas basado en el tamaño de cada bloque 
        if(myImage.getN() != 0)
            for (int i = 0; i < 600; i += myImage.getN()) {
                g.drawLine(0, i, 600, i);
                g.drawLine(i, 0, i, 600);
            }
    }//fin paintComponent
}
