package net.dektyarev.kupandaviewer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;

import net.dektyarev.kupandaviewer.model.OfferData;

public class OfferListFragment extends Fragment implements AbsListView.OnItemClickListener {

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    private static final String ARG_OFFER_DATA = "offerData";
    private static final String LOADING_STATE_KEY = "loadingStateKey";

    private int mLoadingState = LoadingState.NO_STATE;

    public static OfferListFragment newInstance(OfferData offerData) {
        OfferListFragment fragment = new OfferListFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_OFFER_DATA, offerData);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public OfferListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            setOfferData((OfferData) getArguments().getParcelable(ARG_OFFER_DATA));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(LOADING_STATE_KEY, mLoadingState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offer, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);

        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mLoadingState = savedInstanceState.getInt(LOADING_STATE_KEY, LoadingState.NO_STATE);
        }
        updateEmptyListView();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onFragmentInteraction(position);
        }
    }

    public void setLoadingState(int state) {
        mLoadingState = state;
        updateEmptyListView();
    }

    /**
     * Updates text label for empty list. Used to display loading state.
     */
    private void updateEmptyListView() {
        // check if view not created
        if (getView() == null) {
            // skip textview update as fragment view is still not exists
            return;
        }

        View emptyView = getView().findViewById(android.R.id.empty);

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(getLabelForState(mLoadingState));
        }
    }

    /**
     * Resolves string label for loading state
     * @param state LoadingState constant
     * @return Corresponding string for provided loading state
     */
    private String getLabelForState(int state) {
        if (!isAdded()) {
            return null;
        }

        switch (state) {
            case LoadingState.LOADING :
                return getString(R.string.offer_list_loading);
            case LoadingState.FAILED :
                return getString(R.string.offer_list_loading_failed);
            default:
                return "";
        }
    }

    /**
     * Initializes view components to display OfferData
     * @param offerData An OfferData object for adapter initialization
     */
    public void setOfferData(OfferData offerData) {
        if (offerData == null) {
            return;
        }

        mAdapter = new OfferListAdapter(getActivity(), R.layout.offer_list_item, offerData.getItems());

        if (mListView != null) {
            ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(int position);
    }

    /**
     * Class to store loading state constants
     */
    public class LoadingState {
        public static final int FAILED = -1;
        public static final int NO_STATE = 0;
        public static final int LOADING = 1;
        public static final int COMPLETED = 2;
    }

}
