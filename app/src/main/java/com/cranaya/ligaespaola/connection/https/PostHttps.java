package com.cranaya.ligaespaola.connection.https;

import com.cranaya.ligaespaola.utils.HttpsTrustManager;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

public class PostHttps {
    private static final String TAG = PostHttps.class.getSimpleName();


    private static HttpsURLConnection postHttpsURLConnection(String urlNombre, boolean output, boolean input, int contentLength, int http_requet_time_out) throws Exception {
        URL url = new URL(urlNombre);

        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        int contador=0;

        urlConnection.setDoOutput(output);
        urlConnection.setDoInput(input);

        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        urlConnection.setRequestProperty("Accept", "application/json");
        urlConnection.setRequestProperty("Content-Length", "" + Integer.toString(contentLength));
        urlConnection.setUseCaches(false);
        urlConnection.setConnectTimeout(http_requet_time_out);
        urlConnection.setReadTimeout(http_requet_time_out);

        urlConnection.setSSLSocketFactory((SSLSocketFactory) SSLSocketFactory.getDefault());

        HttpsTrustManager.allowAllSSL();
            /*if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_SERVER_ERROR || urlConnection.getResponseCode() == HttpURLConnection.HTTP_INTERNAL_ERROR)
                contador++;*/


        return urlConnection;
    }
}
