package com.cranaya.ligaespaola.activities;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cranaya.ligaespaola.base.BaseActionBarActivity;
import com.cranaya.ligaespaola.R;
import com.cranaya.ligaespaola.dao.TeamDao;
import com.cranaya.ligaespaola.framework.adapter.TeamAdapter;
import com.cranaya.ligaespaola.framework.item.TeamCard;
import com.cranaya.ligaespaola.viewModel.TeamViewModel;

import static com.cranaya.ligaespaola.connection.NetworkUtilities.getTeam;

public class EquiposActivity extends BaseActionBarActivity {
    private static final String TAG = EquiposActivity.class.getSimpleName();

    private RecyclerView viewListTeam;
    private TeamAdapter teamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipos);

        viewListTeam = findViewById(R.id.viewListTeam);
        viewListTeam.setLayoutManager(new LinearLayoutManager(mContext));
        viewListTeam.setItemAnimator(new DefaultItemAnimator());

        teamAdapter = new TeamAdapter();

        teamAdapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TeamCard teamCard = (TeamCard) viewListTeam.getChildViewHolder(v);
                if (teamCard != null){
                    TeamViewModel teamViewModel = teamCard.getTeamViewModel();
                    Toast.makeText(EquiposActivity.this, ""+teamViewModel.name, Toast.LENGTH_SHORT).show();
                }

            }
        });
        viewListTeam.setAdapter(teamAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        loadTeam();
    }

    private void loadTeam(){
        try {
            TeamDao teamDao = TeamDao.getInstance(mContext);
            teamAdapter.setDatos(teamDao.getAll(),mContext);

        } catch (Exception e) {
            Log.d(TAG, "loadTeam: "+e.getMessage());
        }
    }

    @Override
    public void onActualizarLiga() {
        super.onActualizarLiga();
        loadTeam();
    }
}