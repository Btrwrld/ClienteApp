package Restaurante;

import Menu.Orden;


import EstructurasDeDatos.BinarySearchTree;
import EstructurasDeDatos.ColaOrdenes;
import Usuario.Cheff;
import Usuario.Cliente;
import java.util.LinkedList;


public class Restaurante{
	private BinarySearchTree listaCalificaciones = new BinarySearchTree();
	private LinkedList<Cheff> listaCheffs = new LinkedList<Cheff>();
	private ColaOrdenes colaOrdenes = new ColaOrdenes();

	public Restaurante(){
	}
	
	public ColaOrdenes getColaOrdenes(){
		return colaOrdenes;
	}

	public LinkedList<Cheff> getListaChefs(){
		return listaCheffs;
	}

	public Integer searchCalificacion(Cliente cliente){
		return listaCalificaciones.findCliente(cliente);
	}
	
	public BinarySearchTree getListaCalificaciones() {
		return listaCalificaciones;
	}

	public void setListaCalificaciones(BinarySearchTree listaCalificaciones) {
		this.listaCalificaciones = listaCalificaciones;
	}

	public void addCalificacion(Calificacion calificacion) {
		listaCalificaciones.insert(calificacion);
	}

	public boolean validarCheff(String Usuario,String Contrasena){
		for(int i =0; i<listaCheffs.size(); i++){
			if(listaCheffs.get(i).getUsuario().equals(Usuario)&&listaCheffs.get(i).getContrasena().equals(Contrasena)){
				return true;
			}
		}
		return false;
	}

	public void nuevaOrden(Orden orden) {
		orden.getCliente().sumarOrden();
		orden.sede = this;
		colaOrdenes.agregarACola(orden);
	}
}
