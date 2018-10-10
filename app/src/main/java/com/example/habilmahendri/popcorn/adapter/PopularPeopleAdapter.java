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

public class PopularPeopleAdapter extends RecyclerView.Adapter<PopularPeopleAdapter.ViewHolder> {

    private ArrayList<DataCatalog> dataCatalogs;
    private Context context;


    public PopularPeopleAdapter(ArrayList<DataCatalog> dataCatalogs, Context context) {
        this.dataCatalogs = dataCatalogs;
        this.context = context;
    }


    @NonNull
    @Override
    public PopularPeopleAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_people, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PopularPeopleAdapter.ViewHolder viewHolder, int i) {

        viewHolder.name.setText(dataCatalogs.get(i).getName());
        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w185" + dataCatalogs.get(i).getProfile_path())
                .into(viewHolder.imageView);


    }

    @Override
    public int getItemCount() {
        return dataCatalogs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.people_name)TextView name;
        @BindView(R.id.people_image)
        ImageView imageView;

        public ViewHolder(View itemView) {

            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

}
