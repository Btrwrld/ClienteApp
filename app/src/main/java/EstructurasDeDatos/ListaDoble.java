package EstructurasDeDatos;

import java.util.NoSuchElementException;


public class ListaDoble<T> {

	public NodoDoble<T> inicio;
	private NodoDoble<T> ultimo;
	public int size;
	
	public ListaDoble() {
		//size = 0;
	}
	public int size() { 
		return size; 
	}	
	public boolean isEmpty() { 
		return size == 0; 
	}
	
	public void add(T dato) {
		
		NodoDoble<T> temporal = new NodoDoble<T>(dato, null, ultimo);
		if(ultimo != null) {
			ultimo.setSiguiente(temporal);
			}
		ultimo = temporal;
		if(inicio == null) {
			inicio = temporal;}
		size++;
		System.out.println("adding: "+dato);
	}
	public void addFirst(T dato) {
		NodoDoble<T> temporal = new NodoDoble<T>(dato, inicio, null);
		if(inicio != null ) {
			inicio.setAnterior(temporal);
			}
		inicio = temporal;
		if(ultimo == null) { ultimo = temporal;}
		size++;
		System.out.println("adding: " + dato.getClass().getName());
	}
	public void addAt(T dato , int pos)
	{
		NodoDoble<T> temporal = new NodoDoble<T>(dato, null, null);    
		if (pos == 0)
		{
			addFirst(dato);
			return;
		}            
		NodoDoble<T> ptr = inicio;
		for (int i = 1; i < size; i++)
		{
			if (i == pos)
			{
				NodoDoble<T> tmp = ptr.getSiguiente();
				ptr.setSiguiente(temporal);
				temporal.setAnterior(ptr);
				temporal.setSiguiente(tmp);
				tmp.setAnterior(temporal);
			}
			ptr = ptr.getSiguiente();            
		}
		size++ ;
	}
	public T deleteFirst() {
		if (size == 0) throw new NoSuchElementException();
		NodoDoble<T> temporal = inicio;
		inicio = inicio.getSiguiente();
		inicio.setAnterior(null);
		size--;
		return temporal.getDato();
	}
	public T deleteLast() {
		if (size == 0) throw new NoSuchElementException();
		NodoDoble<T> temporal = ultimo;
		ultimo = ultimo.getAnterior();
		ultimo.setSiguiente(null);
		size--;
		return temporal.getDato();
	}
	public void deleteAt(int index){    
        if (size == 1 && index == 0){
            inicio = null;
            ultimo = null;
            size = 0;
            return ;
        }        
        if (index == 0){
            this.inicio = this.inicio.getSiguiente();
            this.inicio.setAnterior(null);
            size--; 
            return ;
        }
        if (index == size - 1){
            this.ultimo = this.ultimo.getAnterior();
            this.ultimo.setSiguiente(null);
            size --;
            return;
        }
        else{
        	
        }
        size-- ;
    }
	public T search(T dato){
		NodoDoble<T> temporal = new NodoDoble<T>();
		temporal = inicio;
		int contador = 0;
		while(!temporal.getDato().equals(dato)){
			if(contador==size-1){
				return null;
			}
			else{
				temporal = temporal.getSiguiente();
			}
		}
		return temporal.getDato();
	}
	
	public void print(){
		NodoDoble<T> temporal = new NodoDoble<T>();
		temporal = inicio;
		for(int i = 0;i < size; i ++){
			System.out.println(temporal.print());
			temporal = temporal.getSiguiente();
		}
		
	}
	
	public NodoDoble<T> get(int posicion){
		NodoDoble<T> temp = this.inicio;
		
		for (int i = 0; i < posicion; i++){
		     temp = temp.getSiguiente();
		}
		return temp;
		
	}
	
	public void borrarDato(T dato){
		for(int i = 0; i < this.size(); i++){
			if(this.get(i).getDato() == dato){
				this.deleteAt(i);
			}
		}
	}
	
	public void intercambiar(NodoDoble<T> nodo1, NodoDoble<T> nodo2){
		T aux = nodo1.getDato();
		
		nodo1.setDato(nodo2.getDato());
		nodo2.setDato(aux);
	}
	
	public void insertarAntes(NodoDoble<T> nodoAInsertar, NodoDoble<T> nodoConsecutivo){
		
		if (nodoAInsertar != nodoConsecutivo){
			//Se elimina el nodo a insertar
			if(nodoAInsertar == this.ultimo){
				this.ultimo.getAnterior().setSiguiente(null);
				this.ultimo = this.ultimo.getAnterior();
			}
			
			else if(nodoAInsertar == this.inicio){
				this.inicio.getSiguiente().setAnterior(null);
				this.inicio = this.inicio.getSiguiente();
			}
			else{
				nodoAInsertar.getSiguiente().setAnterior(nodoAInsertar.getAnterior());
				nodoAInsertar.getAnterior().setSiguiente(nodoAInsertar.getSiguiente());
			}
			
			
			//Se eliminan las referencias antiguas del nodo
			nodoAInsertar.setAnterior(null);
			nodoAInsertar.setSiguiente(null);
			
			if(nodoConsecutivo == this.inicio){
				this.inicio.setAnterior(nodoAInsertar);
				
				this.inicio = nodoAInsertar;
				nodoAInsertar.setSiguiente(nodoConsecutivo);
			}
			
			else {
				nodoConsecutivo.getAnterior().setSiguiente(nodoAInsertar);
				nodoAInsertar.setAnterior(nodoConsecutivo.getAnterior());
				
				nodoConsecutivo.setAnterior(nodoAInsertar);
				nodoAInsertar.setSiguiente(nodoConsecutivo);
			}
		}
	}
	
}
	
	
	
