package net.dektyarev.kupandaviewer.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class OfferPriceCurrency implements Parcelable {

    @SerializedName("short_title")
    private String mShortTitle;

    public String getShortTitle() {
        return mShortTitle;
    }

    public void setShortTitle(String shortTitle) {
        mShortTitle = mShortTitle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(getShortTitle());
    }

    public static final Parcelable.Creator<OfferPriceCurrency> CREATOR = new Parcelable.Creator<OfferPriceCurrency>() {
        @Override
        public OfferPriceCurrency createFromParcel(Parcel in) {
            return new OfferPriceCurrency(in);
        }

        @Override
        public OfferPriceCurrency[] newArray(int size) {
            return new OfferPriceCurrency[size];
        }
    };

    private OfferPriceCurrency(Parcel in) {
        setShortTitle(in.readString());
    }

}
