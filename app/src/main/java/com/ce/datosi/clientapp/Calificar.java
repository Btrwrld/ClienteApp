package com.ce.datosi.clientapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.LinkedList;

import Comunicacion.Comunicador;

public class Calificar extends AppCompatActivity {

    Button calificar;
    RatingBar barraCalificaciones;
    EditText comentarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificar);
        barraCalificaciones = (RatingBar) findViewById(R.id.rbcalificacion);
        calificar = (Button) findViewById(R.id.btnenviarCalificacion);
        comentarios = (EditText)findViewById(R.id.txtcomentarios);

        calificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinkedList<Object> datos = new LinkedList<>();
                datos.addFirst(barraCalificaciones.getRating());
                datos.addFirst(comentarios.getText().toString());

                EnviarCalificacion envioDatos = new EnviarCalificacion();
                envioDatos.execute(datos);

            }

        });

    }




    private class EnviarCalificacion extends AsyncTask<LinkedList<Object>, Void, Boolean> {


        @Override
        protected Boolean doInBackground(LinkedList<Object>... params) {
            boolean retorno;

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            String comentario = gson.toJson(params[0].getFirst());
            String calificacion = gson.toJson(params[0].getLast());

            if (Comunicador.verificarConexion(getApplicationContext())) {
                //Comunicador.POST();   MENSAJE
                //Comunicador.POST();   CALIFICACION
                retorno = true;}
            else {
                isCancelled();
                retorno = false;
            }
            return retorno;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result)
                Toast.makeText(Calificar.this, "Datos enviados!",
                        Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(Calificar.this, "No hay conexion!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
