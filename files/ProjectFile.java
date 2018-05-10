
package files;

import domain.Images;
import domain.Mosaic;
import domain.Project;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import tda.ListExeption;

/**
 *
 * @author 
 */
public class ProjectFile {
    
    private String filePath;

    public ProjectFile(String filePath) {
        this.filePath = filePath;
    }
    
    public ProjectFile() {
        this.filePath = "";
    }
    
    // Método para almacenar la informacion del proyecto en el disco
    public void saveProject(Project myProject) throws IOException {
        
        File myFile = new File(filePath);
        saveLastPath(filePath);
        
        myProject.toByteArray();
        
        // se crea la fuente de salida para escribir el archivo y se escribe el proyecto en el archivo
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(myFile))) {
            outputStream.writeUnshared(myProject);
        } //se cierra el archivo
        
    }//fin saveProject
    
    // Método que se encarga de cargar el proyecto desde los archivos si este 
    // existe
    public Project chargeProject() throws IOException, ClassNotFoundException, ListExeption {
        
        File myFile = new File(filePath);
        
        Project myProject = new Project();
        
        if(myFile.exists()){
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(myFile))) {
                Object tempObject = inputStream.readObject();
                myProject = (Project)tempObject;
            }//se cierra el archivo
        }
        
        chargeMosaic(myProject);
        chargeImage(myProject);
        
        return myProject;   
    }
    
    // Método que se encarga de cargar el proyecto desde los archivos si este 
    // existe en la ruta espesificada
    public Project chargeProject(String path) throws IOException, ClassNotFoundException, ListExeption {
        
        File myFile = new File(path);
        
        Project myProject = new Project();
        
        if(myFile.exists()){
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(myFile))) {
                Object tempObject = inputStream.readObject();
                myProject = (Project)tempObject;
            }//se cierra el archivo
        }
        
        chargeMosaic(myProject);
        chargeImage(myProject);
        
        return myProject;   
    }
    
    // Se sincroniza la instancia global de mosaico con el proyecto cargado
    public void chargeMosaic(Project myProject) throws ListExeption, IOException {
        Mosaic myMosaic = Mosaic.getInstance();
        myMosaic.setSubImageMosaic(myProject.getMyImage().getN());
        myMosaic.setLista(myProject.getMyMosaic().getLista());
    }
    
    // Se sincroniza la instancia global de imagen con el proyecto cargado
    public void chargeImage(Project myProject) throws IOException {
        Images myImage = Images.getInstance();
        myImage.setImageByte(myProject.getMyImage().getImageByte());
        myImage.setN(myProject.getMyImage().getN());
        myImage.byteArrayToImage();
    }//fin chargeImage
    
    public void saveLastPath(String path) throws IOException {
        File myFile = new File("./lastPath.dat");
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(myFile))) {
            //Guarda la ruta que se pasa como parametro
            outputStream.writeObject(path);
            outputStream.close();
        } //se cierra el archivo
    } //fin saveLastPath
    
    public String chargeLastPath() throws FileNotFoundException, IOException, ClassNotFoundException {
        
        File myFile = new File("./lastPath.dat");
        
        String lastPath = "";
        
        if(myFile.exists()){
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(myFile))) {
                Object tempObject = inputStream.readObject();
                lastPath = (String)tempObject;
                inputStream.close();
            }//se cierra el archivo
        }  
        return lastPath;
    }
    
}
