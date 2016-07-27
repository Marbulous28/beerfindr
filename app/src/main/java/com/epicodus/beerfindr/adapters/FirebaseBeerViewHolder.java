package com.epicodus.beerfindr.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.beerfindr.Constants;
import com.epicodus.beerfindr.R;
import com.epicodus.beerfindr.models.Beer;
import com.epicodus.beerfindr.ui.BeerDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by Peter on 7/22/16.
 */
public class FirebaseBeerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView mBeerImageView;
    View mView;
    Context mContext;

    public FirebaseBeerViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindBeer(Beer beer) {
        TextView beerNameTextView = (TextView) mView.findViewById(R.id.beerNameTextView);
        TextView ibuTextView = (TextView) mView.findViewById(R.id.ibuTextView);
        TextView abvTextView = (TextView) mView.findViewById(R.id.abvTextView);
        mBeerImageView = (ImageView) mView.findViewById(R.id.beerImageView);

        beerNameTextView.setText(beer.getName());
        ibuTextView.setText("IBU: " + beer.getIBU());
        abvTextView.setText("ABV: " + beer.getABV() +"%");
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Beer> beers = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_BEERS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    beers.add(snapshot.getValue(Beer.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, BeerDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("beers", Parcels.wrap(beers));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
