package com.ce.datosi.clientapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.LinkedList;

import Comunicacion.Contenedor;
import Menu.Platillo;

public class MenuPlatillos extends AppCompatActivity {

    int indice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_platillos);


        final LinkedList<Platillo> listaPlatillos = new LinkedList<Platillo>() ;
        final String[] platillos = new String[listaPlatillos.size()];

        // si no funciona, se cambia el tamaño de la lista a uno por default por ejemplo 30
        //se crea un for que itere sobre el array y agregue todos los campos de string vacio
        //suposicion de que existe una lista de platillos tipo LinkedList Java
        for(int i = 0; i < listaPlatillos.size(); i++){
            platillos[i] = listaPlatillos.get(i).getNombre();
        }

        ListView platillosList = (ListView)findViewById(R.id.lvplatillos);





























        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, platillos);
        platillosList.setAdapter(adapter);

        final TextView calorias = (TextView)findViewById(R.id.nutriInfor);
        final TextView tiempo = (TextView)findViewById(R.id.tiempoInfor);
        final TextView precio = (TextView)findViewById(R.id.precioInfor);
        final TextView datosComp = (TextView)findViewById(R.id.datosCompInfor);

        Button btnPedir = (Button)findViewById(R.id.ordenar);
        Button btnRegresar = (Button)findViewById(R.id.regresar);

        platillosList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {

                //modificación de los texview para que muestren la información del platillo seleccionado
                indice = posicion;
                calorias.setText(listaPlatillos.get(indice).getInformacionNutricional());
                tiempo.setText(listaPlatillos.get(indice).getTiempoDePreparacion());
                precio.setText(listaPlatillos.get(indice).getPrecio());
                datosComp.setText(listaPlatillos.get(indice).getDatoExtra());
            }
        });

        btnPedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(indice >= 0){
                    //aqui!!!!!!!

                    // en teoria deberia obtener el platillo de la lista de platillos
                    Platillo pedido = listaPlatillos.get(indice);
                    //implementar creacion de nueva orden suponiendo que existe una clase orden donde se agrega el cliente y el platillo

                    Intent intent = new Intent(MenuPlatillos.this, MenuPrincipal.class);
                    //Implementar pedido de la orden o

                    AlertDialog.Builder builder = new AlertDialog.Builder(MenuPlatillos.this);
                    builder.setTitle("¡Listo!");
                    builder.setMessage("Se ha realizado el pedido");
                    builder.setPositiveButton("OK",null);
                    builder.create();
                    builder.show();

                    startActivity(intent);
                    finish();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuPlatillos.this);
                builder.setTitle("¡Atención!");
                builder.setMessage("Seleccione un platillo");
                builder.setPositiveButton("OK",null);
                builder.create();
                builder.show();
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPlatillos.this, MenuPrincipal.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
