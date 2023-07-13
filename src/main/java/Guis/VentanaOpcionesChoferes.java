package Guis;

import ContextoProblema.Admin;
import ContextoProblema.Chofer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaOpcionesChoferes extends JFrame{

    private JPanel mainPanel;
    private JButton agregarChoferALaButton;
    private JButton eliminarChoferDeLaButton;
    private JButton mostrarChoferesDeLaButton;
    private JButton volverALaVentanaButton;
    private JButton editarChoferesButton;

    public VentanaOpcionesChoferes() {
        setContentPane(mainPanel);
        setTitle("Menu opciones Choferes");
        setSize(900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        agregarChoferALaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaAgregarChofer v = new VentanaAgregarChofer();
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
        mostrarChoferesDeLaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Admin.mostrarChoferes(mainPanel);

                } catch (Exception E) {
                    JOptionPane.showMessageDialog(mainPanel, "El archivo no existe");
                }
            }
        });
        eliminarChoferDeLaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaEliminarChofer v = new VentanaEliminarChofer();
                v.setVisible(true);
                setVisible(false);


            }
        });
    }
}
