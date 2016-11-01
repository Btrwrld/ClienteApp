package EstructurasDeDatos;
import Algoritmos.Ordenador;
import Menu.Orden;

public class ColaOrdenes {
	
	private NodoSimple<Orden> primero;
	private int size;
	
	public ColaOrdenes() {
		primero = null;
		size = 0;
	}
	
	public void agregarACola(Orden orden) {
		NodoSimple<Orden> nuevaOrden = new NodoSimple<Orden>(orden);
		
		if (primero == null) {
			primero = nuevaOrden;
		} 
		else{
			primero.setSiguiente(nuevaOrden);
			//primero = this.primero.getAnterior();
		}
		Ordenador.ordenarOrdenes(this);
		for(int i = 0; i < this.size(); i++){
			System.out.println(this.get(i).getDato().getCliente().getCategoria());
		}
		size++;
		
	}

	public Orden sacarDeCola() {
		if (primero == null){
			System.out.println("Esta pidiendo un nulo, revise");
			return null;
		}
		Orden orden = primero.getDato();
		primero = primero.getSiguiente();
		size--;
		return orden;
	} 

	public boolean isEmpty() {
		return (size == 0);
	}

	public int size() {
		return size;
	}
	
	
	public NodoSimple<Orden> get(int posicion){
		NodoSimple<Orden> temp = this.primero;
		
		for (int i = 0; i < posicion; i++){
		     temp = temp.getSiguiente();
		}
		return temp;		
	}
	
	public NodoSimple<Orden> buscarOrden(Orden orden){
		NodoSimple<Orden> temp = this.primero;
		
		while((temp.getDato() != orden) && (temp.getDato() != null)){
			temp = temp.getSiguiente();
		}
		
		return temp;
	}

} 


