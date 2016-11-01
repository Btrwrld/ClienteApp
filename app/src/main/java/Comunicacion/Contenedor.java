package Comunicacion;

import java.util.LinkedList;

import Menu.Pasos;
import Menu.Platillo;

import Ingredientes.Carnes;
import Ingredientes.Frutas;
import Ingredientes.Granos;
import Ingredientes.Lacteos;
import Ingredientes.Vegetales;
import Menu.Platillo;
import Restaurante.Calificacion;
import Restaurante.Restaurante;
import Usuario.Cheff;
import Usuario.Cliente;
import java.util.LinkedList;



public class Contenedor {
    private  LinkedList<Carnes> ListaCarnes;// = new LinkedList<Carnes>();
    private  LinkedList<Frutas> ListaFrutas;// = new LinkedList<Frutas>();
    private  LinkedList<Granos> ListaGranos;// = new LinkedList<Granos>();
    private  LinkedList<Lacteos> ListaLacteos;// = new LinkedList<Lacteos>();
    private  LinkedList<Vegetales> ListaVegetales;// = new LinkedList<Vegetales>();
    private  LinkedList<Platillo> listaPlatillos;// = new LinkedList<Platillo>();
    private  Restaurante restaurante;// = new Restaurante();
    private LinkedList<Cliente> ListaClientes;
    private LinkedList<Cheff> ListaCheffs;



    public Contenedor() {
        super();
        ListaCarnes = new LinkedList<Carnes>();
        ListaFrutas = new LinkedList<Frutas>();
        ListaGranos = new LinkedList<Granos>();
        ListaLacteos = new LinkedList<Lacteos>();
        ListaVegetales = new LinkedList<Vegetales>();
        listaPlatillos = new LinkedList<Platillo>();
        restaurante = new Restaurante();
        ListaClientes = new LinkedList<Cliente>();
        ListaCheffs = new LinkedList<Cheff>();
    }






    public LinkedList<Carnes> getListaCarnes() {
        return ListaCarnes;
    }
    public void setListaCarnes(LinkedList<Carnes> listaCarnes) {
        ListaCarnes = listaCarnes;
    }
    public LinkedList<Frutas> getListaFrutas() {
        return ListaFrutas;
    }
    public void setListaFrutas(LinkedList<Frutas> listaFrutas) {
        ListaFrutas = listaFrutas;
    }
    public LinkedList<Granos> getListaGranos() {
        return ListaGranos;
    }
    public void setListaGranos(LinkedList<Granos> listaGranos) {
        ListaGranos = listaGranos;
    }
    public LinkedList<Lacteos> getListaLacteos() {
        return ListaLacteos;
    }
    public void setListaLacteos(LinkedList<Lacteos> listaLacteos) {
        ListaLacteos = listaLacteos;
    }
    public LinkedList<Vegetales> getListaVegetales() {
        return ListaVegetales;
    }
    public void setListaVegetales(LinkedList<Vegetales> listaVegetales) {
        ListaVegetales = listaVegetales;
    }
    public LinkedList<Platillo> getListaPlatillos() {
        return listaPlatillos;
    }
    public void setListaPlatillos(LinkedList<Platillo> listaPlatillos) {
        this.listaPlatillos = listaPlatillos;
    }
    public Restaurante getRestaurante() {
        return restaurante;
    }
    public LinkedList<Cliente> getListaClientes() {
        return ListaClientes;
    }
    public void setListaClientes(LinkedList<Cliente> listaClientes) {
        ListaClientes = listaClientes;
    }
    public LinkedList<Cheff> getListaCheffs() {
        return ListaCheffs;
    }
    public void setListaCheffs(LinkedList<Cheff> listaCheffs) {
        ListaCheffs = listaCheffs;
    }
    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }






    public void addCarnes(Carnes carne) {
        ListaCarnes.add(carne);
    }
    public void addFrutas(Frutas fruta) {
        ListaFrutas.add(fruta);
    }
    public void addGranos(Granos grano) {
        ListaGranos.add(grano);
    }
    public void addLacteos(Lacteos lacteo) {
        ListaLacteos.add(lacteo);
    }
    public void addVegetales(Vegetales vegetal) {
        ListaVegetales.add(vegetal);
    }
    public void addPlatillos(Platillo platillo) {
        listaPlatillos.add(platillo);
    }
    public void addCalificacion(Calificacion calificacion){
        restaurante.addCalificacion(calificacion);
    }
    public void addCliente(Cliente cliente){
        ListaClientes.add(cliente);
    }
    public void addCheff(Cheff cheff){
        ListaCheffs.add(cheff);
    }

}

