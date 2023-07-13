package ContextoProblema;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Camion {

	public static List<Camion> listaCamiones = new ArrayList<>();
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

	public void agregarCamionLista() {
		listaCamiones.add(this);
	}

	public Camion(String Patente, boolean permisoCirculacion, boolean revisionTecnica, boolean estadoActual, int cargaMax) {
		this.patente = Patente;
		this.permisoCirculacion = permisoCirculacion;
		this.revisionTecnica = revisionTecnica;
		this.estadoActual = estadoActual;
		this.cargaMax = cargaMax;
	}
}
