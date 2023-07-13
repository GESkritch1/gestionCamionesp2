package UsosyMas;

import Archivador.Archivador;

import ContextoProblema.Pedido;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


public class Utilitarios {

    public static String validadorDeCodigoPedido() {
        Archivador archivador = new Archivador();
        List<Pedido> codigos = archivador.cargarPedidoDesdeArchivo("listaPedido.txt");
        String numero = String.valueOf((int) (Math.random() * 1000000 + 100));
        boolean valid = false;
        do {
            for (int i = 0; i < codigos.size(); i++) {
                if (codigos.get(i).equals(numero)) {
                    numero = String.valueOf((int) (Math.random() * 1000000 + 100));
                }
                if (!codigos.get(i).equals(numero)) {
                    valid = true;
                }
            }
        } while (valid = false);
        return numero;
    }

    public static LocalDateTime obtenerFechaHoraActual() {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        return fechaHoraActual;
    }

    public static LocalDateTime obtenerFechaHoraFinal(String ciudadElegida) {
        LocalDateTime fechaHoraActual = obtenerFechaHoraActual();
        int hora = fechaHoraActual.getHour();
        int minutos = fechaHoraActual.getMinute();

        int dia = fechaHoraActual.getDayOfMonth();
        int mes = fechaHoraActual.getMonthValue();
        int año = fechaHoraActual.getYear();

        Archivador archivador = new Archivador();
        List<Distancia> Ciudades = archivador.cargarDistanciaDesdeArchivo("distanciasDesdeTemuco.txt");
        int distancia = 0;
        for (Distancia c : Ciudades) {
            if (c.getCiudad().equals(ciudadElegida)) {
                distancia = c.getDistancia();
                break;
            }
        }

        int suma = distancia / 100;
        int horaFinal = hora + suma+2;

        // Verificar si la entrega se realiza en el día siguiente
        LocalTime horaLimite = LocalTime.of(23, 59);
        if (LocalTime.of(horaFinal, minutos).isAfter(horaLimite)) {
            dia++;
            horaFinal -= 24;
        }

        LocalDateTime fechaHoraFinal = LocalDateTime.of(año, mes, dia, horaFinal, minutos);
        return fechaHoraFinal;
    }

    public static int distancia(String ciudadElegida) {
        Archivador archivador = new Archivador();
        LocalDateTime fechaHoraActual = obtenerFechaHoraActual();
        List<Distancia> Ciudades = archivador.cargarDistanciaDesdeArchivo("distanciasDesdeTemuco.txt");
        int distancia = 0;
        for (Distancia c : Ciudades) {
            if (c.getCiudad().equals(ciudadElegida)) {
                distancia = c.getDistancia();
                break;
            }
        }
        return distancia;
    }

    public static String obtenerHoraActual() {
        LocalDateTime fechaHoraActual = obtenerFechaHoraActual();
        int hora = fechaHoraActual.getHour();
        int minutos = fechaHoraActual.getMinute();

        int dia = fechaHoraActual.getDayOfMonth();
        int mes = fechaHoraActual.getMonthValue();
        int año = fechaHoraActual.getYear();
        String fechaActual = (dia + "-" + mes + "-" + año + " a las: " + hora + ":" + minutos);

        return fechaActual;
    }

    public static int horaAprox(String ciudadElegida) {
        Archivador archivador = new Archivador();
        LocalDateTime fechaHoraActual = obtenerFechaHoraActual();
        List<Distancia> Ciudades = archivador.cargarDistanciaDesdeArchivo("distanciasDesdeTemuco.txt");
        int distancia = 0;
        for (Distancia c : Ciudades) {
            if (c.getCiudad().equals(ciudadElegida)) {
                distancia = c.getDistancia();
                break;
            }
        }

        int suma = distancia / 100;
        suma = suma +2;
        return suma;
    }
}