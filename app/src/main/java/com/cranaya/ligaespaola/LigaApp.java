package com.cranaya.ligaespaola;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.provider.Settings;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;


public class LigaApp extends MultiDexApplication {
    private static final String TAG = LigaApp.class.getSimpleName();

    private static LigaApp instance;
    private static Activity mCurrentActivity = null;

    public LigaApp() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }

    /**
     * Obtiene el contexto de la aplicacion
     *
     * @return el contexto de la aplicacion
     */
    public static Context getContext() {
        return instance.getApplicationContext();
    }

    /**
     * Optiene la carpeta donde se almacena los datos de la aplicacion
     *
     * @return La ruta de la carpeta de la aplicacion
     */
    public static String getAppDirectory() {
        String appDirectory = "";
        try {
            appDirectory = instance.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(instance.getPackageName(), 0)
                    .applicationInfo.dataDir;
        } catch (NameNotFoundException e) {

        }
        return appDirectory;
    }

    /**
     * Obtiene el identificador del dispositivo
     *
     * @return ID del dispositivo actual
     */
    public static String getDeviceId() {
        return Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * Obtiene la version del codigo de la aplicacion
     *
     * @return numero entero que representa la version de la aplicacion
     */
    public static int getVersionCode(Context mContext) throws NameNotFoundException {
        return mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionCode;
    }

    public static String getVersionName(Context mContext) throws NameNotFoundException {
        return mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName;
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.toLowerCase().startsWith(manufacturer.toLowerCase())) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }


    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

    public static Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    public static void setCurrentActivity(Activity mCurrentActivity) {
        LigaApp.mCurrentActivity = mCurrentActivity;
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


}