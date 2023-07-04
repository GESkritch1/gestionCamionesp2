import ContextoProblema.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminTest {

    @Test
    void agregarCamionTest() {
        String input = "ABC123\ntrue\ntrue\ntrue\n1000\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Admin admin = new Admin();

        List<Camion> camiones = Camion.listaCamiones;
        assertEquals(3, camiones.size());

        Camion camion = camiones.get(0);
        assertEquals("ABC123", camion.getPatente());
        assertTrue(camion.getPermisoCirculacion());
        assertTrue(camion.getRevisionTecnica());
        assertTrue(camion.getEstadoActual());
        assertEquals(1000, camion.getCargaMax());
    }

    @Test
    void agregarChoferTest() {
        String input = "John Doe\n12345678-9\n30\ntrue\ntrue\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Admin admin = new Admin();
        admin.agregarChofer();

        List<Chofer> choferes = Chofer.listaChoferes;
        assertEquals(1, choferes.size());

        Chofer chofer = choferes.get(0);
        assertEquals("John Doe", chofer.getNombre());
        assertEquals("12345678-9", chofer.getRut());
        assertEquals(30, chofer.getEdad());
        assertTrue(chofer.getLicencia());
        assertTrue(chofer.getEstadoChofer());
    }

    @Test
    void mostrarCamionesTest() {
        Camion.listaCamiones = new ArrayList<>();
        Camion camion1 = new Camion("ABC123", true, true, true, 1000);
        Camion camion2 = new Camion("DEF456", true, true, true, 2000);
        Camion.listaCamiones.add(camion1);
        Camion.listaCamiones.add(camion2);

        Admin admin = new Admin();
        admin.mostrarCamiones();

        // Asegurar que se muestren los datos de los camiones correctamente
    }

    @Test
    void mostrarChoferesTest() {
        Chofer.listaChoferes = new ArrayList<>();
        Chofer chofer1 = new Chofer("John Doe", "12345678-9", 30, true, true);
        Chofer chofer2 = new Chofer("Jane Smith", "98765432-1", 35, false, true);
        Chofer.listaChoferes.add(chofer1);
        Chofer.listaChoferes.add(chofer2);

        Admin admin = new Admin();
        admin.mostrarChoferes();

        // Asegurar que se muestren los datos de los choferes correctamente
    }
}