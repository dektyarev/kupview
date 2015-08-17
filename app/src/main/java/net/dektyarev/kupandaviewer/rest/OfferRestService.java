package net.dektyarev.kupandaviewer.rest;

import net.dektyarev.kupandaviewer.model.OfferData;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.QueryMap;

public interface OfferRestService {

    @GET("/api/")
    public void getOfferList(@QueryMap Map<String, String> parameters, Callback<OfferData> callback);

}
