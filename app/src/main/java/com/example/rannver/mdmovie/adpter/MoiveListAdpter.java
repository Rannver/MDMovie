package com.example.rannver.mdmovie.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rannver.mdmovie.R;
import com.example.rannver.mdmovie.bean.listBean.MoiveListBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rannver on 2017/4/21.
 */

public class MoiveListAdpter extends RecyclerView.Adapter<MoiveListAdpter.ViewHolder>{

    private List<MoiveListBean> moivelist;
    private Context context;
    private int type;

    public final static int VIEW_HOT = 1;
    public final static int VIEW_FUTURE = 2;

    public MoiveListAdpter(List<MoiveListBean> hotlist, Context context,int type){
        this.moivelist = hotlist;
        this.context = context;
        this.type = type;
    }

    @Override
    public MoiveListAdpter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotlist_item,null);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MoiveListAdpter.ViewHolder holder, final int position) {

        initCommonView(holder,position);

        switch (getItemViewType(position)){
            case VIEW_HOT:
                holder.tv_count.setText(moivelist.get(position).getCollect_count()+"人看过");
                if (moivelist.get(position).getRate()!=0){
                    holder.tv_rate.setText("评分："+moivelist.get(position).getRate());
                }else {
                    holder.tv_rate.setText("评价人数不足");
                }
                break;
            case VIEW_FUTURE:
                holder.tv_count.setVisibility(View.GONE);
                String genres = "";
                for (int i = 0;i<moivelist.get(position).getGenres().size();i++){
                    String s = moivelist.get(position).getGenres().get(i);
                    if(i==0){
                        genres += s;
                    }else {
                        genres += "/"+s;
                    }
                }
                holder.tv_rate.setText(genres);
                break;
            default:
                Log.d("MoiveListAdpter","类型不匹配");
                break;
        }

    }

    @Override
    public int getItemViewType(int position) {
        return type;
    }

    @Override
    public int getItemCount() {
        return moivelist.size();
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

    //加载通用信息
    private void initCommonView(final MoiveListAdpter.ViewHolder holder, int position) {
        Picasso.with(context)
                .load(moivelist.get(position).getUrl())
                .placeholder(R.drawable.ic_movie)
                .error(R.drawable.ic_movie)
                .resize(240,330)
                .into(holder.image);
        holder.tv_title.setText(moivelist.get(position).getTitle());
        String directions = "";
        String casts = "";

        for (int i = 0;i<moivelist.get(position).getDirector().size();i++) {
            String s = moivelist.get(position).getDirector().get(i);
            if (i==0){
                directions+=s;
            }else {
                directions+="/"+s;
            }
        }
        for(int i = 0;i<moivelist.get(position).getCasts().size();i++){
            String s = moivelist.get(position).getCasts().get(i);
            if(i==0){
                casts += s;
            }else {
                casts += "/"+s;
            }
        }

        holder.tv_directors.setText(directions);
        holder.tv_casts.setText(casts);

        //设置监听事件
        if(OnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition();
                    OnItemClickListener.OnItemClick(holder.itemView,position,moivelist.get(position).getId());
                    System.out.println("TAG:"+moivelist.get(position).getId());
                }
            });
        }
    }

    public interface OnItemClickListener{
        void OnItemClick(View view, int position,String id);
    }
    private OnItemClickListener OnItemClickListener;
    public void SetOnItemClickListener(OnItemClickListener onItemClickListener){
        OnItemClickListener =onItemClickListener;
    }
}
