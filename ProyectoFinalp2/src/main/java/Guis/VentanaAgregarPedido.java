package Guis;

import Archivador.Archivador;
import ContextoProblema.Admin;
import ContextoProblema.Camion;
import UsosyMas.Utilitarios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

public class VentanaAgregarPedido extends JFrame{
    private JPanel mainPanel;
    private JComboBox comboBox1;
    private JTextField patenteElejidaTextField;
    private JButton volverALaVentanaButton;
    private JButton guardarYVolverButton;
    public String eleccionTemp;
    private Admin admin = new Admin();

    public VentanaAgregarPedido() {
            setContentPane(mainPanel);
            setTitle("Agregar Pedido");
            setSize(900, 600);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setVisible(true);

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eleccionTemp = comboBox1.getSelectedItem().toString();
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
        guardarYVolverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Archivador archivador = new Archivador();
                List<Camion> listaCamiones = archivador.convertirArchivoCamiones("listaCamiones.txt");
                String codigo = Utilitarios.validadorDeCodigoPedido();
                String patenteCamion = patenteElejidaTextField.getText(); // Patente del camión deseado
                String fechaInicial = String.valueOf(Utilitarios.obtenerFechaHoraActual()); // Fecha inicial en formato numérico
                String lugarSalida = "Temuco";
                String lugarDestino = eleccionTemp;
                LocalDateTime fechaFinal = Utilitarios.obtenerFechaHoraFinal(lugarDestino); // Fecha final en formato numérico
                int tiempo = Utilitarios.horaAprox(lugarDestino);
                int distancia = Utilitarios.distancia(lugarDestino);
                admin.agregarPedido(codigo, patenteCamion, fechaInicial, fechaFinal, distancia, lugarDestino, tiempo, mainPanel);




            }
        });
    }
}
