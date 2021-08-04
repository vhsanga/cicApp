package com.nodoclic.cic.servicios;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.nodoclic.cic.clases.RsLogin;
import com.nodoclic.cic.clases.RsMenu;
import com.nodoclic.cic.util.CONST;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Servicio {

    public static RsLogin _login(String usuario, String pass, Context context){
        RsLogin resultado= new RsLogin();
        String subAddress = "/login";
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(CONST.API_URL+subAddress);

        try {
            //Añade las variables a enviar por post
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("usuario", usuario);
            jsonParam.put("pass", pass);
            String jsonString = jsonParam.toString();

            httppost.setHeader("Accept", "application/json");
            httppost.setHeader("Content-type", "application/json");
            httppost.setEntity(new StringEntity(jsonString));

            //Hace la petición
            HttpResponse response = httpclient.execute(httppost);

            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            String json = reader.readLine();
            Log.d("res:", json);
            try {
                JSONObject jsonObject = new JSONObject(json);
                Toast.makeText(context, jsonObject.getJSONObject("message").getString("description"), Toast.LENGTH_LONG).show();
                resultado.setType(jsonObject.getJSONObject("message").getString("type"));
                resultado.setDescripcion(jsonObject.getJSONObject("message").getString("description"));
                if(jsonObject.getJSONObject("message").getString("type").equals(CONST.OK_RESPONSE)){
                    JSONObject jsonData =  jsonObject.getJSONArray("data").getJSONObject(0);
                    resultado.setId(jsonData.getInt("id"));
                    resultado.setIdCliente(jsonData.getInt("idcliente"));
                    resultado.setUsuario(jsonData.getString("usuario"));
                    resultado.setEstado(jsonData.getInt("estado"));
                    resultado.setNombre(jsonData.getString("nombre"));
                    resultado.setApellido(jsonData.getString("apellido"));
                    resultado.setDireccion(jsonData.getString("direccion"));
                    resultado.setTelefono(jsonData.getString("telefono"));
                    resultado.setCorreo(jsonData.getString("correo"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, "Error:Respuesta incorrecta del servidor", Toast.LENGTH_LONG).show();
            }
        }
        catch (ClientProtocolException e) {
           e.printStackTrace();
            Toast.makeText(context, "Error: "+e.getMessage(), Toast.LENGTH_LONG).show();
        }
        catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error: "+e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error: "+e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return resultado;
    }

    public static List<RsMenu> _menuDia(Context context){
        List<RsMenu> menu = new ArrayList<>();
        String subAddress = "/menu";
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpReq = new HttpGet(CONST.API_URL+subAddress);
        try {
            httpReq.setHeader("Accept", "application/json");
            httpReq.setHeader("Content-type", "application/json");
            //Hace la petición
            Log.d("res menu:", "PRobando .... . ");

            HttpResponse response = httpclient.execute(httpReq);

            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            String json = reader.readLine();
            Log.d("res menu:", json);
            try {
                JSONObject jsonObject = new JSONObject(json);
                //Toast.makeText(context, ">>>>"+jsonObject.getJSONObject("message").getString("description"), Toast.LENGTH_LONG).show();
                if(jsonObject.getJSONObject("message").getString("type").equals(CONST.OK_RESPONSE)){
                    JSONArray jsonData = new JSONArray(jsonObject.getString("data"));
                    for (int i = 0 ; i < jsonData.length(); i++) {
                        JSONObject obj = jsonData.getJSONObject(i);
                        menu.add(new RsMenu(
                                obj.getInt("id"),
                                obj.getInt("tipo"),
                                obj.getString("nombre"),
                                obj.getString("fecha"),
                                obj.getInt("estado"),
                                obj.getInt("cantidadinicial"),
                                obj.getInt("cantidadactual"),
                                obj.getInt("comida")
                                ) );
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, "Error:Respuesta incorrecta del servidor", Toast.LENGTH_LONG).show();
            }
        }
        catch (ClientProtocolException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error: "+e.getMessage(), Toast.LENGTH_LONG).show();
        }
        catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error: "+e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return menu;
    }


}
