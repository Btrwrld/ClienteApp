package com.ce.datosi.clientapp;

import android.content.Intent;
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

import java.util.LinkedList;

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

}
