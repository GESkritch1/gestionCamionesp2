package Guis;

import ContextoProblema.Admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAdmin extends JFrame{
    private JButton opcionesPedidoButton;
    private JButton opcionesCamionesButton;
    private JButton opcionesChoferesButton;
    private JPanel mainPanel;
    private JButton botonDePruebasButton;

    public VentanaAdmin() {
        setContentPane(mainPanel);
        setTitle("Admin");
        setSize(900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        opcionesCamionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaOpcionesCamiones v = new VentanaOpcionesCamiones();
                v.setVisible(true);
                setVisible(false);
            }
        });
        opcionesChoferesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaOpcionesChoferes v = new VentanaOpcionesChoferes();
                v.setVisible(true);
                setVisible(false);
            }
        });
        botonDePruebasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Admin.pruebas();
            }
        });
    }
}
