package com.example.lenovo.lx.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.lx.R;
import com.example.lenovo.lx.entity.User;

import java.util.ArrayList;
import java.util.List;

public class MAdapter extends RecyclerView.Adapter<MAdapter.ViewHolder> {
    private Context context;
    private List<User.DataBean> list;
    public MAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<User.DataBean> list) {
        if(list!=null) {
            this.list = list;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(context).inflate(R.layout.linear, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final User.DataBean dataBean = list.get(i);
        final String[] split = dataBean.getImages().split("!");
        Glide.with(context).load(split[0]).into(viewHolder.image);
        viewHolder.title.setText(list.get(i).getTitle());
        viewHolder.price.setText(list.get(i).getPrice()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView title;
        private TextView price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            title=itemView.findViewById(R.id.title);
            price=itemView.findViewById(R.id.price);
        }
    }
}
