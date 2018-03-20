package com.azcltd.android.test.usichenko.cities.service.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City implements Parcelable {

    @Expose
    public int id;

    @Expose
    public String name;

    @Expose
    public String description;

    @SerializedName("image_url")
    @Expose
    public String imageUrl;

    @Expose
    public String country;

    @Expose
    public Location location;

    public City() {

    }

    protected City(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        imageUrl = in.readString();
        country = in.readString();
        location = in.readParcelable(Location.class.getClassLoader());
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(imageUrl);
        parcel.writeString(country);
        parcel.writeParcelable(location, i);
    }
}
