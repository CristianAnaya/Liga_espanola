package com.cranaya.ligaespaola.connection;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cranaya.ligaespaola.LigaApp;
import com.cranaya.ligaespaola.connection.https.GetHttps;
import com.cranaya.ligaespaola.model.ResponseEndPointBean;
import com.cranaya.ligaespaola.utils.HttpsTrustManager;
import com.cranaya.ligaespaola.utils.JsonData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cranaya.ligaespaola.connection.https.GetHttps.getHttpsURLConnection;

public class NetworkUtilities {
    private static final String TAG = NetworkUtilities.class.getSimpleName();

    public static final int HTTP_REQUEST_TIMEOUT_MS = 30 * 1000;
    private static Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy h:mm:ss a").disableHtmlEscaping().serializeNulls().create();


    private static String convertStreamToString(java.io.InputStream is) {
        try {
            if (is != null) {
                return new java.util.Scanner(is).useDelimiter("\\A").next();
            } else {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }

    public static void getEquiposEspañoles() {

        ResponseEndPointBean responseEndPointBean = null;
        HttpURLConnection urlConnection = null;

        String url = "https://www.thesportsdb.com/api/v1/json/2/search_all_teams.php?s=Soccer&c=Spain";
        try {
            urlConnection = getHttpsURLConnection(url, true, HTTP_REQUEST_TIMEOUT_MS);


            urlConnection.connect();

            // Receive Response from server
            int statusCode = urlConnection.getResponseCode();

            /* 200 represents HTTP OK */
            if (statusCode == HttpURLConnection.HTTP_OK) {
                StringBuilder sb = new StringBuilder();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                String line;
                sb.setLength(0);
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                JSONObject jsonObjectResultado = new JSONObject(sb.toString());

                responseEndPointBean = gson.fromJson(jsonObjectResultado.toString(), ResponseEndPointBean.class);

                if (responseEndPointBean != null && responseEndPointBean.isSuccess()) {
                   // Log.d(TAG, "getEquiposEspañoles: "+responseEndPointBean.ge);
                }
            } else {
                String mensaje = convertStreamToString(urlConnection.getErrorStream());
                Log.e(TAG, mensaje);

                responseEndPointBean = new ResponseEndPointBean();
                responseEndPointBean.setSuccess(false);
                responseEndPointBean.setMessage(mensaje);
            }
        } catch (Exception e) {
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

    }

    public static void getTeam(){
        RequestQueue mQueue = null;

        HttpsTrustManager.allowAllSSL();
        mQueue = Volley.newRequestQueue(LigaApp.getContext());

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://www.thesportsdb.com/api/v1/json/2/search_all_teams.php?s=Soccer&c=Spain", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ResponseEndPointBean responseEndPointBean = null;

                try {
                    JSONObject jsonObjectResultado = null;
                    jsonObjectResultado = new JSONObject(response.toString());

                    JsonData.jsonEquipoFutbol(jsonObjectResultado);
                    Log.d(TAG, "getFormPreTrabajoOperador: "+response.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "error: "+error.getMessage());

            }
        });

        mQueue.add(jsonObjectRequest);
    }
}
