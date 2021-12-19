package com.cranaya.ligaespaola.dao;

import android.database.Cursor;

import java.util.ArrayList;

public interface GenericActionDao<T> {

    boolean existe(String tabla, String column, String dato);

    boolean existe(String tabla, String column, long dato);

    T toEntity(Cursor cursor);

    ArrayList<T> toListOfEntities(Cursor cursor);
}