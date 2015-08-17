package net.dektyarev.kupandaviewer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.dektyarev.kupandaviewer.databinding.FragmentOfferDetailsBinding;
import net.dektyarev.kupandaviewer.model.OfferItem;

public class OfferDetailsFragment extends Fragment {

    private static final String ARG_OFFER_ITEM = "offerItem";

    private OfferItem mOfferItem;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment OfferDetailsFragment.
     */
    public static OfferDetailsFragment newInstance(OfferItem offerItem) {
        OfferDetailsFragment fragment = new OfferDetailsFragment();

        // store OfferItem object in bundle for future use
        Bundle args = new Bundle();
        args.putParcelable(ARG_OFFER_ITEM, offerItem);
        fragment.setArguments(args);

        return fragment;
    }

    public OfferDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mOfferItem = getArguments().getParcelable(ARG_OFFER_ITEM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Bind offer object to view
        FragmentOfferDetailsBinding binding = FragmentOfferDetailsBinding.inflate(inflater, container, false);
        binding.setOffer(mOfferItem);
        View view = binding.getRoot();

        // Use Glide lib to download offer image by url
        ImageView imageView = (ImageView) view.findViewById(R.id.offer_image);
        if (imageView != null) {
            Glide.with(getActivity())
                    .load(mOfferItem.getImageUrl())
                    .into(imageView);
        }

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
