package com.cranaya.ligaespaola.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cranaya.ligaespaola.database.LigaAppContract.TeamColumn;

public class LigaAppDataBase extends SQLiteOpenHelper {
    private static final String TAG = LigaAppDataBase.class.getSimpleName();

    private static SQLiteDatabase myWritableDb;
    private static Context mContext;

    public static final String DATABASE_NAME = "LigaEspaña.db";
    private static final int DATABASE_VERSION = 1;
    private static LigaAppDataBase mInstance = null;

    public interface Tables {
        String TEAM = "Team";

    }

    private LigaAppDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    public static LigaAppDataBase getInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new LigaAppDataBase(ctx);
        }
        mContext = ctx;
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /** Creando las tablas de la base de datos **/

        db.execSQL("CREATE TABLE IF NOT EXISTS " + Tables.TEAM + " ("
                + TeamColumn.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TeamColumn.ID_TEAM + " TEXT NOT NULL, "
                + TeamColumn.NAME_TEAM + " TEXT, "
                + TeamColumn.NAME_STADIUM_TEAM + " TEXT, "
                + TeamColumn.BADGE_TEAM + " TEXT, "
                + TeamColumn.DESCRIPTION_TEAM + " TEXT, "
                + TeamColumn.FOUNDATION_YEAR_TEAM + " TEXT, "
                + TeamColumn.JERSEY_TEAM + " TEXT, "
                + TeamColumn.WEBSITE_TEAM + " TEXT, "
                + TeamColumn.FACEBOOK_TEAM + " TEXT, "
                + TeamColumn.TWITTER_TEAM + " TEXT, "
                + TeamColumn.INSTAGRAM_TEAM + " TEXT, "
                + TeamColumn.FECHA_SISTEMA + " INTEGER)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Retorna una instancia de escritura hacia la base de datos con el fin
     * de no estar abriendo objetos SQLiteDatabase de forma simultanea.
     */
    public SQLiteDatabase getMyWritableDatabase() {
        if ((myWritableDb == null) || (!myWritableDb.isOpen())) {
            try {
                myWritableDb = mInstance.getWritableDatabase();
            } catch (Exception e) {
                Log.d(TAG, "getMyWritableDatabase: "+e.getMessage());
            }
        }

        return myWritableDb;
    }

    @Override
    public synchronized void close() {
        super.close();

        if (myWritableDb != null) {
            myWritableDb.close();
            myWritableDb = null;
        }
    }
}
