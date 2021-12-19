package com.cranaya.ligaespaola.utils;

import android.content.Context;
import android.content.Intent;

import com.cranaya.ligaespaola.base.BaseActionBarActivity;
import com.cranaya.ligaespaola.LigaApp;
import com.cranaya.ligaespaola.dao.TeamDao;
import com.cranaya.ligaespaola.model.TeamBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.cranaya.ligaespaola.utils.Parametro.SERVICE_RESULT;

public class JsonData {
    private static final String TAG = JsonData.class.getSimpleName();
    private Context mContext;

    public static void jsonEquipoFutbol(JSONObject jsonObjectResultado){
        Context mContext  = LigaApp.getContext();

        try {
            TeamDao teamDao = TeamDao.getInstance(LigaApp.getContext());
            JSONArray obj = jsonObjectResultado.getJSONArray("teams");
            for (int i = 0; i<obj.length(); i++){
                JSONObject data = obj.getJSONObject(i);
                String nameLeague = data.getString("strLeague");
                if (nameLeague.equals("Spanish La Liga")){
                    TeamBean teamBean = new TeamBean();
                    teamBean.setId(data.getString("idTeam"));
                    teamBean.setName(data.getString("strTeam"));
                    teamBean.setBadge(data.getString("strTeamBadge"));
                    teamBean.setStadium(data.getString("strStadium"));
                    teamBean.setDescription(data.getString("strDescriptionEN"));
                    teamBean.setFoundation(data.getString("intFormedYear"));
                    teamBean.setJersey(data.getString("strTeamJersey"));
                    teamBean.setWebSite(data.getString("strWebsite"));
                    teamBean.setFacebook(data.getString("strFacebook"));
                    teamBean.setTwitter(data.getString("strTwitter"));
                    teamBean.setInstagram(data.getString("strInstagram"));
                    teamDao.insertOrUpdate(teamBean);

                }
            }

            Intent intent = new Intent(SERVICE_RESULT);
            intent.putExtra(Parametro.CASE_BROADCAST, BaseActionBarActivity.ACTUALIZAR_LIGA);
            mContext.sendBroadcast(intent);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
