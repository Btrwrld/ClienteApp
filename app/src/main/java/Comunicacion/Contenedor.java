package Comunicacion;

import java.util.LinkedList;

/**
 * Created by erick on 10/29/2016.
 */

public class Contenedor {

    private static LinkedList<String> chat = new  LinkedList<String>();
    private static LinkedList<String> pasos = new  LinkedList<String>();

    public static LinkedList<String> getChat(){
        return chat;
    }

    public static LinkedList<String> getPasos(){
        return pasos;}
}
