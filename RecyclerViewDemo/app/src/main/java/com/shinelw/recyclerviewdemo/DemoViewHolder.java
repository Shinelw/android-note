package com.shinelw.recyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by shinelw on 1/11/16.
 */
public class DemoViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public TextView content;


    public DemoViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.tv_title);
        content = (TextView) itemView.findViewById(R.id.tv_content);

    }


}
