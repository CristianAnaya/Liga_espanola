package com.cranaya.ligaespaola.base;

import android.accounts.AccountManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.cranaya.ligaespaola.LigaApp;
import com.cranaya.ligaespaola.utils.Parametro;
import com.cranaya.ligaespaola.utils.Utils;

public class BaseActionBarActivity extends AppCompatActivity {
    public static String TAG;


    public static final int ACTUALIZAR_LIGA = 723;
    private ProgressReceiver rcv;
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        LigaApp.setCurrentActivity(this);
        TAG = mContext.getClass().getSimpleName();

        IntentFilter filter = new IntentFilter();
        filter.addAction(Parametro.SERVICE_RESULT);
        rcv = new ProgressReceiver();
        registerReceiver(rcv,filter);

    }

    public class ProgressReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (intent != null && intent.getExtras() != null) {
                    int caso = intent.getExtras().getInt(Parametro.CASE_BROADCAST);
                    if (caso == ACTUALIZAR_LIGA) {
                        onActualizarLiga();
                    }
                }
            } catch (Exception e) {
                Log.d(TAG, "onReceive: "+e.getMessage());
            }
        }
    }

    public void onActualizarLiga() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TAG = mContext.getClass().getSimpleName();

        LigaApp.setCurrentActivity(this);

        super.onActivityResult(requestCode, resultCode, data);
    }
}
