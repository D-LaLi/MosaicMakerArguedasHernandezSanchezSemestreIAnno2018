package domain;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
public class Project implements Serializable {

    private String name; // nombre del proyecto
    private Images myImage;
    private Mosaic myMosaic;

    // Constructor que se llama cuando se crea el proyecto  por primera vez
    public Project(String name, int blockSize) throws IOException {
        
        this.name = name;
       
        myImage = Images.getInstance();   
        myMosaic = Mosaic.getInstance();
        
        // Reinicia todas la variables dentro de Images
        myImage.flush();
        //myMosaic.flush();
            
        //Se le asigna el tamaño de los bloques
        myImage.setN(blockSize);
        
        // Se crea una nueva matriz de mosaico 
        myMosaic.setSubImageMosaic(blockSize);
                
    }
    
    public Project() {
        this.name = "";
        myImage = Images.getInstance();   
        myMosaic = Mosaic.getInstance();
        myImage.setSubImage(null);
    }
    
    
    // Prepara las instancias para ser serializadas(Importante!). Sin este método
    // no se guarda nada en el archivo por lo tanto de ser llamado antes de 
    // intentar guardar el proyecto.
    public void toByteArray() throws IOException{
        myMosaic.byteArrayToList();
        myImage.imageToByteArray(); 
    } 

    public Images getMyImage() {
        return myImage;
    }

    public void setMyImage(Images myImage) {
        this.myImage = myImage;
    }

    public Mosaic getMyMosaic() {
        return myMosaic;
    }

    public void setMyMosaic(Mosaic myMosaic) {
        this.myMosaic = myMosaic;
    }

    //Métodos de acceso
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Project: " + name;
    }
}
