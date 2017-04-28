package com.example.rannver.mdmovie.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rannver.mdmovie.R;
import com.example.rannver.mdmovie.bean.listBean.CastListBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rannver on 2017/4/28.
 */

public class CastsAdpter extends RecyclerView.Adapter<CastsAdpter.ViewHolder> {

    private List<CastListBean> list;
    private Context context;

    public CastsAdpter(List<CastListBean> list, Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public CastsAdpter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.castlist_item,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CastsAdpter.ViewHolder holder, int position) {

        Picasso.with(context)
                .load(list.get(position).getUrl())
                .placeholder(R.drawable.ic_cast)
                .error(R.drawable.ic_cast)
                .resize(200,280)
                .into(holder.img);

        holder.name.setText(list.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView img;
        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.iv_castItem);
            name = (TextView) itemView.findViewById(R.id.casts_Item);
        }
    }
}
