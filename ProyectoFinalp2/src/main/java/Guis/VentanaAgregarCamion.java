package Guis;

import ContextoProblema.Admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAgregarCamion extends JFrame{
    private JTextField setPatente;
    private JTextField setPermisoCirculacion;
    private JTextField setRevisionTecnica;
    private JTextField setEstadoActual;
    private JTextField setCargaMax;
    private JPanel mainPanel;
    private JButton guardarYVolverButton;
    private JButton volverALaPestañaButton;

    public VentanaAgregarCamion(){
        setContentPane(mainPanel);
        setTitle("Agregar Camion");
        setSize(900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        guardarYVolverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String patente = setPatente.getText();
                    String permisoCirculacionTemp = setPermisoCirculacion.getText();
                    boolean permisoCirculacion;
                    if (permisoCirculacionTemp.equals("si")){
                        permisoCirculacion = true;
                    }else if (permisoCirculacionTemp.equals("no")){
                        permisoCirculacion = false;
                    }else{
                        throw new IllegalStateException("Esa opcion no existe en el permiso de circulacion");
                    }
                    String revisionTecnicaTemp = setRevisionTecnica.getText();
                    boolean revisionTecnica;
                    if (revisionTecnicaTemp.equals("si")){
                        revisionTecnica = true;
                    }else if (revisionTecnicaTemp.equals("no")){
                        revisionTecnica = false;
                    }else{
                        throw new IllegalStateException("Esa opcion no existe la revision tecnica");
                    }
                    String estadoActualTemp = setEstadoActual.getText();
                    boolean estadoActual;
                    if (estadoActualTemp.equals("si")){
                        estadoActual = true;
                    }else if (estadoActualTemp.equals("no")){
                        estadoActual = false;
                    }else{
                        throw new IllegalStateException("Esa opcion no existe en el estado actual");
                    }
                    int cargaMax = Integer.parseInt(setCargaMax.getText());

                    Admin.agregarCamion(patente,permisoCirculacion,revisionTecnica, estadoActual, cargaMax,mainPanel);
                    VentanaOpcionesCamiones v = new VentanaOpcionesCamiones();
                    v.setVisible(true);
                    setVisible(false);
                }catch (Exception E){
                    JOptionPane.showMessageDialog(mainPanel, "ocurrio un error revise los datos puestos");
                }
            }
        });
        volverALaPestañaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaOpcionesCamiones v = new VentanaOpcionesCamiones();
                v.setVisible(true);
                setVisible(false);
            }
        });
    }


}
