package com.cranaya.ligaespaola.activities;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;

import com.cranaya.ligaespaola.R;
import com.cranaya.ligaespaola.base.BaseActionBarActivity;
import com.cranaya.ligaespaola.databinding.ActivityDetailTeamBinding;
import com.cranaya.ligaespaola.viewModel.TeamViewModel;

public class DetailTeamActivity extends BaseActionBarActivity {

    private ActivityDetailTeamBinding binding;
    private TeamViewModel teamViewModel;
    private String linkFacebook, linkInstagram, linkTwitter, linkWeb = "";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_team);
        Bundle extra = getIntent().getExtras();

        if (extra != null){
            teamViewModel = extra.getParcelable(TeamViewModel.TAG);

            if (teamViewModel != null){
                binding.setTeam(teamViewModel);
                linkFacebook = teamViewModel.facebook;
                linkInstagram = teamViewModel.instagram;
                linkTwitter = teamViewModel.twitter;
                linkWeb = teamViewModel.webSite;
            }
        }



        binding.txtFacebook.setText(Html.fromHtml(linkFacebook, Html.FROM_HTML_MODE_COMPACT));
        binding.txtInstagram.setText(Html.fromHtml(linkInstagram, Html.FROM_HTML_MODE_COMPACT));
        binding.txtTwitter.setText(Html.fromHtml(linkTwitter, Html.FROM_HTML_MODE_COMPACT));
        binding.txtWeb.setText(Html.fromHtml(linkWeb, Html.FROM_HTML_MODE_COMPACT));

    }
}