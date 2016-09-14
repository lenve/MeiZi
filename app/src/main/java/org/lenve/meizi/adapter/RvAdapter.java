package org.lenve.meizi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.lenve.meizi.R;
import org.lenve.meizi.bean.Gallery;

/**
 * Created by 王松 on 2016/9/13.
 */
public class RvAdapter extends RecyclerView.Adapter {
    private Gallery gallery;
    private Context context;
    private LayoutInflater inflater;

    public RvAdapter(Context context, Gallery list) {
        this.context = context;
        this.gallery = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rv_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        Picasso.with(context).load("http://tnfs.tngou.net/image" + gallery.getTngou().get(position).getImg())
                .placeholder(R.mipmap.ic_launcher)
                .into(viewHolder.iv);
    }

    @Override
    public int getItemCount() {
        return gallery.getTngou().size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}
