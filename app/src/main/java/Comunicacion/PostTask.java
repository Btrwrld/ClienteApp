package Comunicacion;

import android.os.AsyncTask;

import java.io.IOException;

/**
 * Created by erick on 10/29/2016.
 */

public class PostTask extends AsyncTask<String, String, String> {
    @Override
    protected String doInBackground(String... data) {
        /*// Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://<ip address>:3000");

        try {
            //add data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            nameValuePairs.add(new BasicNameValuePair("data", data[0]));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            //execute http post
            HttpResponse response = httpclient.execute(httppost);

        } catch (ClientProtocolException e) {

        } catch (IOException e) {

        }*/
        return "fdf";
    }
}