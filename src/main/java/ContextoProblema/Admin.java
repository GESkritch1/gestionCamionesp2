package ContextoProblema;

import Archivador.Archivador;

import java.io.*;
import java.util.ArrayList;
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
		System.out.println("(2) eliminar Camion (funcion en mantenimiento)");
		System.out.println("(3) agregar Chofer a algun camion   (funcion en mantenimiento)");
		System.out.println("(4) mostrar Camiones de la lista");
		int i = Utilitarios.leerEntero();

		switch (i){
			case 1: agregarCamion();
			break;
			case 2:
				System.out.println("funcion en mantenimiento");//coming soon
				break;
			case 3:
				System.out.println("funcion en mantenimiento");;//coming soon
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
		int i = Utilitarios.leerEntero();
		switch (i){
			case 1: agregarChofer();
			break;
			case 2: mostrarChoferes();
			default:
				System.out.println("no hay mas funciones por ahora");
		}

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
		int edad = scanner.nextInt();

		System.out.print("Licencia(true=tiene al dia, false=no la tiene al dia): ");
		boolean licencia = scanner.nextBoolean();
		scanner.nextLine(); // Consumir el salto de línea pendiente después de nextBoolean()

		System.out.print("Estado Chofer(true=trabajando, False=no puede trabajar): ");
		boolean estado = scanner.nextBoolean();
		scanner.nextLine(); // Consumir el salto de línea pendiente después de nextInt()

		Chofer nuevoChofer = new Chofer(nombre, rut, edad, licencia, estado);

		// Agregar el nuevo camión a la lista
		nuevoChofer.agregarChoferLista();

		// Guardar el nuevo camión en el archivo de texto
		archivador.agregarChoferATexto("listaChoferes.txt", nuevoChofer);

		System.out.println("Chofer agregado exitosamente.");
	}

	private void eliminarCamion() {
		//coming soon
	}

	public void agregarCamion() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Ingrese los datos del camión:");

		System.out.print("Patente: ");
		String patente = scanner.nextLine();

		System.out.print("Permiso de Circulación al día (true/false): ");
		boolean permisoCirculacion = scanner.nextBoolean();
		scanner.nextLine(); // Consumir el salto de línea pendiente después de nextBoolean()

		System.out.print("Revisión técnica al día (true/false): ");
		boolean revisionTecnica = scanner.nextBoolean();
		scanner.nextLine(); // Consumir el salto de línea pendiente después de nextBoolean()

		System.out.print("Estado actual del camión (true=funcionando, false=no funcionando): ");
		boolean estadoActual = scanner.nextBoolean();
		scanner.nextLine(); // Consumir el salto de línea pendiente después de nextBoolean()

		System.out.print("Carga máxima del camión: ");
		int cargaMax = scanner.nextInt();
		scanner.nextLine(); // Consumir el salto de línea pendiente después de nextInt()

		Camion nuevoCamion = new Camion(patente, permisoCirculacion, revisionTecnica, estadoActual, cargaMax);

		// Agregar el nuevo camión a la lista
		nuevoCamion.agregarCamionLista();

		// Guardar el nuevo camión en el archivo de texto
		archivador.agregarCamionATexto("listaCamiones.txt", nuevoCamion);

		System.out.println("Camión agregado exitosamente.");

	}


	private void agregarChoferaCamion(){

		List<Chofer> choferes = leerListaChoferes();
		List<Camion> camiones = leerListaCamiones(choferes);

		// Llamar a los métodos para guardar la lista de camiones con chofer en un archivo
		guardarListaCamionConChofer(camiones);
	}

	public static List<Camion> leerListaCamiones(List<Chofer> choferes) {
		List<Camion> camiones = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader("listaCamiones.txt"))) {
			String linea;

			while ((linea = br.readLine()) != null) {
				// Separar la línea en los datos correspondientes
				String[] datosCamion = linea.split(",");
				String patente = datosCamion[0];
				boolean permisoCirculacion = Boolean.parseBoolean(datosCamion[1]);
				boolean revisionTecnica = Boolean.parseBoolean(datosCamion[2]);
				boolean estadoActual = Boolean.parseBoolean(datosCamion[3]);
				int cargaMax = Integer.parseInt(datosCamion[4]);

				// Crear una instancia de Camion con los datos leídos
				Camion camion = new Camion(patente, permisoCirculacion, revisionTecnica, estadoActual, cargaMax);

				// Asignar un chofer al camión si hay choferes disponibles
				if (!choferes.isEmpty()) {
					Chofer chofer = choferes.remove(0); // Obtener el primer chofer de la lista
					camion.agregarChoferACamion(chofer);
				}

				// Agregar el camión a la lista
				camiones.add(camion);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return camiones;
	}
	public static void guardarListaCamionConChofer(List<Camion> camiones) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("listaCamionConChofer.txt"))) {
			for (Camion camion : camiones) {
				bw.write(camion.obtenerDatosCamion()); // Escribe la representación del camión con chofer en el archivo
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}