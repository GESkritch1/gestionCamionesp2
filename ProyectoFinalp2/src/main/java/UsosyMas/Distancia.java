package UsosyMas;

import ContextoProblema.Camion;

import java.util.ArrayList;
import java.util.List;

public class Distancia {
    public static List<Distancia> listaCiudades = new ArrayList<>();
    private String ciudad;
    private int distancia;

    public Distancia() {
    }


    public String getCiudad() {
        return ciudad;
    }

    public int getDistancia() {
        return distancia;
    }


    public Distancia(String ciudad, int distancia){
        this.ciudad= ciudad;
        this.distancia=distancia;
    }

    public void agregarCiudades() {
        listaCiudades.add(this);
    }

}
