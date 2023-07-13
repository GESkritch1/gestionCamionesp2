package Guis;

import ContextoProblema.Admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAgregarChofer extends JFrame {
    private JPanel mainPanel;
    private JTextField nombreTemp;
    private JTextField rutTemp;
    private JTextField edadTemp;
    private JTextField licenciaTemp;
    private JTextField estadoChoferTemp;
    private JButton volverALaVentanaButton;
    private JButton guardarYVolverButton;

    public VentanaAgregarChofer(){
        setContentPane(mainPanel);
        setTitle("Menu opciones Choferes");
        setSize(900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        volverALaVentanaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaOpcionesChoferes v = new VentanaOpcionesChoferes();
                v.setVisible(true);
                setVisible(false);
            }
        });
        guardarYVolverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombre = nombreTemp.getText();
                    String rut = rutTemp.getText();
                    int edad = Integer.parseInt(edadTemp.getText());

                    String estadoChoferTemp2=estadoChoferTemp.getText();
                    boolean estadoChofer;

                    if (estadoChoferTemp2.equals("si")){
                        estadoChofer = true;
                    }else if (estadoChoferTemp2.equals("no")){
                        estadoChofer = false;
                    }else{
                        throw new IllegalStateException("Esa opcion no existe en el estado de Chofer");
                    }
                    String licenciaTemp2 = licenciaTemp.getText();
                    String licencia;
                    if (licenciaTemp2.equals("A4")||licenciaTemp2.equals("A5")){
                         licencia = licenciaTemp2;
                    }else {
                        throw new IllegalStateException("Esa opcion no existe en el tipo de licencia");
                    }

                    Admin.agregarChofer(nombre, rut,edad,licencia,estadoChofer,mainPanel);
                    JOptionPane.showMessageDialog(mainPanel, "se guardo correctamente");
                    VentanaOpcionesChoferes v = new VentanaOpcionesChoferes();
                    v.setVisible(true);
                    setVisible(false);
                }catch (Exception E){
                    JOptionPane.showMessageDialog(mainPanel, "ocurrio un error revise los datos puestos");
                }

            }
        });
    }
}
