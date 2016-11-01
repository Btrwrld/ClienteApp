package Menu;

public class Pasos {
	private String paso;
	private boolean pasoTerminado = false;
	
	public Pasos(){
		paso = null;
	}

	public String getPaso() {
		return paso;
	}

	public void setPaso(String paso) {
		this.paso = paso;
	}
	
	public void terminarPaso(){
		this.pasoTerminado = true;
	}
	
	public boolean pasoTerminado(){
		return pasoTerminado;
	}
	

}
