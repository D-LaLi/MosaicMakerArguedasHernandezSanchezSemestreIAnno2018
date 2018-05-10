package graphics;

import domain.Images;
import domain.Mosaic;
import domain.Project;
import files.ImageFile;
import files.ProjectFile;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import tda.ListExeption;

/**
 *
 * @author 
 *
 */
public class MenuPanel extends JPanel implements ActionListener {

    private static JButton insert_btn;
    private ImagePanel imagePanel;
    private MosaicPanel mosaicPanel;
    private JPanel toolBoxPanel;
    private JPanel projectOptionsPanel;
    private JButton saveProject_btn;
    private JButton openProject_btn;
    private JButton createProject_btn;
    private JButton rotateRight_btn;
    private JButton rotateLeft_btn;
    private JButton delete_btn;
    private JButton flipVertical_btn;
    private JButton flipHorizontal_btn;
    private JButton export_btn;
    NewProyectPanel newProjectPanel;

    public MenuPanel() {

        initComponents();
        setProjectOptionsPanel();
        setToolBoxPanel();

        // Se selecciona el color
        Color color = new Color(28, 96, 119);
        setBackground(color);

        // Se define el layout para este panel
        FlowLayout fl = new FlowLayout();
        setLayout(fl);

        // Alineamiento de los componentes
        fl.setAlignment(FlowLayout.LEFT);

        add(projectOptionsPanel);
        add(toolBoxPanel);
    }//fin constructor

    //Método que se utiliza para inicializar todos los componentes de la clase
    private void initComponents() {

        newProjectPanel = new NewProyectPanel();

        //Se inicializan otros componentes necesarios
        projectOptionsPanel = new JPanel();
        toolBoxPanel = new JPanel();

        // Se inicializa el contenedor de imagenes y mosaico
        imagePanel = new ImagePanel();
        mosaicPanel = new MosaicPanel();

        // Se inicializan los botones
        saveProject_btn = new JButton(new ImageIcon("src/icons/save_p.png"));
        openProject_btn = new JButton(new ImageIcon("src/icons/open_p.png"));
        createProject_btn = new JButton(new ImageIcon("src/icons/new_p.png"));
        insert_btn = new JButton(new ImageIcon("src/icons/search_image.png"));
        rotateRight_btn = new JButton(new ImageIcon("src/icons/rotate_right.png"));
        rotateLeft_btn = new JButton(new ImageIcon("src/icons/rotate_left.png"));
        delete_btn = new JButton(new ImageIcon("src/icons/delete.png"));
        flipVertical_btn = new JButton(new ImageIcon("src/icons/flip_horizontal.png"));
        flipHorizontal_btn = new JButton(new ImageIcon("src/icons/flip_vertical.png"));
        export_btn = new JButton(new ImageIcon("src/icons/export.png"));

        // Se agregan mensajes de indicación a cada botón
        saveProject_btn.setToolTipText("Guardar proyecto");
        openProject_btn.setToolTipText("Abrir proyecto");
        createProject_btn.setToolTipText("Crear nuevo projecto");
        insert_btn.setToolTipText("Buscar imagen");
        rotateRight_btn.setToolTipText("Rotar a la derecha");
        rotateLeft_btn.setToolTipText("Rotar a la izquierda");
        delete_btn.setToolTipText("Borrar");
        flipVertical_btn.setToolTipText("Voltear verticalmente");
        flipHorizontal_btn.setToolTipText("Voltear horizontalmente");
        export_btn.setToolTipText("Exportar a png");

        // Se indica quien escuchará las acciones
        insert_btn.addActionListener(this);
        rotateRight_btn.addActionListener(this);
        rotateLeft_btn.addActionListener(this);
        flipVertical_btn.addActionListener(this);
        flipHorizontal_btn.addActionListener(this);
        delete_btn.addActionListener(this);
        saveProject_btn.addActionListener(this);
        openProject_btn.addActionListener(this);
        createProject_btn.addActionListener(this);
        export_btn.addActionListener(this);

        // Posible configuración futura para botones(No borrar)
        /*createProject_btn.setOpaque(true);
        createProject_btn.setFocusPainted(false);
        createProject_btn.setBorderPainted(false);
        createProject_btn.setContentAreaFilled(false);
        createProject_btn.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));*/
    } //fin initComponents

    //Se configura toolBox y se añaden sus componentes
    private void setToolBoxPanel() {

        Color color = new Color(12, 74, 95);
        toolBoxPanel.setBackground(color);

        toolBoxPanel.add(rotateRight_btn);
        toolBoxPanel.add(rotateLeft_btn);
        toolBoxPanel.add(flipVertical_btn);
        toolBoxPanel.add(flipHorizontal_btn);
        toolBoxPanel.add(delete_btn);
        toolBoxPanel.add(export_btn);
    }

    //Opciones para administrar los proyectos(Crear, Abrir, Guardar)
    private void setProjectOptionsPanel() {

        Color color = new Color(12, 74, 95);
        projectOptionsPanel.setBackground(color);

        projectOptionsPanel.add(createProject_btn);
        projectOptionsPanel.add(openProject_btn);
        projectOptionsPanel.add(saveProject_btn);
        projectOptionsPanel.add(insert_btn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == export_btn) {

            JFileChooser fileChooser = new JFileChooser();

            int returnVal = fileChooser.showSaveDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                BufferedImage tempBuffer = new BufferedImage(mosaicPanel.getSize().width, 
                        mosaicPanel.getSize().height, 
                        BufferedImage.TYPE_INT_RGB);
                Graphics g = tempBuffer.createGraphics();
                mosaicPanel.paint(g);  
                g.dispose();
                try {
                    ImageIO.write(tempBuffer, "png", new File(fileChooser.getSelectedFile().getPath()+".png"));
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null, "No se pudo exportar la imagen", 
                            "Exportar a png", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        // Acciones para abrir el projecto
        if (e.getSource() == openProject_btn) {
            //Se crea el jFileChooser
            JFileChooser fileChooser = new JFileChooser();

            int returnVal = fileChooser.showOpenDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    //Se carga el proyecto desde los archivos
                    ProjectFile projectFile = new ProjectFile(fileChooser.getSelectedFile().getPath());
                    projectFile.chargeProject();

                    // Se actualizan los paneles
                    imagePanel.revalidate();
                    imagePanel.repaint();
                    mosaicPanel.revalidate();
                    mosaicPanel.repaint();
                    
                    //Se visible el mosaico
                    mosaicPanel.setBackground(Color.WHITE);

                } catch (IOException | ClassNotFoundException | ListExeption ex) {
                    JOptionPane.showMessageDialog(null, "El archivo no es válido o está dañado", 
                            "Abrir proyecto", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }//fin if

        if (e.getSource() == insert_btn) {
            JFileChooser fileChooser = new JFileChooser();

            //configura los filtros del jfilechooser
            fileChooser.setFileFilter(new FileNameExtensionFilter("Imagenes", "jpg", "png"));
            fileChooser.setAcceptAllFileFilterUsed(false);

            int returnVal = fileChooser.showOpenDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                ImageFile imageFile = new ImageFile(fileChooser.getSelectedFile().getPath());
                Images myImage = Images.getInstance();
                myImage.setImage(imageFile.loadAnImage());

                // Se revalida el JPanel
                imagePanel.revalidate();
                imagePanel.repaint();
                mosaicPanel.revalidate();
                mosaicPanel.repaint();
                
                //Se visible el mosaico
                mosaicPanel.setBackground(Color.WHITE);
            }
        }

        // Acciones para guardar el proyecto
        if (e.getSource() == saveProject_btn) {
            JFileChooser fileChooser = new JFileChooser();

            int returnVal = fileChooser.showSaveDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    ProjectFile projectFile = new ProjectFile(fileChooser.getSelectedFile().getPath());
                    // Se pregunta si es primera vez que se guarda el proyecto o no
                    if (newProjectPanel.getProject() != null) {
                        projectFile.saveProject(newProjectPanel.getProject());
                    } else {
                        Project myProject = new Project();
                        projectFile.saveProject(myProject);
                    }
                } catch (IOException ex) {
                    System.err.println("ups!");
                }
            }
        }//fin if

        //Acciones para crear el proyecto
        if (e.getSource() == createProject_btn) {
            newProjectPanel.setVisible(true);
        }//fin if

        // Rotar a la derecha
        if (e.getSource() == rotateRight_btn) {
            Mosaic myMosaic = Mosaic.getInstance();
            myMosaic.rotateRight();

            mosaicPanel.revalidate();
            mosaicPanel.repaint();
        }//fin if

        //Rotar a la izquierda
        if (e.getSource() == rotateLeft_btn) {
            Mosaic myMosaic = Mosaic.getInstance();
            myMosaic.rotateLeft();

            mosaicPanel.revalidate();
            mosaicPanel.repaint();
        }//fin if

        //Voltear horizontalmente
        if (e.getSource() == flipHorizontal_btn) {
            Mosaic myMosaic = Mosaic.getInstance();
            myMosaic.flipHorizontal();

            mosaicPanel.revalidate();
            mosaicPanel.repaint();
        }//fin if

        //Voltear verticalmente
        if (e.getSource() == flipVertical_btn) {
            Mosaic myMosaic = Mosaic.getInstance();
            myMosaic.flipVertical();

            mosaicPanel.revalidate();
            mosaicPanel.repaint();
        }//fin if

        //Borrar imagen
        if (e.getSource() == delete_btn) {
            Mosaic myMosaic = Mosaic.getInstance();
            myMosaic.deleteBuffered();

            mosaicPanel.revalidate();
            mosaicPanel.repaint();
        }//fin if

    }//fin actionPerformed

    //Métodos de acceso  
    public ImagePanel getImagePanel() {
        return imagePanel;
    }

    public void setImagePanel(ImagePanel ic) {
        this.imagePanel = ic;
    }

    public MosaicPanel getMosaicPanel() {
        return mosaicPanel;
    }

    public void setMosaicPanel(MosaicPanel mo) {
        this.mosaicPanel = mo;
    }

}//fin MenuPanel
