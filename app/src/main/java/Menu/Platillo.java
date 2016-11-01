package Menu;

import java.util.LinkedList;
import Ingredientes.IngredienteSolo;
//import EstructurasDeDatos.LinkedList;
//import Usuario.Cheff;

public class Platillo {

	private String nombre;
	private Integer tiempoDePreparacion;
	private String informacionNutricional;
	//private InformacionNutricional informacionNutricional;
	private Integer precio;
	private Integer cantidadDeIngredientes;
	private Receta receta;
	private String datoExtra;
	private LinkedList<IngredienteSolo> listaIngredientes;
	
	/**
	 * Para cada platillo se registran los ingredientes que lo
	 * componen (incluye la cantidad), información nutricional, precio, tiempo de preparación, recetas
	 * y cualquier otro dato necesario
	 */
	public Platillo(){
		super();
		nombre = null;
		tiempoDePreparacion = 0;
		informacionNutricional = null;
		precio =  0;
		cantidadDeIngredientes = 0;
		receta = new Receta();
		datoExtra = null;
		listaIngredientes = new LinkedList<IngredienteSolo>();
	}


	public Integer getTiempoDePreparacion() {
		return tiempoDePreparacion;
	}


	public void setTiempoDePreparacion(Integer tiempoDePreparacion) {
		this.tiempoDePreparacion = tiempoDePreparacion;
	}


	public String getInformacionNutricional() {
		return informacionNutricional;
	}


	public void setInformacionNutricional(String informacionNutricional) {
		this.informacionNutricional = informacionNutricional;
	}


	public Integer getPrecio() {
		return precio;
	}


	public void setPrecio(Integer precio) {
		this.precio = precio;
	}


	public Integer getCantidadDeIngredientes() {
		return cantidadDeIngredientes;
	}


	public void setCantidadDeIngredientes(Integer cantidadDeIngredientes) {
		this.cantidadDeIngredientes = cantidadDeIngredientes;
	}


	public Receta getReceta() {
		return receta;
	}


	public void setReceta(Receta receta) {
		this.receta = receta;
	}


	public String getDatoExtra() {
		return datoExtra;
	}


	public void setDatoExtra(String datoExtra) {
		this.datoExtra = datoExtra;
	}

	
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LinkedList<IngredienteSolo> getListaIngredientes() {
		return listaIngredientes;
	}


	public void setListaIngredientes(LinkedList<IngredienteSolo> listaIngredientes) {
		this.listaIngredientes = listaIngredientes;
	}
	
	public void addIngrediente(IngredienteSolo ingrediente) {
		this.cantidadDeIngredientes +=1;
		listaIngredientes.add(ingrediente);
	}
	
	public void removeIngrediente(IngredienteSolo ingrediente) {
		this.cantidadDeIngredientes -=1;
		listaIngredientes.remove(ingrediente);
	}
	
	
}
