package com.example.rannver.mdmovie.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rannver.mdmovie.R;
import com.example.rannver.mdmovie.bean.listBean.HotListBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rannver on 2017/4/21.
 */

public class HotListAdpter extends RecyclerView.Adapter<HotListAdpter.ViewHolder>{

    private List<HotListBean> hotlist;
    private Context context;

    public HotListAdpter(List<HotListBean> hotlist, Context context){
        this.hotlist = hotlist;
        this.context = context;
    }

    @Override
    public HotListAdpter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotlist_item,null);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HotListAdpter.ViewHolder holder, int position) {

        Picasso.with(context)
                .load(hotlist.get(position).getUrl())
                .placeholder(R.drawable.ic_movie)
                .error(R.drawable.ic_movie)
                .into(holder.image);

        holder.tv_title.setText(hotlist.get(position).getTitle());
        holder.tv_rate.setText("评分："+hotlist.get(position).getRate());
        holder.tv_count.setText(hotlist.get(position).getCollect_count()+"人看过");

        String directions = "";
        String casts = "";

        for (int i = 0;i<hotlist.get(position).getDirector().size();i++) {
            String s = hotlist.get(position).getDirector().get(i);
            if (i==0){
                directions+=s;
            }else {
                directions+="/"+s;
            }
        }
        for(int i = 0;i<hotlist.get(position).getCasts().size();i++){
            String s = hotlist.get(position).getCasts().get(i);
            if(i==0){
                casts += s;
            }else {
                casts += "/"+s;
            }
        }

        holder.tv_directors.setText(directions);
        holder.tv_casts.setText(casts);
    }

    @Override
    public int getItemCount() {
        return hotlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;
        private TextView tv_title;
        private TextView tv_rate;
        private TextView tv_directors;
        private TextView tv_casts;
        private TextView tv_count;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.iv_hotItem);
            tv_title = (TextView) itemView.findViewById(R.id.title_hotItem);
            tv_rate = (TextView) itemView.findViewById(R.id.rate_hotItem);
            tv_directors = (TextView) itemView.findViewById(R.id.directions_hotItem);
            tv_casts = (TextView) itemView.findViewById(R.id.casts_hotItem);
            tv_count = (TextView) itemView.findViewById(R.id.look_hotItem);
        }
    }
}
