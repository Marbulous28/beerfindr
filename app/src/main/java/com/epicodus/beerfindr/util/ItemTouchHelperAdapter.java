package com.epicodus.beerfindr.util;

/**
 * Created by Peter on 7/26/16.
 */
public interface ItemTouchHelperAdapter {
    boolean onItemMove (int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
