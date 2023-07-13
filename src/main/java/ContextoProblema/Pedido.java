package ContextoProblema;

import Archivador.Archivador;

import java.time.LocalDateTime;
import java.util.List;

public class Pedido {
	private int distancia;
	private Camion camion;
	private String patenteCamion;
	private String codigo;
	private String fechaInicial;
	private LocalDateTime fechaFinal;
	private String lugarSalida;
	private String lugarDestino;
	private int tiempo;

	private List<Camion> listaCamiones = Archivador.getListaCamiones();

	/***
	 * Este constructor crea una instancia de Pedido con los valores proporcionados. Inicializa los atributos codigo, camion, fechaInicial, fechaFinal, lugarSalida, lugarDestino, distancia y tiempo con los valores pasados como argumentos.
	 * @param codigo
	 * @param camion
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param lugarDestino
	 * @param distancia
	 * @param tiempo
	 */
	public Pedido(String codigo, Camion camion, String fechaInicial, LocalDateTime fechaFinal, String lugarDestino, int distancia, int tiempo) {
		this.codigo = codigo;
		this.camion = camion;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.lugarSalida = "Temuco";
		this.lugarDestino = lugarDestino;
		this.distancia = distancia;
		this.tiempo = tiempo;
	}

	/***
	 * Este constructor crea una instancia de Pedido con los valores proporcionados. Inicializa los atributos codigo, patenteCamion, fechaInicial, fechaFinal, lugarSalida, lugarDestino, distancia y tiempo con los valores pasados como argumentos.
	 * @param codigo
	 * @param patenteCamion
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param lugarDestino
	 * @param distancia
	 * @param tiempo
	 */
	public Pedido(String codigo, String patenteCamion, String fechaInicial, LocalDateTime fechaFinal, String lugarDestino, int distancia, int tiempo) {
		this.codigo = codigo;
		this.patenteCamion = patenteCamion;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.lugarSalida = "Temuco";
		this.lugarDestino = lugarDestino;
		this.distancia = distancia;
		this.tiempo = tiempo;
	}

	/***
	 *  Este constructor crea una instancia de Pedido y establece la lista de camiones listaCamiones con el valor proporcionado.
	 * @param listaCamiones
	 */
	public Pedido(List<Camion> listaCamiones) {
		this.listaCamiones = listaCamiones;
	}

	/***
	 *  Este constructor crea una instancia de Pedido sin ningún valor asignado solo para poder generarlo en otra Clase.
	 */
	public Pedido() {
	}

	/***
	 *  Este constructor crea una instancia de Pedido con el código proporcionado y deja los demás atributos sin asignar.
	 * @param codigo
	 */
	public Pedido(String codigo) {
		this.codigo = codigo;
	}

	/***
	 * Este método devuelve el código del pedido.
	 * @return codigo
	 */
	public String getCodigo() {
		return this.codigo;
	}

	/***
	 * Este método devuelve la patente del camión asociado al pedido.
	 * @return
	 */
	public String getPatenteCamion(){
		return this.patenteCamion;
	}

	/***
	 * Este método devuelve la distancia del pedido.
	 * @return distancia
	 */
	public int getDistancia() {
		return distancia;
	}

	/***
	 *  Este método devuelve la fecha inicial del pedido.
	 * @return fechainicial
	 */
	public String getFechaInicial() {
		return this.fechaInicial;
	}

	/***
	 * Este método devuelve la fecha final del pedido.
	 * @return fechaFinal
	 */
	public LocalDateTime getFechaFinal() {
		return this.fechaFinal;
	}

	/***
	 * Este método devuelve el lugar de salida del pedido.
	 * @return lugarSalida
	 */
	public String getLugarSalida() {
		return this.lugarSalida;
	}

	/***
	 * Este método devuelve el lugar de destino del pedido.
	 * @return lugarDestino
	 */
	public String getLugarDestino() {
		return this.lugarDestino;
	}

	/***
	 * Este método devuelve el tiempo estimado del pedido.
	 * @return tiempo
	 */
	public int getTiempo() {
		return this.tiempo;
	}
}