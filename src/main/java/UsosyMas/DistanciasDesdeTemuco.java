package UsosyMas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DistanciasDesdeTemuco {
    private static Map<String, Integer> distancias;

    public DistanciasDesdeTemuco() {
        distancias = new HashMap<>();
        cargarDistancias();
    }

    private void cargarDistancias() {
        try (BufferedReader br = new BufferedReader(new FileReader("distanciasDesdeTemuco.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(":");
                if (datos.length == 2) {
                    String destino = datos[0].trim();
                    int distancia = Integer.parseInt(datos[1].trim().split(" ")[0]);
                    distancias.put(destino, distancia);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int obtenerDistancia(String destino) {
        return distancias.getOrDefault(destino, -1);
    }

    public static int obtenerTiempoEstimado(String destino) {
        int distancia = obtenerDistancia(destino);
        if (distancia != -1) {
            return distancia / 100; // Suponiendo una velocidad promedio de 100 km/h
        }
        return -1;
    }

}