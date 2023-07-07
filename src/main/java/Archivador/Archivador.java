package Archivador;

import ContextoProblema.Camion;
import ContextoProblema.Chofer;
import ContextoProblema.Pedido;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Archivador {
	private List<Camion> camiones;

	public Archivador() {
		camiones = new ArrayList<>();
		archivos = new ArrayList<>();
	}

	private List<String> archivos;


	public void mostrarArchivo(String rutaArchivo, JPanel mainPanel) {
		try {
			// Crea un objeto File con la ruta del archivo
			File archivo = new File(rutaArchivo);
			// Crea un objeto StringBuilder para almacenar el contenido del archivo
			StringBuilder contenidoArchivo = new StringBuilder();

			// Crea un objeto Scanner para leer el archivo
			Scanner scanner = new Scanner(archivo);
			// Lee el contenido del archivo línea por línea y lo agrega al StringBuilder
			while (scanner.hasNextLine()) {
				String linea = scanner.nextLine();
				contenidoArchivo.append(linea).append("\n");
			}
			// Cierra el objeto Scanner
			scanner.close();

			// Muestra el contenido del archivo en una sola ventana
			JOptionPane.showMessageDialog(mainPanel, contenidoArchivo.toString());

		} catch (FileNotFoundException e) {
			System.out.println("El archivo no pudo ser encontrado: " + e.getMessage());
		}
	}

	public void agregarChoferATexto(String nombreArchivo, Chofer chofer) {
		try (FileWriter fileWriter = new FileWriter(nombreArchivo, true);
			 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			 PrintWriter writer = new PrintWriter(bufferedWriter)) {

			writer.println("Rut: " + chofer.getRut()+"; Nombre: " + chofer.getNombre()+"; Licencia al día: " + chofer.getLicencia()+"; Estado conductor (true=trabajando, false=no trabajando): " + chofer.getEstadoChofer());

			System.out.println("Chofer agregado al archivo: " + nombreArchivo);
		} catch (IOException e) {
			System.out.println("Error al agregar el chofer al archivo: " + e.getMessage());
		}
	}

	public void agregarChoferaCamion(String archivo, String patente, String rut, JPanel mainPanel) {
		List<String> lineas = new ArrayList<>();
		List<Integer> indicesEliminar = new ArrayList<>(); // Índices de las líneas a eliminar
		try (BufferedReader br = new BufferedReader(new FileReader("listaCamiones.txt"))) {
			String linea;

			while ((linea = br.readLine()) != null) {
				lineas.add(linea);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(mainPanel, "Error al leer el archivo: " + e.getMessage());
			return;
		}
		// Buscar el camión en las líneas y eliminarlo
		boolean camionEncontrado = false;
		for (int i = 0; i < lineas.size(); i++) {
			String linea = lineas.get(i);
			if (linea.startsWith("Patente: ") && linea.contains(patente)) {
				camionEncontrado = true;
				indicesEliminar.add(i);
			}
		}
		if (!camionEncontrado) {
			JOptionPane.showMessageDialog(mainPanel, "no existe ese Camion");
			return;
		}
		for (int i = lineas.size() - 1; i >= 0; i--) {
			if (!indicesEliminar.contains(i)) {
				lineas.remove(i);
			}
		}
		try (PrintWriter writer = new PrintWriter(archivo)) {
			for (String linea : lineas) {
				writer.println(linea);
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(mainPanel, "Error al guardar los cambios en el archivo: " + e.getMessage());
		}
		List<String> lineas2 = new ArrayList<>();
		List<Integer> indicesEliminar2 = new ArrayList<>(); // Índices de las líneas a eliminar
		try (BufferedReader br = new BufferedReader(new FileReader("listaChoferes.txt"))) {
			String linea;

			while ((linea = br.readLine()) != null) {
				lineas2.add(linea);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(mainPanel, "Error al leer el archivo: " + e.getMessage());
			return;
		}
		// Buscar el chofer en las líneas y eliminarlo
		boolean choferEncontrado = false;
		for (int i = 0; i < lineas2.size(); i++) {
			String linea = lineas2.get(i);
			if (linea.startsWith("Rut: ") && linea.contains(rut)) {
				choferEncontrado = true;
				indicesEliminar2.add(i);
			}
		}
		if (!choferEncontrado) {
			JOptionPane.showMessageDialog(mainPanel, "No existe ese Chofer");
			return;
		}
		for (int i = lineas2.size() - 1; i >= 0; i--) {
			if (!indicesEliminar2.contains(i)) {
				lineas2.remove(i);
			}
		}
		try (PrintWriter writer = new PrintWriter(archivo)) {
			for (String linea : lineas2) {

				writer.println(linea);
			}

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(mainPanel, "Error al guardar los cambios en el archivo: " + e.getMessage());
		}

	}


	public void agregarCamionATexto(String nombreArchivo, Camion camion, JPanel mainPanel) {
		try (FileReader fileReader = new FileReader(nombreArchivo);
			 BufferedReader bufferedReader = new BufferedReader(fileReader)) {

			String linea;
			String patente = camion.getPatente();
			boolean existePatente = false;

			// Verificar si la patente ya existe en el archivo
			while ((linea = bufferedReader.readLine()) != null) {
				if (linea.contains("Patente: " + patente)) {
					existePatente = true;
					break;
				}
			}

			if (existePatente) {
				JOptionPane.showMessageDialog(mainPanel,"El camión con patente " + patente + " ya existe en el archivo.");
			} else {
				try (FileWriter fileWriter = new FileWriter(nombreArchivo, true);
					 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
					 PrintWriter writer = new PrintWriter(bufferedWriter)) {

					writer.println("Patente: " + camion.getPatente() + "; Permiso de Circulación al día: " + camion.getPermisoCirculacion() + "; Revisión técnica al día: " + camion.getRevisionTecnica() + "; Estado actual del camión (true=funcionando, false=no funcionando): " + camion.getEstadoActual() + "; Carga máxima del camión: " + camion.getCargaMax());

					JOptionPane.showMessageDialog(mainPanel,"Camión agregado al archivo: " + nombreArchivo);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(mainPanel,"Error al agregar el camión al archivo: " + e.getMessage());
				}
			}
		} catch (IOException e) {
			System.out.println("Error al leer el archivo: " + e.getMessage());
		}
	}

	public void agregarPedidoATexto(String nombreArchivo, Pedido pedido, JPanel mainPanel) {
		try (FileWriter fileWriter = new FileWriter(nombreArchivo, true);
			 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			 PrintWriter writer = new PrintWriter(bufferedWriter)) {

			writer.println("Código pedido:"+ pedido.getCodigo()+";Camion:"+";Fecha inicial:"+pedido.getFechaInicial() +";Fecha llegada:"+ pedido.getFechaFinal()+";Localidad de donde se envio:"+pedido.getLugarSalida()+";Destino:"+pedido.getLugarDestino()+";Tiempo Extimado:"+pedido.getTiempo());

			JOptionPane.showMessageDialog(mainPanel,"Pedido agregado al archivo: " + nombreArchivo);
		} catch (IOException e) {
			System.out.println("Error al agregar el Pedido al archivo: " + e.getMessage());
		}
	}
	public void eliminarChoferArchivoTxt(String rut, String archivo, JPanel mainPanel) {
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
			JOptionPane.showMessageDialog(mainPanel,"El chofer con RUT " + rut + " no se encuentra en el archivo.");
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
			JOptionPane.showMessageDialog(mainPanel,"Chofer eliminado del archivo: " + rut);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(mainPanel,"Error al guardar los cambios en el archivo: " + e.getMessage());
		}
	}

	public void eliminarCamionArchivotxt(String patente, String archivo, JPanel mainPanel) {
		// Leer el contenido del archivo
		List<String> lineas = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea;

			while ((linea = br.readLine()) != null) {
				lineas.add(linea);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(mainPanel, "Error al leer el archivo: " + e.getMessage());
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
			JOptionPane.showMessageDialog(mainPanel, "no existe ese dato");
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
			JOptionPane.showMessageDialog(mainPanel, "Ha sido eliminado del archivo: " + patente);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(mainPanel, "Error al guardar los cambios en el archivo: " + e.getMessage());
		}
	}




}