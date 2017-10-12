package com.demoapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.demoapp.R;
import com.demoapp.models.ImageModel;

import java.text.SimpleDateFormat;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.CustomViewHolder> {

    private List<ImageModel> drawingList;
    private Context mContext;

    SimpleDateFormat simpledateformat;
    SimpleDateFormat simpledateformat2;

    public ReviewAdapter(Context context, List<ImageModel> drawingList) {
        this.drawingList = drawingList;
        this.mContext = context;
    }

    public void addAll(List<ImageModel> lst){
        drawingList.clear();
        drawingList.addAll(lst);
        notifyDataSetChanged();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_layout, viewGroup, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int i) {
        if (holder instanceof CustomViewHolder) {
            ImageModel im = (ImageModel)drawingList.get(i);
        }
    }


    @Override
    public int getItemCount() {
        return (null != drawingList ? drawingList.size() : 0);
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv;

        public CustomViewHolder(View v) {
            super(v);
            iv = (ImageView)v.findViewById(R.id.imageViewRow);
        }
    }
}
