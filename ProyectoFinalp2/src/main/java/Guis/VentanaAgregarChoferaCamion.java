package Guis;

import ContextoProblema.Admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAgregarChoferaCamion extends JFrame {
    private JPanel mainPanel;
    private JTextField patenteTemp;
    private JTextField rutTemp;
    private JButton guardarYVolverButton;
    private JButton volverALaVentanaButton;

    public VentanaAgregarChoferaCamion() {
        setContentPane(mainPanel);
        setTitle("Agregar Chofer a Camion");
        setSize(900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        volverALaVentanaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaOpcionesCamiones v = new VentanaOpcionesCamiones();
                v.setVisible(true);
                setVisible(false);
            }
        });
        guardarYVolverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String patente = patenteTemp.getText();
                    String rut = rutTemp.getText();
                    Admin.agregarChoferaCamionAD(patente, rut, mainPanel);
                }catch (Exception E){
                    JOptionPane.showMessageDialog(mainPanel, "no existe ese dato");
                }

                VentanaOpcionesCamiones v = new VentanaOpcionesCamiones();
                v.setVisible(true);
                setVisible(false);
            }
        });
    }
}
