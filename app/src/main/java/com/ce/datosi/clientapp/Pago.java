package com.ce.datosi.clientapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.LinkedList;

import Comunicacion.Comunicador;

public class Pago extends AppCompatActivity {

    Spinner cantidadPersonas;
    Button pagar;
    TextView total;
    int totalAPagar = 1000;
    int numero = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);

        cantidadPersonas = (Spinner)(findViewById(R.id.spcantidadDePersonas));
        pagar = (Button)(findViewById(R.id.btnpagar));
        total = (TextView)(findViewById(R.id.lbltotalAPagar));



        //HACER GET DEL TOTAL A PAGAR




        LinkedList<Integer> personas = new LinkedList<Integer>();
        personas.addLast(1);
        personas.addLast(2);
        personas.addLast(3);
        personas.addLast(4);
        personas.addLast(5);
        personas.addLast(6);
        personas.addLast(7);
        personas.addLast(8);
        personas.addLast(9);
        personas.addLast(10);

        //Creamos el adaptador
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, personas);
        //Añadimos el layout para el menú
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Le indicamos al spinner el adaptador a usar
        cantidadPersonas.setAdapter(adapter);

        cantidadPersonas.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {
                        numero = Integer.parseInt((parent.getItemAtPosition(position)).toString());
                        total.setText("$" + (totalAPagar / numero));
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

        pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pago.this, "¡Pago procesado! Su pago fue cobrado por: $" + (totalAPagar / numero) +" . Muchas gracias por su visita.",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Pago.this, MenuPrincipal.class);
                startActivity(intent);
                finish();

            }
        });
    }


    private class EnviarPago extends AsyncTask<LinkedList<Object>, Void, Boolean> {


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
                Toast.makeText(Pago.this, "Datos enviados!",
                        Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(Pago.this, "No hay conexion!",
                    Toast.LENGTH_SHORT).show();
        }
    }


    private class ObtenerDeuda extends AsyncTask<Void, Void, Boolean> {


        @Override
        protected Boolean doInBackground(Void... params) {
            boolean retorno;

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();


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
                Toast.makeText(Pago.this, "Datos enviados!",
                        Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(Pago.this, "No hay conexion!",
                    Toast.LENGTH_SHORT).show();
        }
    }

}
