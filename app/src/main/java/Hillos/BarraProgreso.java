package Hillos;

import android.os.Handler;
import android.widget.ProgressBar;

import Comunicacion.Contenedor;

/**
 * Created by erick on 10/29/2016.
 */

public class BarraProgreso implements Runnable {

    private ProgressBar mProgress;
    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();

    @Override
    public void run() {
        int numPasos = Contenedor.getPasos().size();
        while (true) {
            while (mProgressStatus < 100) {
                try {
                    mProgressStatus = 100 - (100 * ((Contenedor.getPasos().size()) / numPasos));
                }
                catch (ArithmeticException e){
                    mProgressStatus = 0;
                }
                // Update the progress bar
                mHandler.post(new Runnable() {
                    public void run() {
                        mProgress.setProgress(mProgressStatus);
                    }
                });
            }
        }
    }

    public void setProgressBar(ProgressBar progressBar){
        this.mProgress = progressBar;
    }
}
