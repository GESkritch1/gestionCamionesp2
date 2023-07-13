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


	public Pedido(List<Camion> listaCamiones) {
		this.listaCamiones = listaCamiones;
	}

	public Pedido() {

	}

	public Pedido(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public String getPatenteCamion(){
		return this.patenteCamion;
	}

	public int getDistancia() {
		return distancia;
	}

	public String getFechaInicial() {
		return this.fechaInicial;
	}

	public LocalDateTime getFechaFinal() {
		return this.fechaFinal;
	}

	public String getLugarSalida() {
		return this.lugarSalida;
	}

	public String getLugarDestino() {
		return this.lugarDestino;
	}

	public int getTiempo() {
		return this.tiempo;
	}
}