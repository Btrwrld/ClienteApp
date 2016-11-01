package Algoritmos;
import EstructurasDeDatos.ColaOrdenes;
import EstructurasDeDatos.ListaDoble;
import EstructurasDeDatos.NodoDoble;
import Ingredientes.*;
import Menu.Orden;
import java.util.LinkedList;


public class Ordenador {

	public static void main(String[] args) {
		Vegetales v1 = new Vegetales();
		Vegetales v2 = new Vegetales();
		Vegetales v3 = new Vegetales();
		Vegetales v4 = new Vegetales();
		Vegetales v5 = new Vegetales();
		Vegetales v6 = new Vegetales();
		v1.setName("Z");
		v2.setName("J");
		v3.setName("Nb");
		v4.setName("Na");
		v5.setName("A");
		v6.setName("K");
		ListaDoble<Vegetales> lista = new ListaDoble<Vegetales>();
		lista.add(v1);
		lista.add(v2);
		lista.add(v3);
		lista.add(v4);
		lista.add(v5);
		lista.add(v6);
		
		ordenarVegetales(lista);
		
		for(int i = 0; i < lista.size(); i++){
			System.out.println(lista.get(i).getDato().getName());
		}
	}
	
	
	public static void ordenarOrdenes(ColaOrdenes cola){
		int gap = cola.size() / 2;
		while (gap > 0) {
			for (int i = 0; i < cola.size() - gap; i++) {
				
				int j = i + gap;
				NodoDoble<Orden> tmp = new NodoDoble<Orden>();
				tmp.setDato(cola.get(j).getDato());
				
				while ((j >= gap) && (tmp.getDato().getCliente().getCategoria() > cola.get(j - gap).getDato().getCliente().getCategoria())) {
					cola.get(j).setDato(cola.get(j - gap).getDato());
					
					j -= gap;
				}
				cola.get(j).setDato(tmp.getDato());
			}
			
		    if (gap == 2){ 
		    	gap = 1;} 
			else {
				gap /= 2.2;
			}
        }
	}
	
	//ShellSort
	public static void ordenarVegetales(ListaDoble<Vegetales> lista){
		int gap = lista.size() / 2;
		while (gap > 0) {
			for (int i = 0; i < lista.size() - gap; i++) {
				
				int j = i + gap;
				NodoDoble<Vegetales> tmp = new NodoDoble<Vegetales>();
				tmp.setDato(lista.get(j).getDato());
				
				while ((j >= gap) && (tmp.getDato().getName().compareToIgnoreCase(lista.get(j - gap).getDato().getName()) < 0)) {
					lista.get(j).setDato(lista.get(j - gap).getDato());
					
					j -= gap;
				}
				lista.get(j).setDato(tmp.getDato());
			}
			
		    if (gap == 2){ 
		    	gap = 1;} 
			else {
				gap /= 2.2;
			}
        }
	}
	
	//se inician en 0 y len - 1
	//QuickSort
	public static void ordenarFrutas(ListaDoble<Frutas> lista, int indiceInicial, int indiceFinal){
		if (indiceInicial < indiceFinal) { 
	        int pivote = indiceInicial;
	        for (int i = indiceInicial + 1; i < indiceFinal; i++) { 
	            if ((lista.get(i).getDato().getName()).compareToIgnoreCase(lista.get(pivote).getDato().getName()) < 0) { 
	            	lista.insertarAntes(lista.get(i), lista.get(pivote));
	            	++pivote;
	            }
	        }
	        ordenarFrutas(lista, indiceInicial, pivote);
	        ordenarFrutas(lista, pivote + 1, indiceFinal);
	    } 
    }
	
	//BubbleSort
	public static void ordenarLacteos(ListaDoble<Lacteos> lista){
		int indice = 0;
		boolean cambios = true;
		
		while((cambios) || (indice + 1 < lista.size()) ){
			if (indice + 1 == lista.size()){
				indice = 0;
				cambios = false;
			}
			if((lista.get(indice).getDato().getName().compareToIgnoreCase(lista.get(indice + 1).getDato().getName())) > 0){
				lista.intercambiar(lista.get(indice), lista.get(indice + 1));
				cambios = true;
			}
			++indice;
		}
	
	}
	
	//InsertionSort
	public static void ordenarCarnes(ListaDoble<Carnes> lista){	
	    for (int ordenando = 0; ordenando < lista.size; ordenando++) {
	    	int posMenor = ordenando;
	        for (int sonda = ordenando + 1; (sonda < lista.size); sonda++){
	        	if (lista.get(sonda).getDato().getName().compareToIgnoreCase(lista.get(posMenor).getDato().getName()) < 0 ){
	        		posMenor = sonda;
	        	}
	        }
	        lista.insertarAntes(lista.get(posMenor), lista.get(ordenando));
	    }
	}
	
	//RadixSort
	public static void ordenarGranos(ListaDoble<Granos> lista){
		for(int i = lista.size - 1; i >= 0 ; i--){
			ordenamientoSeleccion(lista)
	        ; //order strings by characters at their i-th position
	    }
	}
	
	private static void ordenamientoSeleccion(ListaDoble<Granos> lista){
		int posMenor = 0, actual = 0, porOrdenar = 0;
		while(porOrdenar < lista.size()){
			if (actual == lista.size()){
				lista.intercambiar(lista.get(posMenor), lista.get(porOrdenar));
				++porOrdenar;
				posMenor = actual = porOrdenar;			
			}
			else if((lista.get(actual).getDato().getName().compareToIgnoreCase(lista.get(posMenor).getDato().getName())) < 0){
				posMenor = actual;
				++actual;
			}
			else
				++actual;
		}
	}
}

