package net.dektyarev.kupandaviewer.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class RestClient {
    private static final String BASE_URL = "http://apimobile.kupanda.ru";

    private OfferRestService mApiService;
    private static RestClient mInstance = null;

    public static RestClient getInstance(String baseUrl) {
        if (mInstance == null) {
            synchronized (RestClient.class) {
                if (mInstance == null) {
                    mInstance = new RestClient(baseUrl);
                }
            }
        }
        return mInstance;
    }

    private RestClient(String baseUrl) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new OfferTypeAdapterFactory())
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(baseUrl)
                .setConverter(new GsonConverter(gson))
                .build();

        mApiService = restAdapter.create(OfferRestService.class);
    }

    public OfferRestService getApiService() {
        return mApiService;
    }
}
