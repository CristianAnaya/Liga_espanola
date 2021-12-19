package com.cranaya.ligaespaola.connection.https;

import com.cranaya.ligaespaola.connection.NetworkUtilities;
import com.cranaya.ligaespaola.utils.HttpsTrustManager;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

public class GetHttps {
    private static final String TAG = GetHttps.class.getSimpleName();

    public static HttpsURLConnection getHttpsURLConnection(String urlNombre, boolean input, int http_requet_time_out) throws Exception {
        URL url = new URL(urlNombre);

        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

        urlConnection.setDoInput(input);
        urlConnection.setRequestMethod("GET");
        //urlConnection.setRequestProperty("token", myPreferencia.getToken());
        urlConnection.setConnectTimeout(http_requet_time_out);
        urlConnection.setReadTimeout(http_requet_time_out);

        urlConnection.setSSLSocketFactory((SSLSocketFactory) SSLSocketFactory.getDefault());

        HttpsTrustManager.allowAllSSL();

        return urlConnection;
    }

}
