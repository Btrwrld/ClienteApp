package com.ce.datosi.clientapp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
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
        barraCalificaciones = (RatingBar) findViewById(R.id.rbcalificacion);
        calificar = (Button) findViewById(R.id.btnenviarCalificacion);
        comentarios = (EditText)findViewById(R.id.txtcomentarios);

        barraCalificaciones.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                calificacion = Integer.parseInt(String.valueOf(rating));
            }
        });

        calificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //AQUI VA EL POST DE LA CALIFICACION
                Toast.makeText(Calificar.this, "¡Datos enviados con exito! ¡Datos enviados con exito!",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Calificar.this, MenuPrincipal.class);
                startActivity(intent);
                finish();

            }

        });

    }
}
