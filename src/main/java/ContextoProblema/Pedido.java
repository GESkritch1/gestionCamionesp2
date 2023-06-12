package ContextoProblema;

import java.util.*;

public class Pedido {

	Collection<Camion> transporte;
	private String fecha;
	private String fechaInicial;
	private String fechaFinal;

	public String getFecha() {
		return this.fecha;
	}

	public String getFechaInicial() {
		return this.fechaInicial;
	}

	public String getFechaFinal() {
		return this.fechaFinal;
	}

	public void agregarCamionAPedido(Camion camion) {
		transporte.add(camion);
	}

	public void agregarPedido() {

	}

	public void eliminarPedido() {
		// TODO - implement Pedido.eliminarPedido
		throw new UnsupportedOperationException();
	}

	public void modificarPedido() {
		// TODO - implement Pedido.modificarPedido
		throw new UnsupportedOperationException();
	}

}