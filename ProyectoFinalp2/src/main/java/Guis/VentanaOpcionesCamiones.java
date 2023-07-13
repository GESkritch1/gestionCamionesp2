package Guis;

import ContextoProblema.Admin;
import ContextoProblema.Camion;
import ContextoProblema.Chofer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaOpcionesCamiones extends JFrame {
    private JPanel mainPanel;
    private JButton agregarCamionButton;
    private JButton eliminarCamionesButton;
    private JButton agregarChoferAAlgunButton;
    private JButton mostrarCamionesButton;
    private JButton volverALaVentanaButton;


    public VentanaOpcionesCamiones() {
        setContentPane(mainPanel);
        setTitle("Opciones Camiones");
        setSize(900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        agregarCamionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaAgregarCamion v = new VentanaAgregarCamion();
                v.setVisible(true);
                setVisible(false);
            }
        });

        volverALaVentanaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaAdmin v = new VentanaAdmin();
                v.setVisible(true);
                setVisible(false);
            }
        });

        eliminarCamionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaEliminarCamion v = new VentanaEliminarCamion();
                v.setVisible(true);
                setVisible(false);
            }
        });

        mostrarCamionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Admin.mostrarCamiones(mainPanel);
                } catch (Exception E) {
                    JOptionPane.showMessageDialog(mainPanel, "El archivo no existe");
                }
            }
        });

        agregarChoferAAlgunButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaAgregarChoferaCamion v = new VentanaAgregarChoferaCamion();
                v.setVisible(true);
                setVisible(false);
            }
        });

    }
}
