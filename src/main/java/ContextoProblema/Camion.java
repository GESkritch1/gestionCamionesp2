package ContextoProblema;

import Archivador.Archivador;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Camion implements Serializable {

	public static List<Camion> listaCamiones = new ArrayList<>();
	private Chofer chofer;

	// Resto de métodos y atributos de la clase Camion

	public Chofer getChofer() {
		return chofer;
	}

	public void setChofer(Chofer chofer) {
		this.chofer = chofer;
	}
	Chofer conductor;
	private String patente;
	private boolean permisoCirculacion;
	private boolean revisionTecnica;
	private boolean estadoActual;
	private int cargaMax;

	public String getPatente() {
		return this.patente;
	}

	public boolean getPermisoCirculacion() {
		return this.permisoCirculacion;
	}

	public boolean getRevisionTecnica() {
		return this.revisionTecnica;
	}

	public boolean getEstadoActual() {
		return this.estadoActual;
	}

	public int getCargaMax() {
		return this.cargaMax;
	}

	public void agregarChoferACamion(Chofer chofer) {
		this.conductor=chofer;
	}

	public void agregarCamionLista() {
		listaCamiones.add(this);
	}
	public void asignarChofer(String patente, String nombreArchivo) {
		Chofer choferAsignado = null;

		try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
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
				Chofer chofer = new Chofer(rut, nombre, edad, licencia, estadoChofer);

				// Asignar el chofer al camión si la patente coincide
				if (patente.equals(this.patente)) {
					choferAsignado = chofer;
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (choferAsignado != null) {
			// Asignar el chofer al camión
			this.chofer = choferAsignado;
			System.out.println("Chofer asignado correctamente.");
		} else {
			System.out.println("No se encontró un chofer con la patente especificada.");
		}
	}

	public Camion(String Patente, boolean permisoCirculacion, boolean revisionTecnica, boolean estadoActual, int cargaMax) {
		this.patente = Patente;
		this.permisoCirculacion = permisoCirculacion;
		this.revisionTecnica = revisionTecnica;
		this.estadoActual = estadoActual;
		this.cargaMax = cargaMax;
	}
	public static Camion buscarCamionPorPatente(String patente) {
		for (Camion camion : listaCamiones) {
			if (camion.getPatente().equals(patente)) {
				return camion;  // Se encontró el camión con la patente especificada
			}
		}
		return null;  // No se encontró el camión con la patente especificada
	}

	public <conductor> void verChoferdeCamion() {
		if (conductor != null) {
			System.out.println("El chofer de este camion es: " + conductor.getNombre());
			System.out.println("Nombre: " + conductor.getNombre());
			System.out.println("Rut: " + conductor.getRut());
			System.out.println("Edad: " + conductor.getEdad());
			System.out.println("Licencia al día: " + conductor.getLicencia());
			System.out.println("Estado conductor (true=trabajando, false=no trabajando): " + conductor.getEstadoChofer());
		} else {
			System.out.println("No hay asignado un chofer para este camion.");
		}
	}

	public String obtenerDatosCamion() {
		return  "Datos Chofer: "+
				"\nNombre: " + this.conductor.getNombre() +
				"\nRut: " + this.conductor.getRut() +
				"\nEdad: " + this.conductor.getEdad() +
				"\nLicencia al día: " + this.conductor.getLicencia() +
				"\nEstado conductor (true=trabajando, false=no trabajando): " + this.conductor.getEstadoChofer() +
				"\nDatos Camion: " +
				"\nPatente: "+ this.getPatente() +
				"\nPermiso de Circulacion al dia: "+ this.getPermisoCirculacion()+
				"\nRevision tecnica al dia: "+ this.getRevisionTecnica()+
				"\nEstado actual del camion(true=funcionando, false=no funcionando): "+ this.getEstadoActual()+
				"\nCarga maxima del camion: "+ this.getCargaMax();

	}
}