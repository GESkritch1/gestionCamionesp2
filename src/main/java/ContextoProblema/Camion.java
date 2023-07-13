package ContextoProblema;
import java.util.ArrayList;
import java.util.List;

public class Camion {

	public static List<Camion> listaCamiones = new ArrayList<>();
	private String patente;
	private boolean permisoCirculacion;
	private boolean revisionTecnica;
	private boolean estadoActual;
	private int cargaMax;

	/***
	 *  Este método devuelve la patente del camión.
	 * @return patente
	 */
	public String getPatente() {
		return this.patente;
	}

	/***
	 *  Este método devuelve el estado del permiso de circulación del camión.
	 * @return permisoCirculacion
	 */
	public boolean getPermisoCirculacion() {
		return this.permisoCirculacion;
	}

	/***
	 * Este método devuelve el estado de la revisión técnica del camión.
	 * @return revisionTecnica
	 */
	public boolean getRevisionTecnica() {
		return this.revisionTecnica;
	}

	/***
	 *  Este método devuelve el estado actual del camión.
	 * @return estadoActual
	 */
	public boolean getEstadoActual() {
		return this.estadoActual;
	}

	/***
	 * Este método devuelve la carga máxima del camión.
	 * @return cargaMax
	 */
	public int getCargaMax() {
		return this.cargaMax;
	}

	/***
	 * Este método agrega el camión actual a la lista listaCamiones. Permite almacenar la instancia del camión en la lista compartida por todos los camiones.
	 */
	public void agregarCamionLista() {
		listaCamiones.add(this);
	}

	/***
	 *  Este es el constructor de la clase Camion. Se utiliza para crear una nueva instancia de Camion y asignar valores a los atributos patente, permisoCirculacion, revisionTecnica, estadoActual y cargaMax del camión.
	 * @param Patente
	 * @param permisoCirculacion
	 * @param revisionTecnica
	 * @param estadoActual
	 * @param cargaMax
	 */
	public Camion(String Patente, boolean permisoCirculacion, boolean revisionTecnica, boolean estadoActual, int cargaMax) {
		this.patente = Patente;
		this.permisoCirculacion = permisoCirculacion;
		this.revisionTecnica = revisionTecnica;
		this.estadoActual = estadoActual;
		this.cargaMax = cargaMax;
	}
}