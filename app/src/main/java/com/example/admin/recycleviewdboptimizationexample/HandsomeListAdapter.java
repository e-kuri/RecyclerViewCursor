package com.example.admin.recycleviewdboptimizationexample;

import android.content.Context;
import android.graphics.Movie;
import android.graphics.Point;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.cache.DiskCache;

import java.util.List;

/**
 * Created by admin on 7/19/2016.
 */
public class HandsomeListAdapter extends RecyclerView.Adapter<HandsomeListAdapter.MyViewHolder> {

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_layout, parent, false);

        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(Handsome.handsomeList.get(position).getName());
        Context context = holder.image.getContext();
        Glide.with(context).load(Handsome.handsomeList.get(position).getUrl()).override(800, 300)
                .fitCenter() .into(holder.image);
        holder.id.setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return Handsome.handsomeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name, id;
        public ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = ((TextView) itemView.findViewById(R.id.name));
            image = ((ImageView) itemView.findViewById(R.id.pic));
            id = ((TextView) itemView.findViewById(R.id.id));
        }
    }
}
