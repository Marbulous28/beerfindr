package com.epicodus.beerfindr.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.beerfindr.R;
import com.epicodus.beerfindr.models.Beer;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeerDetailFragment extends Fragment {
    @Bind(R.id.beerNameTextView) TextView mBeerNameLabel;
    @Bind(R.id.beerDescriptionTextView) TextView mDescriptionLabel;
    @Bind(R.id.ibuTextView) TextView mIBULabel;
    @Bind(R.id.abvTextView) TextView mABVLabel;

    private Beer mBeer;

    public static BeerDetailFragment newInstance(Beer beer) {
        BeerDetailFragment beerDetailFragment = new BeerDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("beer", Parcels.wrap(beer));
        beerDetailFragment.setArguments(args);
        return beerDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBeer = Parcels.unwrap(getArguments().getParcelable("beer"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beer_detail, container, false);
        ButterKnife.bind(this, view);

        mBeerNameLabel.setText(mBeer.getName());
        mDescriptionLabel.setText(mBeer.getDescription());
        mABVLabel.setText(mBeer.getABV());
        mIBULabel.setText(mBeer.getIBU());

        return view;
    }

}
