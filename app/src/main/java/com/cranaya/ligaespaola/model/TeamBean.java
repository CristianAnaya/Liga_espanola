package com.cranaya.ligaespaola.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TeamBean extends BaseBean {
    public static final String TAG = TeamBean.class.getSimpleName();

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

    public TeamBean() {
    }

    protected TeamBean(Parcel in) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TeamBean> CREATOR = new Creator<TeamBean>() {
        @Override
        public TeamBean createFromParcel(Parcel in) {
            return new TeamBean(in);
        }

        @Override
        public TeamBean[] newArray(int size) {
            return new TeamBean[size];
        }
    };



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFoundation() {
        return foundation;
    }

    public void setFoundation(String foundation) {
        this.foundation = foundation;
    }

    public String getJersey() {
        return jersey;
    }

    public void setJersey(String yersey) {
        this.jersey = yersey;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }


}
