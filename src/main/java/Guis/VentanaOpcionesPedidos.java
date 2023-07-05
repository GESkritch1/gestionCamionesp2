package Guis;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaOpcionesPedidos extends JFrame{
    private JTextField pedidosTextField;
    private JButton verPedidosButton;
    private JButton hacerPedidoButton;
    private JButton borrarPedidoButton;
    private JButton volverButton;
    private JPanel mainPanel;

    public VentanaOpcionesPedidos(){
        setContentPane(mainPanel);
        setTitle("Menu opciones Choferes");
        setSize(900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaAdmin v = new VentanaAdmin();
                v.setVisible(true);
                setVisible(false);
            }
        });
    }

}
