package com.ce.datosi.clientapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.AsyncTask;
import android.speech.RecognizerIntent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import Comunicacion.Comunicador;
import Comunicacion.Contenedor;
import Menu.Platillo;

import static java.lang.Thread.sleep;


public class MenuPrincipal extends AppCompatActivity {

    //RECONOCIMIENTO DE VOZ
    private static final int RECOGNIZE_SPEECH_ACTIVITY = 1;

    //CHAT
    private Button chatear;

    private Button pagar;
    private Button calificar;
    private Button ordenar;

    public static Contenedor contenedor;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        TextView nombreUsuario = (TextView) findViewById(R.id.txtNombreUsuario);
        nombreUsuario.setText((String) getIntent().getExtras().getSerializable("NombreUsuario"));

        chatear = (Button) findViewById(R.id.btnChatear);
        pagar = (Button) findViewById(R.id.btnpagar);
        calificar = (Button) findViewById(R.id.btnenviarCalificacion);
        ordenar = (Button) (findViewById(R.id.btnordenar));



        ordenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuPrincipal.this, MenuPlatillos.class);
                startActivity(intent);
                onPause();
            }

        });


        calificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuPrincipal.this, Calificar.class);
                startActivity(intent);
                onPause();

            }

        });
        chatear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuPrincipal.this, VistaChat.class);
                startActivity(intent);
                onPause();

            }

        });

        pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuPrincipal.this, Pago.class);
                startActivity(intent);
                onPause();

            }

        });
    }


    //RECONOCIMIENTO DE VOZ
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RECOGNIZE_SPEECH_ACTIVITY:

                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> speech = data
                            .getStringArrayListExtra(RecognizerIntent.
                                    EXTRA_RESULTS);
                    String orden = speech.get(0);

                    LinkedList<Platillo> platillosPedidos = reconocerPlatillo(orden);

                    if(platillosPedidos.size() > 0){
                        //HACER POST CON LAS ORDENES

                        String oracionPlatillos = "";
                        if(platillosPedidos.size() > 1){
                            oracionPlatillos += platillosPedidos.get(0).getNombre();
                            for(int i = 1; i < platillosPedidos.size() - 1; i++){
                                oracionPlatillos += ", " + platillosPedidos.get(i).getNombre();
                            }
                            oracionPlatillos += " y " + platillosPedidos.get(platillosPedidos.size() - 1).getNombre() + ".";}
                        else{
                            oracionPlatillos += platillosPedidos.get(0).getNombre() + ".";
                        }



                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MenuPrincipal.this);
                        builder.setTitle("¡Orden realizada con exito!");
                        builder.setMessage("Usted ha ordenado:" + oracionPlatillos);
                        builder.setPositiveButton("OK",null);
                        builder.create();
                        builder.show();
                    }
                }

                break;
            default:

                break;
        }
    }

    public void onClickImgBtnHablar(View v) {

        Intent intentActionRecognizeSpeech = new Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        // Configura el Lenguaje (Español-México)
        intentActionRecognizeSpeech.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-MX");
        try {
            startActivityForResult(intentActionRecognizeSpeech, RECOGNIZE_SPEECH_ACTIVITY);

        } catch (ActivityNotFoundException a) {
            AlertDialog.Builder cuadroAlerta = new AlertDialog.Builder(getApplicationContext());
            cuadroAlerta.setTitle("¡Disculpas!");
            cuadroAlerta.setMessage("No fue posible reconocer su orden, por favor intente de nuevo");
            cuadroAlerta.setPositiveButton("OK", null);
            cuadroAlerta.create();
            cuadroAlerta.show();
        }

    }

    private LinkedList<Platillo> reconocerPlatillo(String entradaVoz) {

        final String url = "http://192.168.100.22:8080/WebServer/rest/MainCliente/getMenu";
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest get = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                contenedor = gson.fromJson(response, Contenedor.class);

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("Field", "Value"); //Add the data you'd like to send to the server.
                return MyData;
            }
        };
        // add it to the RequestQueue
        queue.add(get);

        LinkedList<Platillo> listaPlatillos = contenedor.getListaPlatillos();
        LinkedList<Platillo> platillosPedidos = new LinkedList<>();

        String[] palabra = entradaVoz.split(" ");
        for (int i = 0; i < listaPlatillos.size(); i++) {
            for (int j = 0; j < palabra.length; j++) {
                if (palabra[j].compareToIgnoreCase(listaPlatillos.get(i).getNombre()) == 0) {
                    platillosPedidos.addFirst(listaPlatillos.get(i));
                }
            }

        }

        return platillosPedidos;

    }

















    private class RealizarOrden extends AsyncTask<LinkedList<Platillo>, Void, Boolean> {


        @Override
        protected Boolean doInBackground(LinkedList<Platillo>... params) {
            LinkedList<Platillo> platillosOrdenados = params[0];

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            String datosAEnviar;

            if (Comunicador.verificarConexion(getApplicationContext())) {
                for (int i = 0; i < platillosOrdenados.size(); i++) {
                    datosAEnviar = gson.toJson(platillosOrdenados.get(i));


                    //AGREGAR DIRECCION CORRECTA
                    try {
                        Comunicador.POST("http://192.168.100.22:8080/WebServer/rest/MainCliente/addClienteCuenta", datosAEnviar);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            } else {
                isCancelled();
            }

            return true;
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
                Toast.makeText(MenuPrincipal.this, "Orden realizada con exito!",
                        Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(MenuPrincipal.this, "No hay conexion!",
                    Toast.LENGTH_SHORT).show();
        }
    }






}