package ContextoProblema;

import Archivador.Archivador;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;

import static ContextoProblema.Camion.listaCamiones;
import static ContextoProblema.Chofer.listaChoferes;

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

	public static void agregarCamion(String patente, boolean permisoCirculacion, boolean revisionTecnica, boolean estadoActual, int cargaMax, JPanel mainPanel) {
		Camion nuevoCamion = new Camion(patente, permisoCirculacion, revisionTecnica, estadoActual, cargaMax);

		// Agregar el nuevo camión a la lista
		nuevoCamion.agregarCamionLista();

		// Guardar el nuevo camión en el archivo de texto
		archivador.agregarCamionATexto("listaCamiones.txt", nuevoCamion, mainPanel);
	}

	public static void agregarChoferaCamionAD(String patente, String rut, JPanel mainPanel) {
		// Buscar el chofer en la lista de choferes
		listaCamiones=archivador.cargarCamionesDesdeArchivo("listaCamiones.txt");
		listaChoferes=archivador.cargarChoferesDesdeArchivo("listaChoferes.txt");
		Chofer chofer = null;
		for (Chofer c : listaChoferes) {
			if (c.getRut().equals(rut)) {
				chofer = c;
				break;
			}
		}

		// Buscar el camión en la lista de camiones
		Camion camion = null;
		for (Camion c : listaCamiones) {
			if (c.getPatente().equals(patente)) {
				camion = c;
				break;
			}
		}

		if (chofer != null && camion != null) {
			archivador.agregarChoferaCamion("listaCamionConChofer.txt", patente, rut, mainPanel);
			JOptionPane.showMessageDialog(mainPanel,"Chofer y camión agregados al archivo listaCamionConChofer.txt");
		} else {
			JOptionPane.showMessageDialog(mainPanel,"No se pudo encontrar el chofer o el camión.");
		}
	}

	public void agregarPedido(String codigo, String patenteCamion, int fechaInicial, int fechaFinal, int distancia, String lugarDestino, int tiempo, JPanel mainPanel) {
		// Cargar los camiones desde el archivo

		// Buscar el camión por su patente
		Camion camion = buscarCamionPorPatente(patenteCamion);
		if (camion != null) {
			Pedido pedido = new Pedido(codigo, patenteCamion, fechaInicial, fechaFinal, lugarDestino, distancia, tiempo);
			archivador.agregarPedidoATexto("listaPedidos.txt", pedido);
		} else {
			System.out.println("El camión con patente " + patenteCamion + " no existe.");
		}
	}

	// Método para buscar un camión por su patente
	private Camion buscarCamionPorPatente(String patente) {
		listaCamiones=archivador.getListaCamiones();
		for (Camion camion : listaCamiones) {
			if (camion.getPatente().equals(patente)) {
				return camion;
			}
		}
		return null;
	}

	public String validadorDeCodigoPedido() {
		List<Pedido> codigos = archivador.cargarPedidoDesdeArchivo("listaPedido.txt");
			String numero = String.valueOf((int) (Math.random()*1000000+100));
			boolean valid = false;
			do {
				for (int i = 0; i < codigos.size(); i++) {
					if (codigos.get(i).equals(numero) ) {
						numero = String.valueOf((int) (Math.random() * 1000000 + 100));
					}
					if (!codigos.get(i).equals(numero) ) {
						valid = true;
					}
				}
			}while (valid=false);
			return numero;
	}
}
