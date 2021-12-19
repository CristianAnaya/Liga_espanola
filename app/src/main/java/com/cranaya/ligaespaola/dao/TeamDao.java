package com.cranaya.ligaespaola.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.cranaya.ligaespaola.database.LigaAppDataBase;
import com.cranaya.ligaespaola.model.TeamBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import com.cranaya.ligaespaola.database.LigaAppContract.TeamColumn;
import com.cranaya.ligaespaola.database.LigaAppDataBase.Tables;
import com.cranaya.ligaespaola.utils.Utils;
import com.cranaya.ligaespaola.viewModel.TeamViewModel;

public class TeamDao extends GenericDao {

    private static TeamDao mInstance = null;


    protected TeamDao(Context context) {
        super(context);

        mContext = context;

    }

    public static TeamDao getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new TeamDao(context.getApplicationContext());
        }
        return mInstance;
    }

    /**
     * Mapea los datos del agente al ContentValues
     * proporcionado
     *
     * @param teamBean El Agente logeado en la aplicacion con los datos que envio el servidor.
     * @return ContentValues con los datos del agente listos para enviar a la base de datos
     * encuentra
     */

    private ContentValues toContentValues(TeamBean teamBean) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(TeamColumn.ID_TEAM, teamBean.getId());
        contentValues.put(TeamColumn.NAME_TEAM, teamBean.getName());
        contentValues.put(TeamColumn.NAME_STADIUM_TEAM, teamBean.getStadium());
        contentValues.put(TeamColumn.BADGE_TEAM, teamBean.getBadge());
        contentValues.put(TeamColumn.DESCRIPTION_TEAM, teamBean.getDescription());
        contentValues.put(TeamColumn.FOUNDATION_YEAR_TEAM, teamBean.getFoundation());
        contentValues.put(TeamColumn.JERSEY_TEAM, teamBean.getJersey());
        contentValues.put(TeamColumn.WEBSITE_TEAM, teamBean.getWebSite());
        contentValues.put(TeamColumn.FACEBOOK_TEAM, teamBean.getFacebook());
        contentValues.put(TeamColumn.TWITTER_TEAM, teamBean.getTwitter());
        contentValues.put(TeamColumn.INSTAGRAM_TEAM, teamBean.getInstagram());
        contentValues.put(TeamColumn.FECHA_SISTEMA, Utils.dateToString(new Date()));

        return contentValues;
    }

    public String update(TeamBean teamBean) {
        String id = "";
        try {
            ContentValues contentValues = toContentValues(teamBean);
            if (ourDatabase.update(Tables.TEAM, contentValues, TeamColumn.ID_TEAM + " = ?", new String[]{teamBean.getId()}) > 0) {
                id = teamBean.getId();
            }
        } catch (Exception e) {
            Log.d(TAG, " "+e.getMessage());

        }
        return id;
    }

    public String insert(TeamBean teamBean) {
        String id = "";
        try {
            ContentValues contentValues = toContentValues(teamBean);
            if (ourDatabase.insert(Tables.TEAM, null, contentValues) > 0) {
                id = teamBean.getId();
            }
        } catch (Exception e) {
            Log.d(TAG, " "+e.getMessage());
        }
        return id;
    }

    public ArrayList<String> insertOrUpdate(TeamBean teamBean) {
        ArrayList<String> listaIdGestionados = new ArrayList<>();

        boolean existe = false;
            try {
                existe = existe(Tables.TEAM, TeamColumn.ID_TEAM, teamBean.getId());
                String idGestionado = " ";
                if (existe) {
                    idGestionado = update(teamBean);
                } else {
                    idGestionado = insert(teamBean);
                }

                if (!idGestionado.isEmpty()) {
                    listaIdGestionados.add(idGestionado);
                }
            } catch (Exception e) {
                Log.d(TAG, " "+e.getMessage());
            }


        return listaIdGestionados;
    }

    public ArrayList<TeamViewModel> getAll() {
        ArrayList<TeamViewModel> listaTeam= null;

        String sql = null;
        try {
            sql = "SELECT * FROM " + Tables.TEAM;
            Cursor cursor = ourDatabase.rawQuery(sql, null);
            listaTeam = toListOfEntities(cursor);
        } catch (Exception e) {
            log(ERROR, e);
        }
        return listaTeam;
    }

    @Override
    public TeamViewModel toEntity(Cursor cursor) {
        TeamViewModel teamBean = null;
        if (cursor != null) {
            ArrayList<TeamViewModel> lista = toListOfEntities(cursor);
            if (lista != null && lista.size() == 1) {
                teamBean = lista.get(0);
            }
        }
        return teamBean;
    }

    @Override
    public ArrayList<TeamViewModel> toListOfEntities(Cursor cursor){
        TeamViewModel teamBean;
        ArrayList<TeamViewModel> list = new ArrayList<>();

        if (cursor != null){
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
                teamBean = new TeamViewModel();

                teamBean.id = cursor.getString(cursor.getColumnIndex(TeamColumn.ID_TEAM));
                teamBean.name = cursor.getString(cursor.getColumnIndex(TeamColumn.NAME_TEAM));
                teamBean.badge = cursor.getString(cursor.getColumnIndex(TeamColumn.BADGE_TEAM));
                teamBean.stadium = cursor.getString(cursor.getColumnIndex(TeamColumn.NAME_STADIUM_TEAM));
                teamBean.description = cursor.getString(cursor.getColumnIndex(TeamColumn.DESCRIPTION_TEAM));
                teamBean.foundation = cursor.getString(cursor.getColumnIndex(TeamColumn.FOUNDATION_YEAR_TEAM));
                teamBean.jersey = cursor.getString(cursor.getColumnIndex(TeamColumn.JERSEY_TEAM));
                teamBean.webSite = cursor.getString(cursor.getColumnIndex(TeamColumn.WEBSITE_TEAM));
                teamBean.facebook = cursor.getString(cursor.getColumnIndex(TeamColumn.FACEBOOK_TEAM));
                teamBean.twitter = cursor.getString(cursor.getColumnIndex(TeamColumn.TWITTER_TEAM));
                teamBean.instagram = cursor.getString(cursor.getColumnIndex(TeamColumn.INSTAGRAM_TEAM));

                /*teamBean.setId(cursor.getString(cursor.getColumnIndex(TeamColumn.ID_TEAM)));
                teamBean.setName(cursor.getString(cursor.getColumnIndex(TeamColumn.NAME_TEAM)));
                teamBean.setBadge(cursor.getString(cursor.getColumnIndex(TeamColumn.BADGE_TEAM)));
                teamBean.setStadium(cursor.getString(cursor.getColumnIndex(TeamColumn.NAME_STADIUM_TEAM)));
                teamBean.setDescription(cursor.getString(cursor.getColumnIndex(TeamColumn.DESCRIPTION_TEAM)));
                teamBean.setFoundation(cursor.getString(cursor.getColumnIndex(TeamColumn.FOUNDATION_YEAR_TEAM)));
                teamBean.setJersey(cursor.getString(cursor.getColumnIndex(TeamColumn.JERSEY_TEAM)));
                teamBean.setWebSite(cursor.getString(cursor.getColumnIndex(TeamColumn.WEBSITE_TEAM)));
                teamBean.setFacebook(cursor.getString(cursor.getColumnIndex(TeamColumn.FACEBOOK_TEAM)));
                teamBean.setTwitter(cursor.getString(cursor.getColumnIndex(TeamColumn.TWITTER_TEAM)));
                teamBean.setInstagram(cursor.getString(cursor.getColumnIndex(TeamColumn.INSTAGRAM_TEAM)));*/

                list.add(teamBean);

            }
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }
        return list;
    }


    @Override
    public void clearInstance() {
        super.clearInstance();

        mInstance = null;
    }
}
