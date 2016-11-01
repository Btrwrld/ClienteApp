package Usuario;


import Menu.Orden;
//import Main.MainCheff;

public class Cheff {
	
	private String Usuario;
	private String Contrasena;
	
	private boolean trabajando = false;
	private Orden OrdenPorTrabajar = new Orden();
	
	
	public void notificarOrdenNueva(Orden orden){
		//MainCheff main = new MainCheff();
		//main.//Aqui se comunica con el cheff y manda notificacion
		//esto se implementa en la app
		//h
	}
	
	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public String getContrasena() {
		return Contrasena;
	}

	public void setContrasena(String contrasena) {
		Contrasena = contrasena;
	}

	public void setOrdenPorTrabajar(Orden ordenPorTrabajar) {
		OrdenPorTrabajar = ordenPorTrabajar;
	}

	public void setTrabajando(boolean nuevoEstado){
		this.trabajando = nuevoEstado;
	}
	
	public boolean getTrabajando(){
		return trabajando;
	}
	public Orden getOrdenPorTrabajar() {
		return OrdenPorTrabajar;
	}
	public void setOrdenPorTrabajarr(Orden OrdenPorTrabajar) {
		this.OrdenPorTrabajar = OrdenPorTrabajar;
	}
	
	
	
	
	
	
}


