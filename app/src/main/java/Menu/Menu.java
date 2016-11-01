package Menu;

import java.util.LinkedList;

//import EstructurasDeDatos.LinkedList;

public class Menu {
	private LinkedList<Platillo> listaPlatillos = new LinkedList<Platillo>();

	
	/**
	 * Para cada platillo se registran los ingredientes que lo
	 * componen (incluye la cantidad), información nutricional, precio, tiempo de preparación, recetas
	 * y cualquier otro dato necesario
	 */
	public Menu(){
		super();
	}

	public LinkedList<Platillo> getListaPlatillos() {
		return listaPlatillos;
	}
	public void setListaPlatillos(LinkedList<Platillo> listaPlatillos) {
		this.listaPlatillos = listaPlatillos;
	}
	
	
	public void addPlatillo(Platillo platillo){
		listaPlatillos.addFirst(platillo);
	}


}
