package Guis;

import javax.swing.*;

public class VentanaOpcionesChoferes extends JFrame{

    private JPanel mainPanel;

    public VentanaOpcionesChoferes() {
        setContentPane(mainPanel);
        setTitle("Menu opciones Choferes");
        setSize(900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
