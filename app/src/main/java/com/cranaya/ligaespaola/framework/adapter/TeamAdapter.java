package com.cranaya.ligaespaola.framework.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cranaya.ligaespaola.R;
import com.cranaya.ligaespaola.framework.item.TeamCard;
import com.cranaya.ligaespaola.model.TeamBean;
import com.cranaya.ligaespaola.viewModel.TeamViewModel;
import com.cranaya.ligaespaola.databinding.TeamBinding;
import java.util.ArrayList;

public class TeamAdapter extends RecyclerView.Adapter<TeamCard> implements View.OnClickListener{
    public static final String TAG = TeamAdapter.class.getSimpleName();

    //private ArrayList<TeamBean> datos;
    private ArrayList<TeamViewModel> datos;

    private View.OnClickListener listener;
    Context context;

    public void setDatos(ArrayList<TeamViewModel> datos, Context mContext) {
        this.datos = datos;
        this.context = mContext;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TeamCard onCreateViewHolder(ViewGroup parent, int viewType) {

        TeamBinding teamBinding = TeamBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        teamBinding.relativeFather.setOnClickListener(this);
        return new TeamCard(teamBinding,context);
    }

    @Override
    public void onBindViewHolder(TeamCard holder, int position) {
        try {

            TeamViewModel teamBean = datos.get(position);
            holder.bindTeam(teamBean,context);

        } catch (Exception e) {
            Log.d(TAG, "onBindViewHolder: "+e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return datos == null ? 0 : datos.size();
    }

    public boolean empty() {
        return getItemCount() == 0;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }


    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }
}
