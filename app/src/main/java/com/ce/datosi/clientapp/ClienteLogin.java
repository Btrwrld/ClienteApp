package com.ce.datosi.clientapp;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

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

                if (Comunicador.verificarConexion(getApplicationContext())){

                    Cliente nuevoRegistro = new Cliente();

                    nuevoRegistro.setMesa(numMesa);
                    nuevoRegistro.setContrasena((contrasena.getText()).toString());
                    nuevoRegistro.setNombre((nombre.getText()).toString());

                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
                    final String datosAEnviar = gson.toJson(nuevoRegistro);




                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    String URL = "http://posttestserver.com/post.php";


                        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.i("VOLLEY", response);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("VOLLEY", error.toString());
                            }
                        }) {
                            @Override
                            public String getBodyContentType() {
                                return  String.format("application/json; charset=utf-8");
                            }

                            @Override
                            public byte[] getBody() throws AuthFailureError {
                                try {
                                    return datosAEnviar == null ? null : datosAEnviar.getBytes("utf-8");
                                } catch (UnsupportedEncodingException uee) {
                                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", datosAEnviar, "utf-8");
                                    return null;
                                }
                            }

                            @Override
                            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                                String responseString = "";
                                if (response != null) {
                                    responseString = String.valueOf(response.statusCode);
                                    // can get more details such as response.headers
                                }
                                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                            }
                        };

                        requestQueue.add(stringRequest);

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
}


