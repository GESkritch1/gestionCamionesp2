package ContextoProblema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Chofer implements Serializable {
	public static List<Chofer> listaChoferes = new ArrayList<>();

	Camion transporte;
	private String nombre;
	private String rut;
	private int edad;
	private boolean licencia;
	private boolean estadoChofer;



	public Chofer(String nombre, String rut, int edad, boolean licencia, boolean estadoChofer) {
		this.nombre = nombre;
		this.rut = rut;
		this.edad = edad;
		this.licencia = licencia;
		this.estadoChofer = estadoChofer;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getRut() {
		return this.rut;
	}

	public int getEdad() {
		return this.edad;
	}

	public boolean getLicencia() {
		return this.licencia;
	}

	public boolean getEstadoChofer() {
		return this.estadoChofer;
	}


	public void agregarChoferLista() {
		listaChoferes.add(this);
	}

	public void editarChoferLista(Chofer nuevoChofer) {
		int index = listaChoferes.indexOf(this);
		if (index != 1){
			listaChoferes.set(index, nuevoChofer);
		}else {
			System.out.println("El chofer no existe en la lista ");
		}
	}

	public void elimarChoferLista() {
		listaChoferes.remove(this);
	}



	public boolean isEstadoChofer() {
		return this.estadoChofer;
	}

	public void setEstadoChofer(boolean estadoChofer) {
		this.estadoChofer = estadoChofer;
	}

	public static void verTodosLosChoferes() {
	}
}