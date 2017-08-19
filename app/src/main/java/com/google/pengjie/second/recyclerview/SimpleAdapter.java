package com.google.pengjie.second.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.pengjie.second.R;

import java.util.List;

/**
 * Created by pengjie on 8/10/17.
 */

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.MyViewHolder> {
    private final LayoutInflater layoutInflater;
    private final List<String> data;

    public SimpleAdapter(Context context, List<String> data) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.simple_recycler_view_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ((TextView)holder.itemView).setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void add(String str) {
        data.add(0, str);
        notifyItemInserted(0);
    }

    static final class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
