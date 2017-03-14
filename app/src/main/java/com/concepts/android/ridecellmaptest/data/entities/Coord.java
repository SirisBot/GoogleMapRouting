package com.concepts.android.ridecellmaptest.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Siris on 3/13/2017.
 */

public class Coord implements Parcelable {
    private Double latitude;
    private Double longitude;

    public Coord(String latitude, String longitude) {
        if (latitude.equals(""))
            this.latitude = null;
        else this.latitude = Double.valueOf(latitude);
        if (longitude.equals(""))
            this.latitude = null;
        else this.longitude = Double.valueOf(longitude);
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public boolean validate() {
        return !(latitude == null || longitude == null);
    }

    protected Coord(Parcel in) {
        latitude = in.readByte() == 0x00 ? null : in.readDouble();
        longitude = in.readByte() == 0x00 ? null : in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (latitude == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(latitude);
        }
        if (longitude == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(longitude);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Coord> CREATOR = new Parcelable.Creator<Coord>() {
        @Override
        public Coord createFromParcel(Parcel in) {
            return new Coord(in);
        }

        @Override
        public Coord[] newArray(int size) {
            return new Coord[size];
        }
    };
}