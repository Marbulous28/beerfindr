package com.epicodus.beerfindr.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.beerfindr.R;
import com.epicodus.beerfindr.models.Beer;
import com.epicodus.beerfindr.ui.BeerDetailActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Peter on 7/19/16.
 */
public class BeerListAdapter extends RecyclerView.Adapter<BeerListAdapter.BeerViewHolder> {
    private ArrayList<Beer> mBeers = new ArrayList<>();
    private Context mContext;

    public BeerListAdapter(Context context, ArrayList<Beer> beers) {
        mContext = context;
        mBeers = beers;
    }

    @Override
    public BeerListAdapter.BeerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.beer_list_item, parent, false);
        BeerViewHolder viewHolder = new BeerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BeerListAdapter.BeerViewHolder holder, int position) {
        holder.bindBeer(mBeers.get(position));
    }

    @Override
    public int getItemCount() {

        return mBeers.size();
    }

    public class BeerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.beerImageView) ImageView mBeerImageView;
        @Bind(R.id.beerNameTextView) TextView mBeerNameTextView;
        @Bind(R.id.beerTypeTextView) TextView mBeerTypeTextView;
        @Bind(R.id.ibuTextView) TextView mIBUTextView;
        @Bind(R.id.abvTextView) TextView mABVTextView;

        private Context mContext;

        public BeerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, BeerDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("beers", Parcels.wrap(mBeers));
            mContext.startActivity(intent);
        }

        public void bindBeer(Beer beer) {
            mBeerNameTextView.setText(beer.getName());
            mIBUTextView.setText("IBU: " + beer.getIBU());
            mABVTextView.setText("ABV: " + beer.getABV() + "%");
//            Picasso.with(mContext).load(beer.getImageUrl()).into(mRestaurantImageView);
        }
    }
}
