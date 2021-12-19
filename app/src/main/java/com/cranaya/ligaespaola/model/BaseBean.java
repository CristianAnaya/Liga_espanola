package com.cranaya.ligaespaola.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BaseBean implements Parcelable {


    protected long _id;

    protected long fechaSistema;


    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(long fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public BaseBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this._id);
        dest.writeLong(this.fechaSistema);
    }

    protected BaseBean(Parcel in) {
        this._id = in.readLong();
        this.fechaSistema = in.readLong();
    }

}