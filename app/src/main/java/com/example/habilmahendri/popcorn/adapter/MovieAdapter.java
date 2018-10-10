package com.example.habilmahendri.popcorn.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.habilmahendri.popcorn.R;
import com.example.habilmahendri.popcorn.model.DataCatalog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private ArrayList<DataCatalog>dataCatalogs;
    private Context context;


    public MovieAdapter(ArrayList<DataCatalog> dataCatalogs, Context context) {
        this.dataCatalogs = dataCatalogs;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tvTitle.setText(dataCatalogs.get(i).getTitle());
        viewHolder.tvReleaseDate.setText(dataCatalogs.get(i).getRelease_date());

        String rate = String.valueOf(dataCatalogs.get(i).getVote_average());
        viewHolder.tvRate.setText(rate);
        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w185" + dataCatalogs.get(i).getPoster())
                .into(viewHolder.imgPoster);

    }

    @Override
    public int getItemCount() {
        return dataCatalogs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.img_poster)
        ImageView imgPoster;
        @BindView(R.id.tv_release_date)
        TextView tvReleaseDate;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_genres)
        TextView tvGenres;
        @BindView(R.id.tv_rate)
        TextView tvRate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
