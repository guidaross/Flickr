package com.example.guilhermedaros.flickr;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FotoAdapter extends RecyclerView.Adapter<FotoAdapter.MyViewHolder>{

    private List<CardImagem> imagens;
    private CardImagem imagem;
    private LayoutInflater layoutInflater;
    private Context context;

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    private View.OnClickListener onClickListener;

    public FotoAdapter(Context c, List<CardImagem> imagens) {
        this.imagens = imagens;
        this.layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = c;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("LOG", "onCreateViewHolder()");
        View v = layoutInflater.inflate(R.layout.gridlayout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        Picasso.with(context).load(imagens.get(position).src)
                .placeholder(R.drawable.nophoto)
                .into(holder.ivImg);

        if (imagens.get(position).title.length() > 30) {
            holder.tvTitle.setText(imagens.get(position).title.substring(0, 30) + "...");
        }
        else
            holder.tvTitle.setText(imagens.get(position).title);

        holder.ivImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagem = imagens.get(position);
                onClickListener.onClick(v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imagens.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView ivImg;
        public TextView tvTitle;


        public MyViewHolder(View itemView) {
            super(itemView);

            ivImg = (ImageView) itemView.findViewById(R.id.iv_img);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);

        }
    }

    public CardImagem getImagem() {
        return imagem;
    }
}
