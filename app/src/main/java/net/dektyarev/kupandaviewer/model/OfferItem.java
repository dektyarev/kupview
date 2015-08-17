package net.dektyarev.kupandaviewer.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class OfferItem implements Parcelable {

    @SerializedName("image_item")
    private String mImageUrl;

    @SerializedName("title_75")
    private String mTitle;

    @SerializedName("price")
    private OfferPrice mPrice;

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public OfferPrice getPrice() {
        return mPrice;
    }

    public void setPrice(OfferPrice price) {
        mPrice = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(getTitle());
        out.writeString(getImageUrl());
        out.writeParcelable(getPrice(), flags);
    }

    public static final Parcelable.Creator<OfferItem> CREATOR = new Parcelable.Creator<OfferItem>() {
        @Override
        public OfferItem createFromParcel(Parcel in) {
            return new OfferItem(in);
        }

        @Override
        public OfferItem[] newArray(int size) {
            return new OfferItem[size];
        }
    };

    private OfferItem(Parcel in) {
        setTitle(in.readString());
        setImageUrl(in.readString());
        setPrice((OfferPrice) in.readParcelable(null));
    }

}
