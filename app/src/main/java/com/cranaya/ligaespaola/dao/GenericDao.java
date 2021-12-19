package com.cranaya.ligaespaola.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.cranaya.ligaespaola.database.LigaAppContract;
import com.cranaya.ligaespaola.database.LigaAppDataBase;

import java.util.ArrayList;

public abstract class GenericDao<T> implements GenericActionDao {
	protected static String TAG;

	protected  final int DEBUG = 1;
	protected  final int INFO = 2;
	protected  final int ERROR = 3;

	protected LigaAppDataBase dbProvider;
	protected SQLiteDatabase ourDatabase;
	protected Context mContext;


	protected GenericDao(Context ctx) {
		dbProvider = LigaAppDataBase.getInstance(ctx);
		ourDatabase = dbProvider.getMyWritableDatabase();
		mContext = ctx;

		TAG = ctx.getClass().getSimpleName();

	}

	public void beginTransaction() {
		ourDatabase.beginTransaction();
	}

	public void beginTransactionNonExclusive() {
		ourDatabase.beginTransactionNonExclusive();
	}

	public void setTransactionSuccessful() {
		ourDatabase.setTransactionSuccessful();
	}

	public void endTransaction() {
		ourDatabase.endTransaction();
	}

	public boolean enableWriteAheadLogging() {
		return ourDatabase.enableWriteAheadLogging();
	}

	public void disableWriteAheadLogging() {
		ourDatabase.disableWriteAheadLogging();
	}

	public boolean isWriteAheadLoggingEnabled() {
		return ourDatabase.isWriteAheadLoggingEnabled();
	}

	public boolean isDbLockedByCurrentThread() {
		return ourDatabase.isDbLockedByCurrentThread();
	}

	public boolean inTransaction() {
		return ourDatabase.inTransaction();
	}

	protected void log(int nombreLog, Exception e) {
		switch (nombreLog) {
			case DEBUG:
				Log.d(TAG, e.toString(), e);
				break;
			case INFO:
				Log.i(TAG, e.toString(), e);
				break;
			case ERROR:
				Log.d(TAG, " "+e);
				break;
		}
	}



    @Override
    public boolean existe(String tabla, String column, String dato) {
        boolean existe = false;

        String sql = "select " + LigaAppContract.BaseColumn.ID + " from " + tabla + " where " + column + " = '" + dato + "'";

        Cursor cursor = ourDatabase.rawQuery(sql, null);

        if (cursor != null) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                long id = cursor.getInt(cursor.getColumnIndex(LigaAppContract.BaseColumn.ID));
                if (id > 0) {
                    existe = true;
                }
            }
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }
        return existe;
    }


	@Override
	public synchronized boolean existe(String tabla, String column, long dato) {
		boolean existe = false;

		String sql = "select " + LigaAppContract.BaseColumn.ID + " from " + tabla + " where " + column + " = ?";

		Cursor cursor = ourDatabase.rawQuery(sql, new String[] {String.valueOf(dato)});

		if (cursor != null) {
			for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
				long id = cursor.getInt(cursor.getColumnIndex(LigaAppContract.BaseColumn.ID));
				if (id > 0) {
					existe = true;
				}
			}
			if (!cursor.isClosed()) {
				cursor.close();
			}
		}
		return existe;
	}

	public void clearInstance() {

	}

}