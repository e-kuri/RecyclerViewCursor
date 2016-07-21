package com.example.admin.recycleviewdboptimizationexample;

import android.database.Cursor;
import android.database.DataSetObserver;
import android.support.v7.widget.RecyclerView;

/**
 * Created by admin on 7/19/2016.
 */
public abstract class CursorRecyclerViewAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>{

    private Cursor cursor;

    private boolean dataValid;

    protected int idIndex;

    private DataSetObserver mDataSetObserver;

    //private DataSetObserver mDataSetObserver;

    public abstract void onBindViewHolder(VH viewHolder, Cursor cursor);

    public CursorRecyclerViewAdapter(Cursor cursor){
        this.cursor = cursor;
        dataValid = cursor != null;
        idIndex = dataValid ? cursor.getColumnIndex("_id") : -1;

        mDataSetObserver = new NotifyingDataSetObserver();
        if (cursor != null) {
            cursor.registerDataSetObserver(mDataSetObserver);
        }
    }

    @Override
    public int getItemCount() {
        if(dataValid && cursor != null){
            return cursor.getCount();
        }
        return 0;
    }

    @Override
    public long getItemId(int position) {
        if(dataValid && cursor != null && cursor.moveToPosition(position)){
            return cursor.getLong(idIndex);
        }
        return -1;
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(true);
    }

    @Override
    public void onBindViewHolder(VH viewHolder, int position) {
        if (!dataValid) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (!cursor.moveToPosition(position)) {
            throw new IllegalStateException("couldn't move cursor to position " + position);
        }
        onBindViewHolder(viewHolder, cursor);
    }

    /**
     * Change the underlying cursor to a new cursor. If there is an existing cursor it will be
     * closed.
     */
    public void changeCursor(Cursor cursor) {
        Cursor old = swapCursor(cursor);
        if (old != null) {
            old.close();
        }
    }

    /**
     * Swap in a new Cursor, returning the old Cursor.  Unlike
     * {@link #changeCursor(Cursor)}, the returned old Cursor is <em>not</em>
     * closed.
     */
    public Cursor swapCursor(Cursor newCursor) {
        if (newCursor == cursor) {
            return null;
        }
        final Cursor oldCursor = cursor;
        if (oldCursor != null && mDataSetObserver != null) {
            oldCursor.unregisterDataSetObserver(mDataSetObserver);
        }
        cursor = newCursor;
        if (cursor != null) {
            if (mDataSetObserver != null) {
                cursor.registerDataSetObserver(mDataSetObserver);
            }
            idIndex = newCursor.getColumnIndexOrThrow("_id");
            dataValid = true;
            notifyDataSetChanged();
        } else {
            idIndex = -1;
            dataValid = false;
            notifyDataSetChanged();
            //There is no notifyDataSetInvalidated() method in RecyclerView.Adapter
        }
        return oldCursor;
    }

    private class NotifyingDataSetObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            super.onChanged();
            dataValid = true;
            notifyDataSetChanged();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
            dataValid = false;
            notifyDataSetChanged();
            //There is no notifyDataSetInvalidated() method in RecyclerView.Adapter
        }
    }

}
