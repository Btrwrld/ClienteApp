package Menu;

import java.util.LinkedList;

import Restaurante.Restaurante;
import Usuario.Cliente;

public class Orden{

	private Cliente cliente = new Cliente();
	private Platillo platillo = new Platillo();
	private Integer monto;
	private String estado;
	public Restaurante sede;
	private boolean ordenTerminada = false;
	private LinkedList<Pasos> listaPasos;


	public Orden(){
		super();
		cliente = null;
		platillo = null;
		monto = null;
		estado = "Debe";
		listaPasos = new LinkedList<Pasos>();
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Platillo getPlatillo() {
		return platillo;
	}


	public void setPlatillo(Platillo platillo) {
		listaPasos = platillo.getReceta().getListaPasos();
		this.platillo = platillo;
	}


	public Integer getPagado() {
		return monto;
	}


	public void setPagado(Integer monto) {
		this.monto = monto;
	}
	
	
	public String getEstado(){
		return estado;
	}
	
	public LinkedList<Pasos> getListaPasos() {
		return listaPasos;
	}


	public void setListaPasos(LinkedList<Pasos> listaPasos) {
		this.listaPasos = listaPasos;
	}
	
	
	//Se agrego para prueba
	public void addPaso(Pasos paso){
		listaPasos.add(paso);
		
	}
	
	public void Pagar(Integer monto) {
		if(this.monto - monto<=0){
			this.monto-=monto;
			estado = "Pagado";
		}
		else{
			this.monto-=monto;
		}
	}
	
	public boolean getOrdenTerminada(){
		return this.ordenTerminada;
	}
	
	public void setOrdenTerminada(boolean nuevoEstado){
		this.ordenTerminada = nuevoEstado;
	}

}
