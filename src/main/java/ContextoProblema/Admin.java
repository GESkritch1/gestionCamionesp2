package ContextoProblema;

import Archivador.Archivador;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static Archivador.Archivador.*;

public class Admin {
	Archivador archivador = new Archivador();
Scanner sc = new Scanner(System.in);
	public void opcionesPedido() {

	}
	public void opcionesCamiones() {
		System.out.println("(1) agregar un camion a la lista de camiones");
		System.out.println("(2) eliminar Camion ");
		System.out.println("(3) agregar Chofer a algun camion (en mantenimiento)");
		System.out.println("(4) mostrar Camiones de la lista");
		int i = Utilitarios.leerEntero();

		switch (i){
			case 1: agregarCamion();
			break;
			case 2: eliminarCamion();//coming soon
				break;
			case 3:
				System.out.println("esta en mantenimiento");
				break;
			case 4: mostrarCamiones();
			break;
			default:
				System.out.println("no existe esa opcion");
				break;


		}
	}

	public void mostrarCamiones() {
		archivador.mostrarArchivo("listaCamiones.txt");
	}

	public void opcionesChoferes() {
		System.out.println("(1) agregar Chofer a la lista");
		System.out.println("(2) mostrar Choferes de la lista");
		System.out.println("(3) eliminar Chofer de la lista");
		int i = Utilitarios.leerEntero();
		switch (i){
			case 1: agregarChofer();
			break;
			case 2: mostrarChoferes();
			break;
			case 3: eliminarChofer();
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

	public void mostrarChoferes() {
		archivador.mostrarArchivo("listaChoferes.txt");
	}

	public void agregarChofer() {Scanner scanner = new Scanner(System.in);

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

	private void eliminarCamion() {
		System.out.println("ingrese la patente del camion que desea eliminar: ");
		String camionaEliminar = sc.nextLine();
		archivador.eliminarCamionArchivotxt(camionaEliminar, "listaCamiones.txt" );
	}

	public void agregarCamion() {
		System.out.println("Ingrese los datos del camión:");

		System.out.print("Patente: ");
		String patente = sc.nextLine();

		boolean permisoCirculacion = Utilitarios.solicitarBooleano("Permiso de Circulación al día (true/false): ");
		boolean revisionTecnica = Utilitarios.solicitarBooleano("Revisión técnica al día (true/false): ");
		boolean estadoActual = Utilitarios.solicitarBooleano("Estado actual del camión (true=funcionando, false=no funcionando): ");

		System.out.print("Carga máxima del camión: ");
		int cargaMax = Utilitarios.leerEntero();
		sc.nextLine(); // Consumir el salto de línea pendiente después de nextInt()

		Camion nuevoCamion = new Camion(patente, permisoCirculacion, revisionTecnica, estadoActual, cargaMax);


		// Agregar el nuevo camión a la lista
		nuevoCamion.agregarCamionLista();

		// Guardar el nuevo camión en el archivo de texto
		archivador.agregarCamionATexto("listaCamiones.txt", nuevoCamion);

		System.out.println("Camión agregado exitosamente.");
	}


	private void agregarChoferaCamion(){

		List<Chofer> choferes = archivador.leerListaChoferes();
		archivador.asignarChoferaCamion(choferes);
	}


}