package com.example.admin.recycleviewdboptimizationexample;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by admin on 7/19/2016.
 */
public class HandsomeAdapter extends CursorRecyclerViewAdapter<HandsomeAdapter.HandsomeHolder> {

    private Context context;

    public HandsomeAdapter(Cursor cursor, Context context) {
        super(cursor);
        this.context = context;
    }

    @Override
    public void onBindViewHolder(HandsomeHolder viewHolder, Cursor cursor) {
        viewHolder.name.setText(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_KEY)));
        Glide.with(context).load(cursor.getString(cursor.getColumnIndex(DBHelper.URL_KEY))).override(800, 300)
                .fitCenter() .into(viewHolder.image);
        viewHolder.id.setText(String.valueOf(cursor.getInt(idIndex)));
    }

    @Override
    public HandsomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_layout, parent, false);
        return new HandsomeHolder(viewItem);
    }

    public class HandsomeHolder extends RecyclerView.ViewHolder{

        public TextView name, id;
        public ImageView image;

        public HandsomeHolder(View itemView) {
            super(itemView);
            name = ((TextView) itemView.findViewById(R.id.name));
            image = ((ImageView) itemView.findViewById(R.id.pic));
            id = ((TextView) itemView.findViewById(R.id.id));
        }
    }
}
