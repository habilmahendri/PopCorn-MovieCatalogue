package com.example.habilmahendri.popcorn.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.habilmahendri.popcorn.DetailActivity;
import com.example.habilmahendri.popcorn.R;
import com.example.habilmahendri.popcorn.model.DataCatalog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowsTrendingAdapter extends RecyclerView.Adapter<TvShowsTrendingAdapter.ViewHolder> {

    private ArrayList<DataCatalog> tvShowItems;
    private Context context;

    public TvShowsTrendingAdapter(ArrayList<DataCatalog> tvShowItems, Context context) {
        this.tvShowItems = tvShowItems;
        this.context = context;
    }

    public ArrayList<DataCatalog> getTvShowItems() {
        return tvShowItems;
    }

    public void setTvShowItems(ArrayList<DataCatalog> tvShowItems) {
        this.tvShowItems = tvShowItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final DataCatalog items = tvShowItems.get(i);

        viewHolder.tvReleaseDate.setText(items.getFirst_air_date());
//        viewHolder.tvGenres.setText();
        String rate = String.valueOf(items.getVote_average());
        viewHolder.tvRate.setText(rate);
        viewHolder.tvTitle.setText(items.getOriginal_name());

        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w185" + items.getPoster())
                .into(viewHolder.imgPoster);

        viewHolder.listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, items.getOriginal_name(), Toast.LENGTH_SHORT).show();

                context.startActivity(new Intent(context, DetailActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return getTvShowItems().size();
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

        @BindView(R.id.list_item)
        LinearLayout listItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
