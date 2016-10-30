package Usuario;

public class Cliente {
	private String nombre;
	private String Contrasena;
	private int categoria;//Bronce=1     oro=2       platino=3
	public Integer mesa;
	private Integer numeroOrdenes;

	public Cliente(){
		super();
		numeroOrdenes = 0;
		categoria = 1;
		this.mesa = 0;
	}

	public String getContrasena() {
		return Contrasena;
	}


	public void setContrasena(String contrasena) {
		Contrasena = contrasena;
	}


	public Integer getMesa() {
		return mesa;
	}


	public void setMesa(Integer mesa) {
		this.mesa = mesa;
	}


	public Integer getNumeroOrdenes() {
		return numeroOrdenes;
	}


	public void setNumeroOrdenes(Integer numeroOrdenes) {
		this.numeroOrdenes = numeroOrdenes;
	}



	public void sumarOrden(){
		numeroOrdenes ++;
		if(numeroOrdenes > 5 && numeroOrdenes < 10){
			categoria = 2;
		}
		else if(numeroOrdenes > 15){
			categoria = 3;
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCategoria() {
		return this.categoria;
	}

	public void setCategoria(int categoria){
		this.categoria = categoria;
	}


}

