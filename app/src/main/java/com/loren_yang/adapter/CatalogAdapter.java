package com.loren_yang.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loren_yang.domain.TagDomain;
import com.loren_yang.listener.RecycleViewListener;
import com.loren_yang.tagflowlayout.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Loren Yang on 2017/8/1.
 */

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder> implements View.OnClickListener {

    private List<TagDomain> dataList;
    private RecycleViewListener.OnItemOnClickListener mOnItemOnClickListener=null;

    @Override
    public CatalogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_listview, parent,false);
        CatalogViewHolder wHolder = new CatalogViewHolder(view);
        view.setOnClickListener(this);
        return wHolder;
    }

    @Override
    public void onBindViewHolder(CatalogViewHolder holder, int position) {
        //给Item打上标签
        holder.itemView.setTag(position);
        TagDomain td=dataList.get(position);
        holder.recyleTitle.setText(td.getTagName());
        holder.recycleInstruction.setText(td.getTagInstruction());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setData(List<TagDomain> tagList){
        this.dataList=tagList;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        if(mOnItemOnClickListener!=null){
            mOnItemOnClickListener.onItemOnClick(view, (Integer) view.getTag(),dataList.get((Integer) view.getTag()).getTagName());
        }
    }

    public void setmOnItemOnClickListener(RecycleViewListener.OnItemOnClickListener listener ){
        this.mOnItemOnClickListener=listener;
    }

    public class CatalogViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recyle_title)
        TextView recyleTitle;
        @BindView(R.id.recycle_instruction)
        TextView recycleInstruction;

        public CatalogViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
