package graphics;

import domain.Project;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import tda.ListExeption;

/**
 *
 * @author 
 */
public class NewProyectPanel extends JFrame implements ActionListener {

    //Atributos
    private JLabel lblnameProyect;
    private JLabel lblAuthor;
    private JTextField txtnameProyect;
    private JTextField txtAuthor;
    private JLabel image;
    private JLabel lblblock;
    private JTextField txtblock;
    public JButton btnNewProject;
    private int blockSize;
    private JLabel lblerror = new JLabel("Ingrese solo números");
    private JLabel lblerror2 = new JLabel("Debe ingresar un número entre 10 al 100");


    public boolean estate;
    public int cont = 0;
    Project project;

    //Constructor
    public NewProyectPanel() {   

        setLayout(null);
        getContentPane().setBackground(Color.white);
        setSize(455, 500);
        setTitle("Nuevo proyecto");
        setLocation(400, 100);
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icons/new_p.png")));

        Icon icon = new ImageIcon(getClass().getResource("/icons/fondonew.jpg"));
        image = new JLabel();
        image.setIcon(icon);
        image.setBounds(0, 0, 500, 70);
        add(image);

        lblnameProyect = new JLabel("Nombre del proyecto");
        lblnameProyect.setBounds(90, 90, 140, 20);
        lblnameProyect.setFont(new Font("times new roman", Font.BOLD, 15));
        add(lblnameProyect);

        lblAuthor = new JLabel("Autor");
        lblAuthor.setBounds(90, 120, 60, 20);
        lblAuthor.setFont(new Font("times new roman", Font.BOLD, 15));
        add(lblAuthor);

        lblblock = new JLabel("Tamaño de los bloques");
        lblblock.setBounds(90, 160, 150, 20);
        lblblock.setFont(new Font("times new roman", Font.BOLD, 15));
        add(lblblock);

        txtAuthor = new JTextField();
        txtAuthor.setBounds(250, 123, 100, 20);
        add(txtAuthor);

        txtnameProyect = new JTextField();
        txtnameProyect.setBounds(250, 93, 100, 20);
        add(txtnameProyect);

        txtblock = new JTextField();
        txtblock.setBounds(250, 160, 100, 20);
        add(txtblock);

        btnNewProject = new JButton(new ImageIcon("src/icons/crearJpanel.png"));
        btnNewProject.setBounds(300, 200, 40, 40);
        btnNewProject.setBackground(Color.WHITE);
        btnNewProject.setToolTipText("Crear proyecto");
        add(btnNewProject);

        btnNewProject.addActionListener(this);
        
        lblerror.setBounds(250, 290, 150, 20);
        add(lblerror);
        lblerror.setForeground(Color.red);
        lblerror.setVisible(false);

        lblerror2.setBounds(205, 290, 240, 20);
        add(lblerror2);
        lblerror2.setForeground(Color.red);
        lblerror2.setVisible(false);

    } // fin constructor

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == btnNewProject) {
            estate = estate(1);
            try {
                blockSize = Integer.parseInt(txtblock.getText());

                // Validaciones de campos
                if ((txtnameProyect.getText().equals("") || txtAuthor.getText().equals("") || txtblock.getText().equals(""))) {
                    JOptionPane.showMessageDialog(null, "Debe llenar todos los espacios");

                } else if (blockSize < 10 || blockSize > 100) { //se valida el tamaño de los bloques
                    lblerror2.setVisible(true);
                    txtblock.setText("");

                } else { // si todo esta bien entra aqui
                    
                    cont = 1;
                    
                    lblerror.setVisible(false);
                    lblerror2.setVisible(false);

                    project = new Project(txtnameProyect.getText(), blockSize);
                    
                    txtAuthor.setText("");
                    txtblock.setText("");
                    
                    this.dispose();
                }
            } catch (NumberFormatException e) {
                lblerror.setVisible(true);
                txtblock.setText("");

            } catch (IOException ex) {
                Logger.getLogger(NewProyectPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public boolean estate(int num) {
        if (num == 1) {
            return true;
        } else {
            return false;
        }
    } //fin estate

    //Métodos de acceso
    public Project getProject() {
        return project;
    }
}
