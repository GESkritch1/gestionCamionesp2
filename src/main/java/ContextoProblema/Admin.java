package ContextoProblema;

import Archivador.Archivador;

import javax.swing.*;
import java.util.Scanner;


public class Admin {
	static Archivador archivador = new Archivador();
	static Scanner sc = new Scanner(System.in);

	public static void mostrarCamiones(JPanel mainPanel) {
		archivador.mostrarArchivo("listaCamiones.txt", mainPanel);
	}

	public static void eliminarChofer(String choferaEliminar, JPanel mainPanel) {

		archivador.eliminarChoferArchivoTxt(choferaEliminar, "listaChoferes.txt", mainPanel);
	}

	public static void mostrarChoferes(JPanel mainPanel) {
		archivador.mostrarArchivo("listaChoferes.txt", mainPanel);
	}

	public static void agregarChofer(String nombre, String rut, int edad, String licencia, boolean estadoChofer) {
		Chofer nuevoChofer = new Chofer(nombre, rut, edad, licencia, estadoChofer);

		// Agregar el nuevo chofer a la lista
		nuevoChofer.agregarChoferLista();

		// Guardar el nuevo chofer en el archivo de texto
		archivador.agregarChoferATexto("listaChoferes.txt", nuevoChofer);

		System.out.println("Chofer agregado exitosamente.");
	}

	public static void eliminarCamion(String camionaEliminar, JPanel mainPanel) {

		archivador.eliminarCamionArchivotxt(camionaEliminar, "listaCamiones.txt", mainPanel);
	}

	public static void agregarCamion(String patente, boolean permisoCirculacion, boolean revisionTecnica, boolean estadoActual, int cargaMax,JPanel mainPanel) {
		Camion nuevoCamion = new Camion(patente, permisoCirculacion, revisionTecnica, estadoActual, cargaMax);


		// Agregar el nuevo camión a la lista
		nuevoCamion.agregarCamionLista();

		// Guardar el nuevo camión en el archivo de texto
		archivador.agregarCamionATexto("listaCamiones.txt", nuevoCamion,mainPanel);
	}

	public static void agregarChoferaCamionAD(String patente, String rut, JPanel mainPanel){
		archivador.agregarChoferaCamion("listaCamionConChofer.txt", patente, rut, mainPanel);
	}

}