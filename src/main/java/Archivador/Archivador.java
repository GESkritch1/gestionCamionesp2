package Archivador;

import ContextoProblema.Camion;
import ContextoProblema.Chofer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
	public void abrirArchivo(String archivo) {
		try {
			FileReader fileReader = new FileReader(archivo);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String linea;
			while ((linea = bufferedReader.readLine()) != null) {
				System.out.println(linea);
			}

			bufferedReader.close();
		} catch (IOException e) {
			System.out.println("Error al abrir el archivo: " + e.getMessage());
		}
	}
	public void guardarObjetoEnArchivo(Object objeto, String nombreArchivo) {
		try (FileOutputStream fileOutputStream = new FileOutputStream(nombreArchivo);
			 ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

			objectOutputStream.writeObject(objeto);
			System.out.println("Objeto guardado en el archivo: " + nombreArchivo);
		} catch (IOException e) {
			System.out.println("Error al guardar el objeto en el archivo: " + e.getMessage());
		}
	}
	public Object leerObjetoDesdeArchivo(String nombreArchivo) {
		try (FileInputStream fileInputStream = new FileInputStream(nombreArchivo);
			 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

			Object objeto = objectInputStream.readObject();
			System.out.println("Objeto leído desde el archivo: " + nombreArchivo);
			return objeto;
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error al leer el objeto desde el archivo: " + e.getMessage());
		}
		return null;
	}
	public void guardarTextoEnArchivo(String texto, String nombreArchivo) {
		try (PrintWriter writer = new PrintWriter(nombreArchivo)) {
			writer.println(texto);
			System.out.println("Texto guardado en el archivo: " + nombreArchivo);
		} catch (FileNotFoundException e) {
			System.out.println("Error al guardar el texto en el archivo: " + e.getMessage());
		}
	}
	public void guardarTextoEnArchivosoloChoferes(String nombreArchivo){
		try (PrintWriter writer = new PrintWriter(nombreArchivo)) {
			for (Chofer chofer : Chofer.listaChoferes) {
				writer.println("Nombre: " + chofer.getNombre());
				writer.println("Rut: " + chofer.getRut());
				writer.println("Edad: " + chofer.getEdad());
				writer.println("Licencia al día: " + chofer.getLicencia());
				writer.println("Estado conductor (true=trabajando, false=no trabajando): " + chofer.getEstadoChofer());
				writer.println();
			}
			System.out.println("Datos de choferes guardados en el archivo: " + nombreArchivo);
		} catch (FileNotFoundException e) {
			System.out.println("Error al guardar los datos de choferes en el archivo: " + e.getMessage());
		}

	}
	public void agregarChoferATexto(String nombreArchivo, Chofer chofer) {
		try (FileWriter fileWriter = new FileWriter(nombreArchivo, true);
			 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			 PrintWriter writer = new PrintWriter(bufferedWriter)) {

			writer.println("Nombre: " + chofer.getNombre());
			writer.println("Rut: " + chofer.getRut());
			writer.println("Edad: " + chofer.getEdad());
			writer.println("Licencia al día: " + chofer.getLicencia());
			writer.println("Estado conductor (true=trabajando, false=no trabajando): " + chofer.getEstadoChofer());
			writer.println();

			System.out.println("Chofer agregado al archivo: " + nombreArchivo);
		} catch (IOException e) {
			System.out.println("Error al agregar el chofer al archivo: " + e.getMessage());
		}
	}
	public void agregarCamionATexto(String nombreArchivo, Camion camion) {
		try (FileWriter fileWriter = new FileWriter(nombreArchivo, true);
			 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			 PrintWriter writer = new PrintWriter(bufferedWriter)) {

			writer.println("Patente: " + camion.getPatente());
			writer.println("Permiso de Circulación al día: " + camion.getPermisoCirculacion());
			writer.println("Revisión técnica al día: " + camion.getRevisionTecnica());
			writer.println("Estado actual del camión (true=funcionando, false=no funcionando): " + camion.getEstadoActual());
			writer.println("Carga máxima del camión: " + camion.getCargaMax());
			writer.println();

			System.out.println("Camión agregado al archivo: " + nombreArchivo);
		} catch (IOException e) {
			System.out.println("Error al agregar el camión al archivo: " + e.getMessage());
		}
	}
	public void eliminarCamionArchivo(Camion camion) {
		try (FileOutputStream fileOutputStream = new FileOutputStream("camiones.txt");
			 ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

			camiones.remove(camion);

			for (Camion c : camiones) {
				objectOutputStream.writeObject(c);
			}

			System.out.println("Camión eliminado del archivo: " + camion.getPatente());
		} catch (IOException e) {
			System.out.println("Error al eliminar el camión del archivo: " + e.getMessage());
		}
	}
	public void eliminarCamionArchivotxt(String patente, String archivo) {
		// Leer el contenido del archivo listaCamiones.txt
		List<Camion> camiones = new ArrayList<>();

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
			camiones = (List<Camion>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error al leer el archivo listaCamiones.txt: " + e.getMessage());
			return;
		}

		// Buscar el camión en la lista y eliminarlo
		boolean camionEncontrado = false;
		for (Camion camion : camiones) {
			if (camion.getPatente().equals(patente)) {
				camiones.remove(camion);
				camionEncontrado = true;
				break;
			}
		}

		if (!camionEncontrado) {
			System.out.println("El camión con patente " + patente + " no se encuentra en el archivo listaCamiones.txt");
			return;
		}

		// Guardar la lista actualizada de camiones en el archivo listaCamiones.txt
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
			oos.writeObject(camiones);
			System.out.println("Camión eliminado del archivo listaCamiones.txt");
		} catch (IOException e) {
			System.out.println("Error al escribir en el archivo listaCamiones.txt: " + e.getMessage());
		}
	}
	public static List<Chofer> leerListaChoferes() {
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




}