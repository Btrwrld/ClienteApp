package Restaurante;

import Usuario.Cliente;

public class Calificacion{
	private Cliente cliente;
	private Integer calificacion;
	
	public Calificacion(){
		super();
	}

	public Integer getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}

	public Calificacion(Integer calificacion, Cliente cliente) {
		super();
		this.calificacion = calificacion;
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
