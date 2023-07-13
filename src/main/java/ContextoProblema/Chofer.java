package ContextoProblema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Chofer implements Serializable {
	public static List<Chofer> listaChoferes = new ArrayList<>();

	private String nombre;
	private String rut;
	private int edad;
	private String licencia;
	private boolean estadoChofer;

	/***
	 * Este es un constructor de la clase "Chofer"
	 * @param nombre
	 * @param rut
	 * @param edad
	 * @param licencia
	 * @param estadoChofer
	 */
	public Chofer(String nombre, String rut, int edad, String licencia, boolean estadoChofer) {
		this.nombre = nombre;
		this.rut = rut;
		this.edad = edad;
		this.licencia = licencia;
		this.estadoChofer = estadoChofer;
	}

	/***
	 * Este es otro constructor de la clase "Chofer"
	 * @param nombre
	 * @param rut
	 * @param licencia
	 * @param estadoChofer
	 */
	public Chofer(String nombre, String rut, String licencia, boolean estadoChofer) {this.nombre = nombre;
		this.nombre = nombre;
		this.rut = rut;
		this.licencia = licencia;
		this.estadoChofer = estadoChofer;
	}

	/***
	 * Este método devuelve el nombre del chofer
	 * @return
	 */
	public String getNombre() {
		return this.nombre;
	}

	/***
	 *  Este método devuelve el rut del chofer
	 * @return
	 */
	public String getRut() {
		return this.rut;
	}

	/***
	 * Este método devuelve la edad del chofer.
	 * @return
	 */
	public int getEdad() {
		return this.edad;
	}

	/***
	 * Este método devuelve el tipo de licencia del chofer
	 * @return
	 */
	public String getLicencia() {
		return this.licencia;
	}

	/***
	 * Este método devuelve el estado del chofer, representado por un valor booleano
	 * @return
	 */
	public boolean getEstadoChofer() {
		return this.estadoChofer;
	}

	/***
	 * Este método agrega el objeto actual de la clase Chofer a la lista estática listaChoferes. La lista listaChoferes es una lista de objetos Chofer y se inicializa como una lista vacía al comienzo.
	 */
	public void agregarChoferLista() {
		listaChoferes.add(this);
	}


}