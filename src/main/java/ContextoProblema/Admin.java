package ContextoProblema;

import Archivador.Archivador;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;


public class Admin {
	static Archivador archivador = new Archivador();
	static Scanner sc = new Scanner(System.in);

	public void opcionesPedido() {

	}

	public void opcionesCamiones() {
		System.out.println("(1) agregar un camion a la lista de camiones");
		System.out.println("(2) eliminar Camion ");
		System.out.println("(3) agregar Chofer a algun camion (en mantenimiento)");
		System.out.println("(4) mostrar Camiones de la lista");
		int i = Utilitarios.leerEntero();

		switch (i) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				System.out.println("esta en mantenimiento");
				break;
			case 4:
				break;
			default:
				System.out.println("no existe esa opcion");
				break;


		}
	}

	public static void mostrarCamiones(JPanel mainPanel) {
		archivador.mostrarArchivo("listaCamiones.txt", mainPanel);
	}

	public void opcionesChoferes() {
		System.out.println("(1) agregar Chofer a la lista");
		System.out.println("(2) mostrar Choferes de la lista");
		System.out.println("(3) eliminar Chofer de la lista");
		int i = Utilitarios.leerEntero();
		switch (i) {
			case 1:
				agregarChofer();
				break;
			case 2:
				break;
			case 3:
				eliminarChofer();
				break;
			default:
				System.out.println("no hay mas funciones por ahora");
		}

	}

	private void eliminarChofer() {
		System.out.println("ingrese el rut del chofer que desea eliminar: ");
		String choferaEliminar = sc.nextLine();
		archivador.eliminarChoferArchivoTxt(choferaEliminar, "listaChoferes.txt");
	}

	public void mostrarChoferes(JPanel mainPanel) {
		archivador.mostrarArchivo("listaChoferes.txt", mainPanel);
	}

	public void agregarChofer() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Ingrese los datos del Chofer:");

		System.out.print("Nombre: ");
		String nombre = scanner.nextLine();

		System.out.print("Rut: ");
		String rut = scanner.nextLine();

		System.out.print("Edad: ");
		int edad = Utilitarios.leerEntero();

		boolean licencia = Utilitarios.solicitarBooleano("Licencia (true=tiene al día, false=no la tiene al día): ");
		boolean estado = Utilitarios.solicitarBooleano("Estado del Chofer (true=trabajando, false=no puede trabajar): ");

		Chofer nuevoChofer = new Chofer(nombre, rut, edad, licencia, estado);

		// Agregar el nuevo camión a la lista
		nuevoChofer.agregarChoferLista();

		// Guardar el nuevo camión en el archivo de texto
		archivador.agregarChoferATexto("listaChoferes.txt", nuevoChofer);

		System.out.println("Chofer agregado exitosamente.");
	}

	public static void eliminarCamion(String camionaEliminar, JPanel mainPanel) {

		archivador.eliminarCamionArchivotxt(camionaEliminar, "listaCamiones.txt", mainPanel);
	}

	public static void agregarCamion(String patente, boolean permisoCirculacion, boolean revisionTecnica, boolean estadoActual, int cargaMax) {
		Camion nuevoCamion = new Camion(patente, permisoCirculacion, revisionTecnica, estadoActual, cargaMax);


		// Agregar el nuevo camión a la lista
		nuevoCamion.agregarCamionLista();

		// Guardar el nuevo camión en el archivo de texto
		archivador.agregarCamionATexto("listaCamiones.txt", nuevoCamion);

		System.out.println("Camión agregado exitosamente.");
	}

	private static Camion buscarCamionPorPatente(List<Camion> camiones, String patente) {
		for (Camion camion : camiones) {
			if (camion.getPatente().equals(patente)) {
				return camion;
			}
		}
		return null;
	}

	private static Chofer buscarChoferPorRut(List<Chofer> choferes, String rut) {
		for (Chofer chofer : choferes) {
			if (chofer.getRut().equals(rut)) {
				return chofer;
			}
		}
		return null;
	}
}