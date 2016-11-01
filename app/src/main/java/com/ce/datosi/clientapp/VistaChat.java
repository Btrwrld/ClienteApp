package com.ce.datosi.clientapp;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

import Comunicacion.Comunicador;
import Comunicacion.Contenedor;

public class VistaChat extends AppCompatActivity {

    ImageButton enviar;
    FloatingActionButton refrescar;

    static LinkedList<TextView> entradas = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_chat);

        enviar = (ImageButton)(findViewById(R.id.btnEnviar));
        refrescar = (FloatingActionButton)(findViewById(R.id.fbaSincronizar));

        entradas.addFirst((TextView) findViewById(R.id.msj11));
        entradas.addFirst((TextView) findViewById(R.id.msj10));
        entradas.addFirst((TextView) findViewById(R.id.msj9));
        entradas.addFirst((TextView) findViewById(R.id.msj8));
        entradas.addFirst((TextView) findViewById(R.id.msj7));
        entradas.addFirst((TextView) findViewById(R.id.msj6));
        entradas.addFirst((TextView) findViewById(R.id.msj5));
        entradas.addFirst((TextView) findViewById(R.id.msj4));
        entradas.addFirst((TextView) findViewById(R.id.msj3));
        entradas.addFirst((TextView) findViewById(R.id.msj2));

        refrescar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SincronizarChat chat = new SincronizarChat();
                chat.execute();
            }

        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnviarMensaje enviar = new EnviarMensaje();
                String mensaje = (String)(getIntent().getExtras().getSerializable("NombreUsuario"));
                mensaje += ": ";
                mensaje += (String)(((TextView)findViewById(R.id.txtMensaje)).getText());
                enviar.execute(mensaje);
            }

        });
    }





    private class SincronizarChat extends AsyncTask<Void, String[], Boolean> {


        @Override
        protected Boolean doInBackground(Void... params) {
            //LinkedList<String> mensajes = Contenedor.getMensajes();
            LinkedList<String> mensajes = new LinkedList<>();
            mensajes.add("Sirve carajo!!!!");
            String[] mensajesPorMostrar = new String[10];

            for(int i = 0; i < 10; i++){
                //mensajesPorMostrar[i] = mensajes.get(i);
                mensajesPorMostrar[i] = mensajes.getFirst();
            }
            publishProgress(mensajesPorMostrar);
            return true;
        }

        @Override
        protected void onProgressUpdate(String[]... values) {

            ((TextView)(findViewById(R.id.msj2))).setText(values[0][0]);
            ((TextView)(findViewById(R.id.msj3))).setText(values[0][1]);
            ((TextView)(findViewById(R.id.msj4))).setText(values[0][2]);
            ((TextView)(findViewById(R.id.msj5))).setText(values[0][3]);
            ((TextView)(findViewById(R.id.msj6))).setText(values[0][4]);
            ((TextView)(findViewById(R.id.msj7))).setText(values[0][5]);
            ((TextView)(findViewById(R.id.msj8))).setText(values[0][6]);
            ((TextView)(findViewById(R.id.msj9))).setText(values[0][7]);
            ((TextView)(findViewById(R.id.msj10))).setText(values[0][8]);
            ((TextView)(findViewById(R.id.msj11))).setText(values[0][9]);
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
                Toast.makeText(VistaChat.this, "Sincronizacion finalizada!",
                        Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(VistaChat.this, "Tarea cancelada!",
                    Toast.LENGTH_SHORT).show();
        }
    }


    private class EnviarMensaje extends AsyncTask<String, Void, Boolean> {


        @Override
        protected Boolean doInBackground(String... params) {
            if (Comunicador.verificarConexion(getApplicationContext())){
                //Comunicador.POST();
                return true;
            }
            else{
                isCancelled();
                return false;
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
                Toast.makeText(VistaChat.this, "Mensaje enviado!",
                        Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(VistaChat.this, "No hay conexion!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
