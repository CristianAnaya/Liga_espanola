package com.cranaya.ligaespaola.framework.item;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cranaya.ligaespaola.R;
import com.cranaya.ligaespaola.activities.DetailTeamActivity;
import com.cranaya.ligaespaola.databinding.TeamBinding;
import com.cranaya.ligaespaola.model.TeamBean;
import com.cranaya.ligaespaola.viewModel.TeamViewModel;
import com.squareup.picasso.Picasso;


public class TeamCard extends RecyclerView.ViewHolder {
    public static final String TAG = TeamCard.class.getSimpleName();

    private TeamBinding teamBinding;
    private TeamViewModel teamViewModel;
    public TeamCard(TeamBinding teamBinding, final Context mContext) {
        super(teamBinding.getRoot());

        this.teamBinding = teamBinding;

    }


    public void bindTeam(final TeamViewModel teamViewModel, final Context context) {
        try {
            teamBinding.setTeamView(teamViewModel);
            this.teamViewModel = teamViewModel;
            teamBinding.btnVerDetalle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,DetailTeamActivity.class);
                    intent.putExtra(TeamViewModel.TAG, teamViewModel);
                    context.startActivity(intent);
                }
            });
        } catch (Exception e) {
            Log.d(TAG, "bindDescargo: "+e.getMessage());
        }
    }

    public TeamViewModel getTeamViewModel() {
        return teamViewModel;
    }

}