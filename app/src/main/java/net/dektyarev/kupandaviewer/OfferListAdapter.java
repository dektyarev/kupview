package net.dektyarev.kupandaviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.dektyarev.kupandaviewer.databinding.OfferListItemBinding;
import net.dektyarev.kupandaviewer.model.OfferItem;

import java.util.List;

public class OfferListAdapter extends ArrayAdapter<OfferItem> {

    private List<OfferItem> mItems;

    public OfferListAdapter(Context context, int resource, List<OfferItem> items) {
        super(context, resource, items);
        mItems = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OfferItem item = mItems.get(position);

        // bind OfferItem to list item view
        OfferListItemBinding binding = OfferListItemBinding.inflate(getLayoutInflater(), parent, false);
        binding.setOffer(item);
        View view = binding.getRoot();

        // use Glide to download offer image
        ImageView imageView = (ImageView) view.findViewById(R.id.offer_image);
        if (imageView != null) {
            Glide.with(getContext())
                    .load(item.getImageUrl())
                    .into(imageView);
        }

        return view;
    }

    private LayoutInflater getLayoutInflater() {
        return (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
}
