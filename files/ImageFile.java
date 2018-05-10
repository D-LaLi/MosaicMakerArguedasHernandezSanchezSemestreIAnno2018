
package files;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author
 */

public class ImageFile {
    
    private BufferedImage img;
    private String path;

    public ImageFile(String path) {
        this.path = path;
        img = null;
    }  

    public ImageFile() {
        // Constructor vacio
        path = "";
        img = null;
    }
    
    // Se obtiene una imagen a partir de una dirección en el directorio 
    public BufferedImage loadAnImage() {
        try {
            img = ImageIO.read(new File(path));
            return img;
        } catch(IOException | NullPointerException e) {
            //System.err.println("Error at ImageFile | Method: loadAnImage");
            return null;
        }
    }//fin loadAnImage 
    
    // Métodos de acceso

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    
}
