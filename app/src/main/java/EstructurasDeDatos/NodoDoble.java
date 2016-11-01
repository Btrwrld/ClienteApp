package EstructurasDeDatos;

public class NodoDoble<T>{
	
    private T dato;
    private NodoDoble<T> siguiente, anterior;
 
    /* Constructor */
    public NodoDoble()
    {
        siguiente = null;
        anterior = null;
        dato = null;
    }
    /* Constructor */
    public NodoDoble(T dato, NodoDoble<T> siguiente, NodoDoble<T> anterior)
    {
        this.dato = dato;
        this.siguiente = siguiente;
        this.anterior = anterior;
    }
    public NodoDoble(T dato)
    {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }
    /* Function to set link to siguiente node */
    public void setSiguiente(NodoDoble<T> siguiente)
    {
        this.siguiente = siguiente;
    }
    /* Function to set link to previous node */
    public void setAnterior(NodoDoble<T> anterior)
    {
        this.anterior = anterior;
    }    
    /* Funtion to get link to siguiente node */
    public NodoDoble<T> getSiguiente()
    {
        return siguiente;
    }
    /* Function to get link to previous node */
    public NodoDoble<T> getAnterior()
    {
        return anterior;
    }
    /* Function to set dato to node */
    public void setDato(T dato)
    {
        this.dato = dato;
    }
    /* Function to get dato from node */
    public T getDato()
    {
        return dato;
    }
    
    public String print(){
    	return (dato.getClass()).getName();
    }
}