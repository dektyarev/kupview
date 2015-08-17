package net.dektyarev.kupandaviewer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.Toast;

import net.dektyarev.kupandaviewer.model.OfferData;
import net.dektyarev.kupandaviewer.model.OfferItem;
import net.dektyarev.kupandaviewer.rest.RequestUtils;
import net.dektyarev.kupandaviewer.rest.RestClient;

import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends ActionBarActivity implements OfferListFragment.OnFragmentInteractionListener {

    private static final String OFFER_LIST_FRAGMENT_TAG = "OfferListFragmentTag";
    private static final String OFFER_DATA_KEY = "offerDataKey";

    private OfferData mOfferData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            setOfferData((OfferData) savedInstanceState.getParcelable(OFFER_DATA_KEY));
        }

        OfferListFragment offerFragment = getOfferFragment();
        if (offerFragment == null) {
            offerFragment = OfferListFragment.newInstance(mOfferData);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, offerFragment, OFFER_LIST_FRAGMENT_TAG)
                    .commit();
        }

        if (savedInstanceState == null) {
            offerFragment.setLoadingState(OfferListFragment.LoadingState.LOADING);
            loadOffers();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(OFFER_DATA_KEY, mOfferData);
    }

    private void loadOffers() {
        Map<String, String> parameters = RequestUtils.constructParametersMap(this);
        String serviceUrl = getString(R.string.service_url);
        RestClient.getInstance(serviceUrl).getApiService().getOfferList(parameters, new Callback<OfferData>() {
            @Override
            public void success(OfferData offerData, Response response) {
                setOfferData(offerData);
                getOfferFragment().setLoadingState(OfferListFragment.LoadingState.COMPLETED);
            }

            @Override
            public void failure(RetrofitError error) {
                getOfferFragment().setLoadingState(OfferListFragment.LoadingState.FAILED);
            }
        });
    }

    private void setOfferData(OfferData offerData) {
        mOfferData = offerData;

        if (getOfferFragment() != null) {
            getOfferFragment().setOfferData(mOfferData);
        }
    }

    /**
     * Retreives offer fragment
     * @return
     */
    private OfferListFragment getOfferFragment() {
        return (OfferListFragment) getSupportFragmentManager().findFragmentByTag(OFFER_LIST_FRAGMENT_TAG);
    }

    @Override
    public void onFragmentInteraction(int position) {
        OfferItem offerItem = mOfferData.getItems().get(position);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, OfferDetailsFragment.newInstance(offerItem))
                .addToBackStack(null)
                .commit();
    }

}
