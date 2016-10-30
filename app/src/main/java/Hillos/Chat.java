package Hillos;

import android.widget.TextView;

import java.util.LinkedList;

import Comunicacion.Contenedor;

import static java.lang.Thread.sleep;

/**
 * Created by erick on 10/29/2016.
 */

public class Chat implements Runnable {

    private LinkedList<TextView> entradas;

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < Contenedor.getChat().size(); i++) {
                entradas.get(i).setText(Contenedor.getChat().get(i));
            }
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void setEntradas(LinkedList<TextView> entradas){
        this.entradas = entradas;
    }
}
