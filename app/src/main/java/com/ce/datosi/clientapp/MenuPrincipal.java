package com.ce.datosi.clientapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.LinkedList;

import Comunicacion.Contenedor;
import Hillos.BarraProgreso;
import Hillos.Chat;
import Menu.Platillo;


public class MenuPrincipal extends AppCompatActivity {

    //RECONOCIMIENTO DE VOZ
    private static final int RECOGNIZE_SPEECH_ACTIVITY = 1;

    //CHAT
    private EditText mensaje;
    private Button enviar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        TextView nombreUsuario = (TextView) findViewById(R.id.txtNombreUsuario);
        nombreUsuario.setText((String) getIntent().getExtras().getSerializable("NombreUsuario"));

        mensaje = (EditText) findViewById(R.id.txtmsjChat);
        enviar = (Button) findViewById(R.id.btnenviar);

        LinkedList<TextView> entradas = new LinkedList<TextView>();

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
        entradas.addFirst((TextView) findViewById(R.id.msj1));
        /*
        Chat chat = new Chat();
        chat.setEntradas(entradas);
        chat.run();

        BarraProgreso barraProgreso = new BarraProgreso();
        barraProgreso.setProgressBar((ProgressBar) (findViewById(R.id.pbProgresoOrden)));
        barraProgreso.run();*/

        Button ordenar = (Button) (findViewById(R.id.btnordenar));

        ordenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuPrincipal.this, MenuPlatillos.class);
                startActivity(intent);
                onPause();
            }

        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Hacer un post y get del comentario

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
        LinkedList<Platillo> listaPlatillos = Contenedor.getPlatillos();
        Platillo p1 = new Platillo();
        p1.setNombre("Hamburguesa");
        listaPlatillos.addFirst(p1);
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
}