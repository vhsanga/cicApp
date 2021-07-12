package com.nodoclic.cic.servicios;

import android.content.Context;
import android.util.JsonReader;
import android.util.Log;
import android.widget.Toast;

import com.nodoclic.cic.clases.RsLogin;
import com.nodoclic.cic.util.CONST;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Servicio {

    public static RsLogin _login(Context context){
        Log.d("login:", "iniciando proceso  lkogin");
        String subAddress = "/login";
        Toast.makeText(context, "0:init login", Toast.LENGTH_SHORT).show();
        try {
            HttpClient client = new DefaultHttpClient ();
            HttpPost post = new HttpPost (CONST.API_URL+subAddress);
        }

        /*try {
            URL urlEndPoint = new URL(CONST.API_URL+subAddress);
            HttpsURLConnection myConnection =
                    (HttpsURLConnection) urlEndPoint.openConnection();
            myConnection.setRequestMethod("POST");
            myConnection.setRequestProperty("Content-Type", "application/json; utf-8");
            myConnection.setRequestProperty("Accept", "application/json");

            String jsonInputString = "{\"usuario\": \"jose\", \"pass\": \"123456\"}";
            try(OutputStream os = myConnection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            Log.d("Repsuesta cod:", String.valueOf(myConnection.getResponseCode()) );
            if (myConnection.getResponseCode() == 200) {
                InputStream responseBody = myConnection.getInputStream();
                InputStreamReader responseBodyReader =
                        new InputStreamReader(responseBody, "UTF-8");
                String response = responseBodyReader.toString();
                Log.d("Repsuesta:", response);
            } else {
                // Error handling code goes here
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Toast.makeText(context, "2:"+e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(context, "3:"+e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }*/
        return null;
    }

}
