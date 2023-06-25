package Guis;

import ContextoProblema.Admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaEliminarCamion extends JFrame {
    private JPanel mainPanel;
    private JTextField setPatenteaEliminar;
    private JButton eliminarCamionYVolverButton;
    private JButton volverALaVentanaButton;


    public VentanaEliminarCamion() {

        setContentPane(mainPanel);
        setTitle("Eliminar camion ");
        setSize(900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        eliminarCamionYVolverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String patente = setPatenteaEliminar.getText();
                    Admin.eliminarCamion(patente, mainPanel);
                    VentanaOpcionesCamiones v = new VentanaOpcionesCamiones();
                    v.setVisible(true);
                    setVisible(false);
                }catch (Exception E){
                    JOptionPane.showMessageDialog(mainPanel, "No se pudo eliminar ese camion o quiza no existe");
                }
            }
        });
        volverALaVentanaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaOpcionesCamiones v = new VentanaOpcionesCamiones();
                v.setVisible(true);
                setVisible(false);
            }
        });
    }
}
