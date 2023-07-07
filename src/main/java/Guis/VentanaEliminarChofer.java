package Guis;

import ContextoProblema.Admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaEliminarChofer extends JFrame {
    private JPanel mainPanel;
    private JTextField rutTemp;
    private JButton volverALaVentanaButton;
    private JButton guardarYVolverButton;

    public VentanaEliminarChofer(){
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
                String rut = rutTemp.getText();
                Admin.eliminarChofer(rut, mainPanel);
                VentanaOpcionesChoferes v = new VentanaOpcionesChoferes();
                v.setVisible(true);
                setVisible(false);
            }
        });
    }

}
