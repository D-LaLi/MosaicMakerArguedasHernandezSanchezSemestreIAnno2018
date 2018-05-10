package domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.imageio.ImageIO;

/**
 *
 * @author
 */
public class Images implements Serializable {

    private static Images instance = new Images();

    //Atributos de la clase
    private transient BufferedImage[][] subImageList; // <- Matriz en donde se guarda cada sub-imagen
    private transient BufferedImage image; // <- imagen que se debe manipular
    private transient BufferedImage subImage;// <- Imagen que sera la última seleccionada
    private int n; // <- Tamaño de cada bloque
    private byte[] imageByte;

    // Constructor vacio, con acceso privado para asegurar que sea instanciado 
    // solamente por la propia clase.
    private Images() {
        this.n = 0;
        this.image = null;
        subImageList = null;
        subImage = null;
    }

    public static Images getInstance() {
        return instance;
    }

    // Este método guarda un array bidimensional de imagenes en donde
    // "n" representa el tamano de cada cuadro (cada sub-imagen) e
    // "image" representa la imagen que se va a dividir
    public void arrayOfImages() {
        subImageList = new BufferedImage[image.getHeight() / n][image.getWidth() / n];
        // for que controla las filas (eje y)
        for (int i = 0; i < subImageList.length; i++) {
            //for que controla las columnas (eje x)
            for (int j = 0; j < subImageList[0].length; j++) {
                subImageList[i][j] = image.getSubimage(j * n, i * n, n, n); //(x, y, ancho, alto)
            }//fin for j
        }//fin for i
    }//fin arrayOfImages 

    // Método que seleciona la una imagen clikeada
    public void copyImage(int mY, int mX) {
        int witdh = (subImageList.length * n);
        int height = (subImageList[0].length * n);
        //comprueba que el click se dio dentro de la imagen
        if (mX < witdh && mY < height) {
            int x1 = mY / n;
            int y1 = mX / n;
            subImage = subImageList[y1][x1];
        }//fin if   
    }//fin copyImage 

    // Guarda la imagen en un byte array para que de está manera pueda ser
    // almecenada
    public void imageToByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        baos.flush();
        imageByte = baos.toByteArray();
        baos.close();
    }

    // Recupera la imagen del array de bytes
    public void byteArrayToImage() throws IOException {
        InputStream in = new ByteArrayInputStream(imageByte);
        image = ImageIO.read(in);
    }

    // Métodos de acceso
    public BufferedImage[][] getSubImageList() {
        return subImageList;
    }

    public void setSubImageList(BufferedImage[][] subImageList) {
        this.subImageList = subImageList;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        //this.image = image;
        Image toolkitImage = image.getScaledInstance(500, -1, Image.SCALE_SMOOTH);
        int width = toolkitImage.getWidth(null);
        int height = toolkitImage.getHeight(null);

        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.image = newImage;
        Graphics g = newImage.getGraphics();
        g.drawImage(toolkitImage, 0, 0, null);
        g.dispose();
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public BufferedImage getSubImage() {
        return subImage;
    }

    public void setSubImage(BufferedImage subImage) {
        this.subImage = subImage;
    }

    public byte[] getImageByte() {
        return imageByte;
    }

    public void setImageByte(byte[] imageByte) {
        this.imageByte = imageByte;
    }
    
    public void flush() {
        subImageList = null;
        subImage = null;
        image = null;
        n = 0;
        imageByte = null;
    }
    
}
