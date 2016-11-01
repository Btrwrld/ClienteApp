package Ingredientes;

public class IngredienteSolo {
	private Carnes carne = new Carnes();
	private Frutas fruta = new Frutas();
	private Granos grano = new Granos();
	private Lacteos lacteo = new Lacteos();
	private Vegetales vegetal = new Vegetales();
	
	public IngredienteSolo(){
		super();
		carne = null;
		fruta = null;
		grano = null;
		lacteo = null;
		vegetal = null;
		
	}
	
	
	public Carnes getCarne() {
		return carne;
	}
	public void setCarne(Carnes carne) {
		this.carne = carne;
	}
	public Frutas getFruta() {
		return fruta;
	}
	public void setFruta(Frutas fruta) {
		this.fruta = fruta;
	}
	public Granos getGrano() {
		return grano;
	}
	public void setGrano(Granos grano) {
		this.grano = grano;
	}
	public Lacteos getLacteo() {
		return lacteo;
	}
	public void setLacteo(Lacteos lacteo) {
		this.lacteo = lacteo;
	}
	public Vegetales getVegetal() {
		return vegetal;
	}
	public void setVegetal(Vegetales vegetal) {
		this.vegetal = vegetal;
	}
	
	

}
