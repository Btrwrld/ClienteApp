package Comunicacion;

import java.util.LinkedList;

import Menu.Pasos;
import Menu.Platillo;

/**
 * Created by erick on 10/29/2016.
 */

public class Contenedor implements Runnable {

    private static LinkedList<String> chat = new  LinkedList<>();
    private static LinkedList<Pasos> pasos = new  LinkedList<>();
    private static LinkedList<Platillo> platillos = new LinkedList<>();
    private static LinkedList<String> mensajes = new LinkedList<>();

    public static LinkedList<String> getChat(){
        return chat;
    }

    public static LinkedList<Pasos> getPasos(){
        return pasos;}

    public static LinkedList<Platillo> getPlatillos(){
        return platillos;}

    public static LinkedList<String> getMensajes(){
        return mensajes;}


    @Override
    public void run() {

    }
}
