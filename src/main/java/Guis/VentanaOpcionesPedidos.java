package Guis;

import Archivador.Archivador;
import ContextoProblema.Admin;
import ContextoProblema.Camion;
import ContextoProblema.Pedido;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VentanaOpcionesPedidos extends JFrame{
    private JTextField pedidosTextField;
    private JButton verPedidosButton;
    private JButton hacerPedidoButton;
    private JButton borrarPedidoButton;
    private JButton volverButton;
    private JPanel mainPanel;
    private Admin admin = new Admin();

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
        hacerPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Archivador archivador = new Archivador();
                List<Camion> listaCamiones = archivador.convertirArchivoCamiones("listaCamiones.txt");

                Pedido pedido = new Pedido();


                // Parámetros del nuevo pedido
                String codigo = "0001";
                String patenteCamion = "DADADA"; // Patente del camión deseado
                int fechaInicial = 20230712; // Fecha inicial en formato numérico
                int fechaFinal = 20230715; // Fecha final en formato numérico
                String lugarSalida = "Temuco";
                String lugarDestino = "Valdivia";
                int tiempo = 3;
                int distancia = 300;
                admin.agregarPedido(codigo, patenteCamion, fechaInicial, fechaFinal, distancia, lugarDestino, tiempo, mainPanel);



            }
        });
    }

}
