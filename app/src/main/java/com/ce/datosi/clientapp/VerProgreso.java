package com.ce.datosi.clientapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import Comunicacion.Contenedor;

public class VerProgreso extends AppCompatActivity {

    private ProgressBar barraProgreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_progreso);

        barraProgreso = (ProgressBar) (findViewById(R.id.pbProgresoOrden));

        BarraProgreso progresar = new BarraProgreso();
        progresar.execute();
    }





    private void tareaLarga()
    {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {}
    }

    private class BarraProgreso extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            int mProgressStatus = 0;
            double numPasos = Contenedor.getPasos().size();
            double numPasosActuales = Contenedor.getPasos().size();

            if (numPasos == 0){
                isCancelled();
                return false;
            }
            while (mProgressStatus < 100) {
                
                //mProgressStatus = 100 - (100 * ((Contenedor.getPasos().size()) / numPasos));
                mProgressStatus =(int)(100 - (100 * (numPasosActuales / numPasos)));
                tareaLarga();
                publishProgress(mProgressStatus);

                numPasosActuales = Contenedor.getPasos().size();

            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0].intValue();

            barraProgreso.setProgress(progreso);
            ((TextView)findViewById(R.id.lblProgreso)).setText(""+ progreso +"");
        }

        @Override
        protected void onPreExecute() {
            barraProgreso.setMax(100);
            barraProgreso.setProgress(0);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
                Toast.makeText(VerProgreso.this, "Orden finalizada!",
                        Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(VerProgreso.this, "Usted no ha realizado ninguna orden",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
