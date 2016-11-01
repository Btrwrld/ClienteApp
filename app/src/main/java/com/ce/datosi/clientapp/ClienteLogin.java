package com.ce.datosi.clientapp;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;

import Comunicacion.Comunicador;
import Usuario.Cliente;


public class ClienteLogin extends AppCompatActivity implements View.OnClickListener {

    TextView mesa;
    Button escanear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_login);

        final EditText nombre = (EditText)findViewById(R.id.txtnombreUsuario);
        final EditText contrasena = (EditText)findViewById(R.id.txtContrasena);
        Button iniciar = (Button)findViewById(R.id.btnIniciar);
        escanear = (Button)findViewById(R.id.btnEscanear);
        mesa = (TextView)findViewById(R.id.lblMesa);
        escanear.setOnClickListener(this);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int numMesa = Integer.parseInt(mesa.getText().toString());
                TextView resultado = (TextView)findViewById(R.id.resultado);

                if (Comunicador.verificarConexion(getApplicationContext())){

                    Cliente nuevoRegistro = new Cliente();

                    nuevoRegistro.setMesa(numMesa);
                    nuevoRegistro.setContrasena((contrasena.getText()).toString());
                    nuevoRegistro.setNombre((nombre.getText()).toString());

                    EnviarUsuario envio = new EnviarUsuario();
                    envio.execute(nuevoRegistro);

                    Intent pasoCliente = new Intent(ClienteLogin.this, VistaChat.class);
                    pasoCliente.putExtra("NombreUsuario", (nombre.getText()).toString());

                    Intent intent = new Intent(ClienteLogin.this, MenuPrincipal.class);
                    intent.putExtra("NombreUsuario", (nombre.getText()).toString());

                    startActivity(intent);
                    finish();
                }
                else {
                    AlertDialog.Builder cuadroAlerta = new AlertDialog.Builder(getApplicationContext());
                    cuadroAlerta.setTitle("¡Atencion!");
                    cuadroAlerta.setMessage("Es necesaria una coneccion al servidor para el funcionamiento de la app");
                    cuadroAlerta.setPositiveButton("OK", null);
                    cuadroAlerta.create();
                    cuadroAlerta.show();
                }
            }
        });
    }

    public void onClick(View v) {

        if(v.getId()==R.id.btnEscanear){
            //Se instancia un objeto de la clase IntentIntegrator
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            //Se procede con el proceso de scaneo
            scanIntegrator.initiateScan();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //Se obtiene el resultado del proceso de scaneo y se parsea
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            //Quiere decir que se obtuvo resultado pro lo tanto:
            //Desplegamos en pantalla el contenido del código de barra scaneado
            String scanContent = scanningResult.getContents();
            mesa.setText(scanContent);
        }else{
            //Quiere decir que NO se obtuvo resultado
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No se ha recibido datos del scaneo!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }





    private class EnviarUsuario extends AsyncTask<Cliente, Void, Boolean> {


        @Override
        protected Boolean doInBackground(Cliente... params) {
            Cliente clienteNuevo = params[0];

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            String datosAEnviar = gson.toJson(clienteNuevo);

            if (Comunicador.verificarConexion(getApplicationContext())){
                try {
                    Comunicador.POST("http://192.168.100.22:8080/WebServer/rest/MainCliente/addClienteCuenta", datosAEnviar);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
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
                Toast.makeText(ClienteLogin.this, "Inicio de sesision terminado!",
                        Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(ClienteLogin.this, "No hay conexion!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}


