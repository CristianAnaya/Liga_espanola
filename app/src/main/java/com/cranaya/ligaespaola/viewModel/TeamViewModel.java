package com.cranaya.ligaespaola.viewModel;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.ViewModel;

import com.cranaya.ligaespaola.R;
import com.cranaya.ligaespaola.model.TeamBean;
import com.squareup.picasso.Picasso;


public class TeamViewModel extends ViewModel implements Parcelable {
    public static final String TAG = TeamViewModel.class.getSimpleName();

    public String id;
    public String name;
    public String stadium;
    public String badge;
    public String description;
    public String foundation;
    public String jersey;
    public String webSite;
    public String facebook;
    public String twitter;
    public String instagram;

    public TeamViewModel() {
    }


    public TeamViewModel(TeamBean teamBean) {
        this.id = teamBean.id;
        this.name = teamBean.name;
        this.stadium = teamBean.stadium;
        this.badge = teamBean.badge;
        this.description = teamBean.description;
        this.foundation = teamBean.foundation;
        this.jersey = teamBean.jersey;
        this.webSite = teamBean.webSite;
        this.facebook = teamBean.facebook;
        this.twitter = teamBean.twitter;
        this.instagram = teamBean.instagram;
    }


    protected TeamViewModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        stadium = in.readString();
        badge = in.readString();
        description = in.readString();
        foundation = in.readString();
        jersey = in.readString();
        webSite = in.readString();
        facebook = in.readString();
        twitter = in.readString();
        instagram = in.readString();
    }

    public static final Creator<TeamViewModel> CREATOR = new Creator<TeamViewModel>() {
        @Override
        public TeamViewModel createFromParcel(Parcel in) {
            return new TeamViewModel(in);
        }

        @Override
        public TeamViewModel[] newArray(int size) {
            return new TeamViewModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(stadium);
        dest.writeString(badge);
        dest.writeString(description);
        dest.writeString(foundation);
        dest.writeString(jersey);
        dest.writeString(webSite);
        dest.writeString(facebook);
        dest.writeString(twitter);
        dest.writeString(instagram);
    }
}
