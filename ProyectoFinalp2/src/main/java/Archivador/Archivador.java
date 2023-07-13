package Archivador;

import ContextoProblema.Camion;
import ContextoProblema.Chofer;
import ContextoProblema.Pedido;
import UsosyMas.Distancia;
import UsosyMas.Utilitarios;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Archivador {
	static List<Chofer> listaChoferes = convertirArchivoChoferes("listaChoferes.txt");
	static List<Camion> listaCamiones = convertirArchivoCamiones("listaCamiones.txt");
	List<Pedido> listaPedidos = convertirArchivoPedidos("listaPedidos.txt");
	List<Distancia> listaDistancias = convertirArchivoDistancias("listaDistancias.txt");


	public Archivador() {
	}

	public static List<Pedido> convertirArchivoPedidos(String archivo) {
		List<Pedido> listaPedidos = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] partes = linea.split("; ");

				String codigo = partes[0].substring(14);
				Pedido pedido = new Pedido(codigo);
				listaPedidos.add(pedido);
			}
		} catch (IOException e) {

			//
			//
			//
			//
			//
		}

		return listaPedidos;
	}

	public static List<Chofer> convertirArchivoChoferes(String archivo) {
		List<Chofer> listaChoferes = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea;

			while ((linea = br.readLine()) != null) {
				String[] partes = linea.split("; ");

				String rut = partes[0].substring(5);
				String nombre = partes[1].substring(8);
				String licencia = partes[2].substring(18);
				boolean estadoChofer = Boolean.parseBoolean(partes[3].substring(41));

				Chofer chofer = new Chofer(nombre, rut, licencia, estadoChofer);
				listaChoferes.add(chofer);
			}
		} catch (IOException e) {
			//
			//
			//
			//
		}

		return listaChoferes;
	}

	public static List<Camion> convertirArchivoCamiones(String archivo) {
		List<Camion> listaCamiones = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea;

			while ((linea = br.readLine()) != null) {
				String[] partes = linea.split("; ");

				String patente = partes[0].substring(9);
				boolean permisoCirculacion = Boolean.parseBoolean(partes[1].substring(26));
				boolean revisionTecnica = Boolean.parseBoolean(partes[2].substring(23));
				boolean estadoActual = Boolean.parseBoolean(partes[3].substring(47));
				String cargaSacada = partes[4].substring(22);
				String palabraEliminar = "n: ";
				String textoModificado = cargaSacada.replace(palabraEliminar, "");
				int cargaMax = Integer.parseInt(textoModificado);
				Camion camion = new Camion(patente, permisoCirculacion, revisionTecnica, estadoActual, cargaMax);
				listaCamiones.add(camion);
			}
		} catch (IOException e) {
			//
			//
			//
			//
		}

		return listaCamiones;
	}

	public static List<Distancia> convertirArchivoDistancias(String archivo) {
		List<Distancia> listaDistancias = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea;

			while ((linea = br.readLine()) != null) {
				String[] partes = linea.split("; ");

				String ciudad = partes[0].substring(partes[0].indexOf(": ") + 2);
				int distancia = Integer.parseInt(partes[1].substring(0, partes[1].indexOf(" km")));

				Distancia distanciasDesdeTemuco = new Distancia(ciudad, distancia);
				listaDistancias.add(distanciasDesdeTemuco);
			}
		} catch (IOException e) {
			//
			//
			//
			//
		}

		return listaDistancias;
	}

	public static void mostrarArchivo(String rutaArchivo, JPanel mainPanel) {
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
			JOptionPane.showMessageDialog(mainPanel,"El archivo no pudo ser encontrado: " + e.getMessage());
		}
	}

	public static void agregarChoferATexto(String nombreArchivo, Chofer chofer,JPanel mainPanel) {
		try (FileWriter fileWriter = new FileWriter(nombreArchivo, true);
			 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			 PrintWriter writer = new PrintWriter(bufferedWriter)) {

			writer.println("Rut: " + chofer.getRut() + "; Nombre: " + chofer.getNombre() + "; Licencia al día: " + chofer.getLicencia() + "; Estado conductor (true=trabajando, false=no trabajando): " + chofer.getEstadoChofer());

			JOptionPane.showMessageDialog(mainPanel,"Chofer agregado al archivo: " + nombreArchivo);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(mainPanel,"Error al agregar el chofer al archivo: " + e.getMessage());
		}
	}

	public static void agregarCamionATexto(String nombreArchivo, Camion camion, JPanel mainPanel) {
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
				JOptionPane.showMessageDialog(mainPanel, "El camión con patente " + patente + " ya existe en el archivo.");
			} else {
				try (FileWriter fileWriter = new FileWriter(nombreArchivo, true);
					 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
					 PrintWriter writer = new PrintWriter(bufferedWriter)) {
					Utilitarios utilitarios = new Utilitarios();
					writer.println("Patente: " + camion.getPatente() + "; Permiso de Circulación al día: " + camion.getPermisoCirculacion() + "; Revisión técnica al día: " + camion.getRevisionTecnica() + "; Estado actual del camión (true=funcionando, false=no funcionando): " + camion.getEstadoActual() + "; Carga máxima del camión: " + camion.getCargaMax());

					JOptionPane.showMessageDialog(mainPanel, "Camión agregado al archivo: " + nombreArchivo);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(mainPanel, "Error al agregar el camión al archivo: " + e.getMessage());
				}
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(mainPanel,"Error al leer el archivo: " + e.getMessage());
		}
	}

	public static void agregarPedidoATexto(String nombreArchivo, Pedido pedido, JPanel mainPanel) {
		try (FileReader fileReader = new FileReader(nombreArchivo);
			 BufferedReader bufferedReader = new BufferedReader(fileReader)) {

			String linea;
			String patenteCamion = pedido.getPatenteCamion();
			boolean existePatente = false;

			// Verificar si la patente ya existe en el archivo
			while ((linea = bufferedReader.readLine()) != null) {
				if (linea.contains("Camion: " + patenteCamion)) {
					existePatente = true;
					break;
				}
			}

			if (existePatente) {
				JOptionPane.showMessageDialog(mainPanel, "La patente del camión " + patenteCamion + " ya está registrada en el archivo de pedidos.");
			} else {
				try (FileWriter fileWriter = new FileWriter(nombreArchivo, true);
					 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
					 PrintWriter writer = new PrintWriter(bufferedWriter)) {
					writer.println("Código pedido: " + pedido.getCodigo() + "; Camion: " + pedido.getPatenteCamion() + "; Fecha inicial: " + pedido.getFechaInicial() + "; Fecha llegada: " + pedido.getFechaFinal() + "; Localidad de donde se envio: " + pedido.getLugarSalida() + "; Destino: " + pedido.getLugarDestino() + "; distancia: " + pedido.getDistancia() + "km" + "; Tiempo Estimado: " + pedido.getTiempo() + " horas aproximadas(se le suma 2 horas al tiempo estimado por problemas de algun taco en el camino u otro inconveniente)");

					JOptionPane.showMessageDialog(mainPanel, "Pedido agregado al archivo: " + nombreArchivo);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(mainPanel, "Error al agregar el Pedido al archivo: " + e.getMessage());
				}
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(mainPanel, "Error al leer el archivo: " + e.getMessage());
		}
	}

	public static void eliminarChoferArchivoTxt(String rut, String archivo, JPanel mainPanel) {
		// Leer el contenido del archivo
		List<String> lineas = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea;

			while ((linea = br.readLine()) != null) {
				lineas.add(linea);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(mainPanel,"Error al leer el archivo: " + e.getMessage());
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
			JOptionPane.showMessageDialog(mainPanel, "El chofer con RUT " + rut + " no se encuentra en el archivo.");
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
			JOptionPane.showMessageDialog(mainPanel, "Chofer eliminado del archivo: " + rut);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(mainPanel, "Error al guardar los cambios en el archivo: " + e.getMessage());
		}
	}

	public static void eliminarCamionArchivotxt(String patente, String archivo, JPanel mainPanel) {
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

	public static void eliminarPedidoArchivotxt(String codigo, String archivo, JPanel mainPanel) {
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


		boolean pedidoEncontrado = false;
		List<Integer> indicesEliminar = new ArrayList<>(); // Índices de las líneas a eliminar
		for (int i = 0; i < lineas.size(); i++) {
			String linea = lineas.get(i);
			if (linea.startsWith("Código pedido: ") && linea.contains(codigo)) {
				pedidoEncontrado = true;
				indicesEliminar.add(i); // Almacenar el índice de la línea a eliminar
			}
		}

		if (!pedidoEncontrado) {
			JOptionPane.showMessageDialog(mainPanel, "no existe ese dato");
			return;
		}

		// Eliminar las líneas correspondientes al pedido
		for (int i : indicesEliminar) {
			lineas.remove(i);
		}

		// Guardar las líneas actualizadas en el archivo
		try (PrintWriter writer = new PrintWriter(archivo)) {
			for (String linea : lineas) {
				writer.println(linea);
			}
			JOptionPane.showMessageDialog(mainPanel, "Ha sido eliminado del archivo: " + codigo);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(mainPanel, "Error al guardar los cambios en el archivo: " + e.getMessage());
		}
	}

	public static void agregarChoferaCamion(String archivo, String patente, String rut, JPanel mainPanel) {
		try (FileWriter fileWriter = new FileWriter(archivo, true);
			 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			 PrintWriter writer = new PrintWriter(bufferedWriter)) {

			writer.println("Camión: " + patente + "; Chofer: " + rut);

			JOptionPane.showMessageDialog(mainPanel,"Chofer y camión agregados al archivo: " + archivo);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(mainPanel,"Error al agregar el chofer y el camión al archivo: " + e.getMessage());
		}
	}

	public static List<Camion> getListaCamiones() {
		return listaCamiones;
	}

	public static List<Camion> cargarCamionesDesdeArchivo(String archivo) {
		listaCamiones = convertirArchivoCamiones(archivo);
		return listaCamiones;
	}

	public static List<Chofer> cargarChoferesDesdeArchivo(String archivo) {
		listaChoferes = convertirArchivoChoferes(archivo);
		return listaChoferes;
	}

	public List<Pedido> cargarPedidoDesdeArchivo(String archivo) {
		listaPedidos = convertirArchivoPedidos(archivo);
		return listaPedidos;
	}

	public List<Distancia> cargarDistanciaDesdeArchivo(String archivo) {
		listaDistancias = convertirArchivoDistancias(archivo);
		return listaDistancias;
	}
}