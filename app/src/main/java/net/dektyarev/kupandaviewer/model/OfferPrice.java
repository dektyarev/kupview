package net.dektyarev.kupandaviewer.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class OfferPrice implements Parcelable {

    @SerializedName("amount")
    private int mAmount;

    @SerializedName("currency")
    private OfferPriceCurrency mCurrency;

    public int getAmount() {
        return mAmount;
    }

    public void setAmount(int amount) {
        mAmount = amount;
    }

    public OfferPriceCurrency getCurrency() {
        return mCurrency;
    }

    public void setCurrency(OfferPriceCurrency currency) {
        mCurrency = mCurrency;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(getAmount());
    }

    public static final Parcelable.Creator<OfferPrice> CREATOR = new Parcelable.Creator<OfferPrice>() {
        @Override
        public OfferPrice createFromParcel(Parcel in) {
            return new OfferPrice(in);
        }

        @Override
        public OfferPrice[] newArray(int size) {
            return new OfferPrice[size];
        }
    };

    private OfferPrice(Parcel in) {
        setAmount(in.readInt());
    }

    public String getPriceWithCurrency() {
        return getAmount() + " " + getCurrency().getShortTitle();
    }
}
