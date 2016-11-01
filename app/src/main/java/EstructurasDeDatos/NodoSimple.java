package EstructurasDeDatos;

public class NodoSimple<T> {
	
	private T dato;
	private NodoSimple<T> siguiente;
	 
	 
	public NodoSimple()
    {
        siguiente = null;
        dato = null;
    }
	
	public NodoSimple(T dato, NodoSimple<T> siguiente)
	{
        this.dato = dato;
        this.siguiente = siguiente;
    }
	
    public NodoSimple(T dato)
    {
        this.dato = dato;
        this.siguiente = null;
    }
    
    
    
    
    public void setSiguiente(NodoSimple<T> siguiente)
    {
        this.siguiente = siguiente;
    }
    public NodoSimple<T> getSiguiente()
    {
        return this.siguiente;
    }
    public void setDato(T dato)
    {
        this.dato = dato;
    }
    /* Function to get dato from node */
    public T getDato()
    {
        return dato;
    }
}
