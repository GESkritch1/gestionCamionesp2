package Archivador;

import ContextoProblema.Camion;
import ContextoProblema.Chofer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Archivador {
	private List<Camion> camiones;

	public Archivador() {
		camiones = new ArrayList<>();
		archivos = new ArrayList<>();
	}

	private List<String> archivos;


	public void mostrarArchivo(String rutaArchivo) {
		try {
			// Crea un objeto File con la ruta del archivo
			File archivo = new File(rutaArchivo);
			// Crea un objeto Scanner para leer el archivo
			Scanner scanner = new Scanner(archivo);
			// Lee el contenido del archivo línea por línea
			while (scanner.hasNextLine()) {
				String linea = scanner.nextLine();
				System.out.println(linea);
			}

			// Cierra el objeto Scanner
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("El archivo no pudo ser encontrado: " + e.getMessage());
		}
	}
	public void agregarChoferATexto(String nombreArchivo, Chofer chofer) {
		try (FileWriter fileWriter = new FileWriter(nombreArchivo, true);
			 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			 PrintWriter writer = new PrintWriter(bufferedWriter)) {

			writer.println("Nombre: " + chofer.getNombre()+"; Rut: " + chofer.getRut()+"; Licencia al día: " + chofer.getLicencia()+"; Estado conductor (true=trabajando, false=no trabajando): " + chofer.getEstadoChofer());

			System.out.println("Chofer agregado al archivo: " + nombreArchivo);
		} catch (IOException e) {
			System.out.println("Error al agregar el chofer al archivo: " + e.getMessage());
		}
	}
	public void agregarCamionATexto(String nombreArchivo, Camion camion) {
		try (FileWriter fileWriter = new FileWriter(nombreArchivo, true);
			 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			 PrintWriter writer = new PrintWriter(bufferedWriter)) {

			writer.println("Patente: " + camion.getPatente()+"; Permiso de Circulación al día: " + camion.getPermisoCirculacion()+"; Revisión técnica al día: " + camion.getRevisionTecnica()+ "; Estado actual del camión (true=funcionando, false=no funcionando): " + camion.getEstadoActual()+"; Carga máxima del camión: " + camion.getCargaMax());

			System.out.println("Camión agregado al archivo: " + nombreArchivo);
		} catch (IOException e) {
			System.out.println("Error al agregar el camión al archivo: " + e.getMessage());
		}
	}
	public void eliminarCamionArchivotxt(String patente, String archivo) {
		// Leer el contenido del archivo
		List<String> lineas = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea;

			while ((linea = br.readLine()) != null) {
				lineas.add(linea);
			}
		} catch (IOException e) {
			System.out.println("Error al leer el archivo: " + e.getMessage());
			return;
		}

		// Buscar el camión en las líneas y eliminarlo
		boolean camionEncontrado = false;
		List<Integer> indicesEliminar = new ArrayList<>(); // Índices de las líneas a eliminar
		for (int i = 0; i < lineas.size(); i++) {
			String linea = lineas.get(i);
			if (linea.startsWith("Patente: ") && linea.contains(patente)) {
				camionEncontrado = true;
				indicesEliminar.add(i); // Almacenar el índice de la línea a eliminar
			}
		}

		if (!camionEncontrado) {
			System.out.println("El camión con patente " + patente + " no se encuentra en el archivo.");
			return;
		}

		// Eliminar las líneas correspondientes al camión
		for (int i : indicesEliminar) {
			lineas.remove(i);
		}

		// Guardar las líneas actualizadas en el archivo
		try (PrintWriter writer = new PrintWriter(archivo)) {
			for (String linea : lineas) {
				writer.println(linea);
			}
			System.out.println("Camión eliminado del archivo: " + patente);
		} catch (FileNotFoundException e) {
			System.out.println("Error al guardar los cambios en el archivo: " + e.getMessage());
		}
	}

	public void eliminarChoferArchivoTxt(String rut, String archivo) {
		// Leer el contenido del archivo
		List<String> lineas = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea;

			while ((linea = br.readLine()) != null) {
				lineas.add(linea);
			}
		} catch (IOException e) {
			System.out.println("Error al leer el archivo: " + e.getMessage());
			return;
		}

		// Buscar el chofer en las líneas y eliminarlo
		boolean choferEncontrado = false;
		List<Integer> indicesEliminar = new ArrayList<>(); // Índices de las líneas a eliminar

		for (int i = 0; i < lineas.size(); i++) {
			String linea = lineas.get(i);

			// Buscar el RUT del chofer sin puntos ni guión
			if (linea.contains(rut)) {
				choferEncontrado = true;
				indicesEliminar.add(i); // Almacenar el índice de la línea a eliminar
			}
		}

		if (!choferEncontrado) {
			System.out.println("El chofer con RUT " + rut + " no se encuentra en el archivo.");
			return;
		}

		// Eliminar las líneas correspondientes al chofer
		for (int i : indicesEliminar) {
			lineas.remove(i);
		}

		// Guardar las líneas actualizadas en el archivo
		try (PrintWriter writer = new PrintWriter(archivo)) {
			for (String linea : lineas) {
				writer.println(linea);
			}
			System.out.println("Chofer eliminado del archivo: " + rut);
		} catch (FileNotFoundException e) {
			System.out.println("Error al guardar los cambios en el archivo: " + e.getMessage());
		}
	}


	public List<Chofer> leerListaChoferes() {
		List<Chofer> choferes = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader("listaChoferes.txt"))) {
			String linea;

			while ((linea = br.readLine()) != null) {
				// Separar la línea en los datos correspondientes
				String[] datosChofer = linea.split(",");
				String rut = datosChofer[0];
				String nombre = datosChofer[1];
				int edad = Integer.parseInt(datosChofer[2]);
				boolean licencia = Boolean.parseBoolean(datosChofer[3]);
				boolean estadoChofer = Boolean.parseBoolean(datosChofer[4]);

				// Crear una instancia de Chofer con los datos leídos
				Chofer chofer = new Chofer(nombre, rut, edad, licencia, estadoChofer);

				// Agregar el chofer a la lista
				choferes.add(chofer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return choferes;
	}
	public List<Camion> asignarChoferaCamion(List<Chofer> choferes) {
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
	public void guardarListaCamionConChofer(List<Camion> camiones) {
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