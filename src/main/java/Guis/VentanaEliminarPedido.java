package Guis;

import Archivador.Archivador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaEliminarPedido extends JFrame{
    private JTextField pedidoTemp;
    private JButton volverALaVentanaButton;
    private JButton guardarYVolverButton;
    private JPanel mainPanel;

    public VentanaEliminarPedido() {
        setContentPane(mainPanel);
        setTitle("Eliminarcion Pedido");
        setSize(900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        guardarYVolverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoaEliminar = pedidoTemp.getText();
                Archivador.eliminarPedidoArchivotxt(codigoaEliminar,"listaPedidos.txt", mainPanel);
                VentanaOpcionesPedidos v = new VentanaOpcionesPedidos();
                v.setVisible(true);
                setVisible(false);
            }
        });
        volverALaVentanaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaOpcionesPedidos v = new VentanaOpcionesPedidos();
                v.setVisible(true);
                setVisible(false);
            }
        });
    }
}
