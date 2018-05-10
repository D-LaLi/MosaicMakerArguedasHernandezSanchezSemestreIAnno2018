package domain;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.imageio.ImageIO;
import tda.LinkedList;
import tda.ListExeption;
import tda.Node;

/**
 *
 * @author
 */
public class Mosaic implements Serializable {

    private static Mosaic instance = new Mosaic();

    private transient BufferedImage[][] subImageMosaic; //Matriz en donde se guarda cada sub-imagenes del masaico
    private transient Images myImage;
    private LinkedList lista;
    private transient int selectedX, selectedY;

    // Constructores
    private Mosaic() {
        this.myImage = Images.getInstance();
        this.subImageMosaic = null;
    }

    // Mètodos de acceso
    public static Mosaic getInstance() {
        return instance;
    }

    public BufferedImage[][] getSubImageMosaic() {
        return subImageMosaic;
    }

    public void setSubImageMosaic(int blockSize) {
        this.subImageMosaic = new BufferedImage[500 / blockSize][500 / blockSize];
    }

    // Se obtiene una sub-imagen de la poscion indicada
    public void listOfMasaicImages() {
        try {
            subImageMosaic[selectedY][selectedX] = myImage.getSubImage();
        } catch (Exception e) {
            // No se pudo insertar la imagen en la posicion indicada
        }
    }

    public BufferedImage getSelectedBufered(int x, int y) {
        return subImageMosaic[x][y];
    }

    public void setSelectedBufered(int x, int y) {
        this.selectedX = x;
        this.selectedY = y;
    }

    public void setLista(LinkedList lista) throws ListExeption, IOException {
        this.lista = lista;
        //Sicroniza la lista enlazada con la matriz de imagenes
        retrieveFromList();
    }

    public LinkedList getLista() {
        return lista;
    }

    // Se alamcena el contenido de la matriz de imagenes(bufferedImage) en una 
    // lista enlazada que se pueda serializar.
    public void byteArrayToList() throws IOException {
        lista = new LinkedList();
        for (int i = 0; i < subImageMosaic.length; i++) {
            for (int j = 0; j < subImageMosaic[0].length; j++) {
                if (subImageMosaic[i][j] != null) {
                    // Convierte la imagen a un array de bytes y la guarda en 
                    // lista enlazada junto con i y j (posiciones dento de la matriz)
                    lista.insert(imageToByteArray(subImageMosaic[i][j]), i, j);
                }//fin if
            }//fin for j
        }//fin for i
    } //fin 

    // Recupera las imagenes de la lista con sus respectivas posiciones y las 
    // almacena en la matriz de imagenes
    public void retrieveFromList() throws ListExeption, IOException {
        for (int i = 0; i < lista.getSize(); i++) {
            Node temp = lista.getNode(i);
            subImageMosaic[(int) temp.element2][(int) temp.element3]
                    = byteArrayToImage((byte[]) temp.element1);
        }//fin for
    }

    // Recupera la imagen del array de bytes
    public BufferedImage byteArrayToImage(byte[] imageByte) throws IOException {
        InputStream in = new ByteArrayInputStream(imageByte);
        BufferedImage image = ImageIO.read(in);
        return image;
    }

    // Guarda la imagen en un byte array para que de está manera pueda ser
    // almecenada
    public byte[] imageToByteArray(BufferedImage image) throws IOException {
        //Clase que me permite convertir una imagen a un array de bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        //Se convierte la imagen
        ImageIO.write(image, "jpg", baos);

        baos.flush();

        byte[] imageByte = baos.toByteArray();

        baos.close();

        return imageByte;
    }

    /**
     * Los siguientes métodos atañen a las funciones correspondientes del
     * mosaico: Borrar(deleteBuffered), rotar a la derecha(rotateRight), rotar a
     * la izquierda(rotate left), voltear a la derecha(flipRight), voltear a la
     * izquierda(filpLeft). En estos métodos se reordenan los pixeles de la
     * imagen y se guarda esta imagen en tempRotateImage(BufferedImage).
     */
    public void rotateRight() {
        if (subImageMosaic[selectedY][selectedX] != null) {
            BufferedImage tempRotateImage = new BufferedImage(myImage.getN(), myImage.getN(), BufferedImage.TYPE_INT_ARGB);
            for (int i = 0; i < myImage.getN(); i++) {
                for (int j = 0; j < myImage.getN(); j++) {
                    // Crea un temporal con los pixeles reacomodados
                    tempRotateImage.setRGB(myImage.getN() - 1 - j, i, subImageMosaic[selectedY][selectedX].getRGB(i, j));
                }
            }
            subImageMosaic[selectedY][selectedX] = tempRotateImage;
        }
    }

    public void rotateLeft() {
        if (subImageMosaic[selectedY][selectedX] != null) {
            BufferedImage tempRotateImage = new BufferedImage(myImage.getN(), myImage.getN(), BufferedImage.TYPE_INT_ARGB);
            for (int i = 0; i < myImage.getN(); i++) {
                for (int j = 0; j < myImage.getN(); j++) {
                    // Crea un temporal con los pixeles reacomodados
                    tempRotateImage.setRGB(j, myImage.getN() - 1 - i, subImageMosaic[selectedY][selectedX].getRGB(i, j));
                }
            }
            subImageMosaic[selectedY][selectedX] = tempRotateImage;
        }
    }

    public void flipHorizontal() {
        if (subImageMosaic[selectedY][selectedX] != null) {
            BufferedImage tempRotateImage = new BufferedImage(myImage.getN(), myImage.getN(), BufferedImage.TYPE_INT_ARGB);
            for (int i = 0; i < myImage.getN(); i++) {
                for (int j = 0; j < myImage.getN(); j++) {
                    // Crea un temporal con los pixeles reacomodados
                    tempRotateImage.setRGB(myImage.getN() - 1 - i, j, subImageMosaic[selectedY][selectedX].getRGB(i, j));
                }
            }
            subImageMosaic[selectedY][selectedX] = tempRotateImage;
        }
    }

    public void flipVertical() {
        if (subImageMosaic[selectedY][selectedX] != null) {
            BufferedImage tempRotateImage = new BufferedImage(myImage.getN(), myImage.getN(), BufferedImage.TYPE_INT_ARGB);
            for (int i = 0; i < myImage.getN(); i++) {
                for (int j = 0; j < myImage.getN(); j++) {
                    // Crea un temporal con los pixeles reacomodados
                    tempRotateImage.setRGB(i, myImage.getN() - 1 - j, subImageMosaic[selectedY][selectedX].getRGB(i, j));
                }
            }
            subImageMosaic[selectedY][selectedX] = tempRotateImage;
        }
    }

    public void deleteBuffered() {
        // Anula lo que tienen la imagen en esa zona
        subImageMosaic[selectedY][selectedX] = null;
    }

    public void flush() {
        subImageMosaic = null;
        myImage = null;
        lista = null;
        selectedX = 0;
        selectedY = 0;
    }

}
