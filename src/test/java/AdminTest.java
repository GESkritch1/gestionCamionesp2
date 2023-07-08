import Archivador.Archivador;
import ContextoProblema.Admin;
import ContextoProblema.Chofer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.JPanel;
import java.io.*;

public class AdminTest {
    private JPanel mainPanel;

    @BeforeEach
    public void setUp() {
        mainPanel = new JPanel();
    }

    @Test
    public void testMostrarCamiones() {
        Admin.mostrarCamiones(mainPanel);

    }
    @Test
    public void testEliminarChofer() {
        String choferAEliminar = "John Doe";

        Admin.eliminarChofer(choferAEliminar, mainPanel);
    }

    @Test
    public void testMostrarChoferes() {
        Admin.mostrarChoferes(mainPanel);
    }
    @Test
    public void testAgregarChofer() {
        // Arrange
        String nombre = "John Doe";
        String rut = "123456789";
        int edad = 30;
        String licencia = "A5";
        boolean estadoChofer = true;

        Admin.agregarChofer(nombre, rut, edad, licencia, estadoChofer);
    }

    @Test
    public void testEliminarCamion() {
        String camionAEliminar = "ABC123";
        Admin.eliminarCamion(camionAEliminar, mainPanel);
    }

    @Test
    public void testAgregarCamion() {
        // Arrange
        String patente = "ABC123";
        boolean permisoCirculacion = true;
        boolean revisionTecnica = true;
        boolean estadoActual = true;
        int cargaMax = 5000;
        Admin.agregarCamion(patente, permisoCirculacion, revisionTecnica, estadoActual, cargaMax, mainPanel);
    }
    @Test
    public void testAgregarChoferaCamionAD() {
        String patente = "ABC123";
        String rut = "123456789";
        Admin.agregarChoferaCamionAD(patente, rut, mainPanel);
    }
    public void mostrarArchivoTest() {
        String rutaArchivo = "archivo_prueba.txt";
        String contenidoArchivo = "Línea 1\nLínea 2\nLínea 3";
        crearArchivoDePrueba(rutaArchivo, contenidoArchivo);

        Archivador archivador = new Archivador();
        archivador.mostrarArchivo(rutaArchivo, null);
    }

    @Test
    public void agregarChoferATextoTest() {
        String nombreArchivo = "choferes.txt";
        Chofer chofer = new Chofer("Juan Perez", "12345678-9", "Licencia123", true);

        Archivador archivador = new Archivador();
        archivador.agregarChoferATexto(nombreArchivo, chofer);
        File archivo = new File(nombreArchivo);
        Assertions.assertTrue(archivo.exists());
    }
    private void crearArchivoDePrueba(String rutaArchivo, String contenido) {
        try {
            FileWriter fileWriter = new FileWriter(rutaArchivo);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter writer = new PrintWriter(bufferedWriter);
            writer.write(contenido);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}