package com.shinelw.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by shinelw on 1/11/16.
 */
public class DemoAdapter extends RecyclerView.Adapter<DemoViewHolder> {
    private Context context;
    public DemoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public DemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DemoViewHolder holder = new DemoViewHolder(LayoutInflater.from(context).inflate(R.layout.item_demo, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(DemoViewHolder holder, int position) {
        holder.content.setText("content:" + position);
        holder.title.setText("title:" + position);
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
