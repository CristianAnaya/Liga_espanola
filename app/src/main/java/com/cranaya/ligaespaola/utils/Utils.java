package com.cranaya.ligaespaola.utils;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    private static final String TAG = Utils.class.getSimpleName();

    @SuppressLint("SimpleDateFormat")
    public static String dateToString(Date fecha) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
        String cadenaFecha = df.format(fecha);

        return cadenaFecha;
    }
}
