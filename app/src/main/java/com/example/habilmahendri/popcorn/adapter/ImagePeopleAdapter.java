package com.example.habilmahendri.popcorn.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.habilmahendri.popcorn.R;
import com.example.habilmahendri.popcorn.model.DataCatalog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImagePeopleAdapter extends RecyclerView.Adapter<ImagePeopleAdapter.ViewHolder> {
    private ArrayList<DataCatalog> dataCatalogs;
    private Context context;

    public ImagePeopleAdapter(ArrayList<DataCatalog> dataCatalogs, Context context) {
        this.dataCatalogs = dataCatalogs;
        this.context = context;
    }

    @NonNull
    @Override
    public ImagePeopleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_image_people, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagePeopleAdapter.ViewHolder viewHolder, int i) {
        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w185" + dataCatalogs.get(i).getFile_path())
                .into(viewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return dataCatalogs.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.image_gallery)ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
