package net.dektyarev.kupandaviewer.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OfferData implements Parcelable{

    @SerializedName("items")
    private List<OfferItem> items;

    public List<OfferItem> getItems() {
        return items;
    }

    public void setItems(List<OfferItem> items) {
        this.items = items;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeTypedList(getItems());
    }

    public static final Parcelable.Creator<OfferData> CREATOR = new Parcelable.Creator<OfferData>() {
        @Override
        public OfferData createFromParcel(Parcel in) {
            return new OfferData(in);
        }

        @Override
        public OfferData[] newArray(int size) {
            return new OfferData[size];
        }
    };

    private OfferData(Parcel in) {
        setItems(in.createTypedArrayList(OfferItem.CREATOR));
    }
}
