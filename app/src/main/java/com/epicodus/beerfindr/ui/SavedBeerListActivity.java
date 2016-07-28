package com.epicodus.beerfindr.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.epicodus.beerfindr.Constants;
import com.epicodus.beerfindr.R;
import com.epicodus.beerfindr.adapters.FirebaseBeerListAdapter;
import com.epicodus.beerfindr.adapters.FirebaseBeerViewHolder;
import com.epicodus.beerfindr.models.Beer;
import com.epicodus.beerfindr.util.OnStartDragListener;
import com.epicodus.beerfindr.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedBeerListActivity extends AppCompatActivity implements OnStartDragListener {
    private DatabaseReference mBeerReference;
    private FirebaseBeerListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_beers);
        ButterKnife.bind(this);

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mBeerReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_BEERS)
                .child(uid);

        mFirebaseAdapter = new FirebaseBeerListAdapter(Beer.class,
                R.layout.beer_list_item_draggable, FirebaseBeerViewHolder.class,
                mBeerReference, this, this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}