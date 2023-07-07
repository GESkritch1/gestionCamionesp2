package ContextoProblema;

import Archivador.Archivador;

import javax.swing.*;
import java.util.*;

public class Pedido {

	private String distancia;
	List<Camion> transporte;
	private  String codigo;
	private int fechaInicial;
	private int fechaFinal;
	private String lugarSalida;
	private String lugarDestino;
	private int tiempo;

	public String getCodigo(){return  this.codigo;}
	public int getFechaInicial() {
		return this.fechaInicial;
	}

	public int getFechaFinal() {
		return this.fechaFinal;
	}
	public String getLugarSalida(){return  this.lugarSalida;}
	public String getLugarDestino(){return  this.lugarDestino;}
	public int getTiempo(){return  this.tiempo;}

	public void agregarCamionAPedido(Camion camion) {
		transporte.add(camion);
	}

	public Pedido(String Codigo,List<Camion> Camion,int fechaInicial,int fechaFinal,String lugarDestino,String distancia, int tiempo){
		this.codigo = Codigo;
		this.transporte = Camion;
		this.fechaInicial = fechaInicial;
		this.fechaFinal= fechaFinal;
		this.lugarSalida= "Temuco";
		this.lugarDestino = lugarDestino;
		this.distancia = distancia;
		this.tiempo = tiempo;

	}

	public void agregarPedido(String codigo,List<Camion> Camion,int fechaInicial,int fechaFinal,String lugarSalida,String lugarDestino, int tiempo,JPanel mainPanel) {
		Pedido nuevoPedido = new Pedido(codigo,Camion,fechaInicial,fechaFinal,lugarSalida,lugarDestino,tiempo);
		Archivador ar = new Archivador();
		ar.agregarPedidoATexto("listaPedidos.txt",nuevoPedido,mainPanel);
	}

	public void eliminarPedido(String pedidoAeliminar, JPanel mainPanel) {
		Archivador ar= new Archivador();
		ar.eliminarCamionArchivotxt(pedidoAeliminar, "listaPedidos.txt",mainPanel);

	}

	public void mostarPedido(JPanel mainPanel) {
		Archivador ar = new Archivador();
		ar.mostrarArchivo("listaChoferes.txt", mainPanel);
	}

}