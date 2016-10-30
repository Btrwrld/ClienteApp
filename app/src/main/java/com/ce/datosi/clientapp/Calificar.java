package com.ce.datosi.clientapp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Calificar extends AppCompatActivity {

    Button calificar;
    RatingBar barraCalificaciones;
    EditText comentarios;
    int calificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificar);
        addListenerOnRatingBar();
        addListenerOnButton();

        barraCalificaciones = (RatingBar) findViewById(R.id.rbcalificacion);
        calificar = (Button) findViewById(R.id.btncalificar);
        comentarios = (EditText)findViewById(R.id.txtcomentarios);
    }

    public void addListenerOnRatingBar() {

        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        barraCalificaciones.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                calificacion = Integer.parseInt(String.valueOf(rating));
            }
        });
    }

    public void addListenerOnButton() {

        //if click on me, then display the current rating value.
        calificar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //AQUI VA EL POST DE LA CALIFICACION

                AlertDialog.Builder cuadroAlerta = new AlertDialog.Builder(getApplicationContext());
                cuadroAlerta.setTitle("¡Datos enviados con exito!");
                cuadroAlerta.setMessage("Muchas gracias por su opinion.");
                cuadroAlerta.setPositiveButton("OK", null);
                cuadroAlerta.create();
                cuadroAlerta.show();

                Intent intent = new Intent(Calificar.this, MenuPrincipal.class);
                startActivity(intent);
                finish();

            }

        });

    }
}
