package Comunicacion;

import android.content.Context;
import android.net.*;
import android.os.StrictMode;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


/**
 * Created by erick on 10/26/2016.
 */
public class Comunicador {

    public static InputStream GET(String direccionURL) throws IOException {
        URL url = new URL(direccionURL);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStream in;
        try {
            in = new BufferedInputStream(urlConnection.getInputStream());}
        finally {
            urlConnection.disconnect();
        }
        return in;
    }

    public static void POST(String direccionURL, String JSON) throws IOException {

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        String urlParameters  = "param1=" + URLEncoder.encode(JSON, "UTF-8");
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8 );
        URL url = new URL(direccionURL);
        HttpURLConnection conn= (HttpURLConnection) url.openConnection();

        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postData.length));
        conn.setUseCaches(false);

        try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
            wr.write( postData );
        }

    }

    public static boolean verificarConexion(Context contexto) {

        ConnectivityManager connMgr = (ConnectivityManager)
                contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo!=null&&networkInfo.isConnected()){
            return true;
        }

        else{
            return false;
        }
    }

}
