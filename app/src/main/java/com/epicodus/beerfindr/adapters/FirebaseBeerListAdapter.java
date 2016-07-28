package com.epicodus.beerfindr.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.epicodus.beerfindr.models.Beer;
import com.epicodus.beerfindr.ui.BeerDetailActivity;
import com.epicodus.beerfindr.util.ItemTouchHelperAdapter;
import com.epicodus.beerfindr.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

public class FirebaseBeerListAdapter extends FirebaseRecyclerAdapter<Beer, FirebaseBeerViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Beer> mBeers = new ArrayList<>();

    public FirebaseBeerListAdapter(Class<Beer> modelClass, int modelLayout,
                                         Class<FirebaseBeerViewHolder> viewHolderClass,
                                         Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mBeers.add(dataSnapshot.getValue(Beer.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void populateViewHolder(final FirebaseBeerViewHolder viewHolder, Beer model, int position) {
        viewHolder.bindBeer(model);

        viewHolder.mBeerImageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }

        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BeerDetailActivity.class);
                intent.putExtra("position", viewHolder.getAdapterPosition());
                intent.putExtra("beers", Parcels.wrap(mBeers));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mBeers, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mBeers.remove(position);
        getRef(position).removeValue();
    }

    private void setIndexInFirebase() {
        for (Beer beer : mBeers) {
            int index = mBeers.indexOf(beer);
            DatabaseReference ref = getRef(index);
            ref.setValue(beer);
        }
    }

    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }
}
