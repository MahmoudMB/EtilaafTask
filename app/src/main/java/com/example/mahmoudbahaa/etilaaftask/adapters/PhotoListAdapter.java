package com.example.mahmoudbahaa.etilaaftask.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mahmoudbahaa.etilaaftask.utilities.ui.photo.PhotoDetail;
import com.example.mahmoudbahaa.etilaaftask.R;
import com.example.mahmoudbahaa.etilaaftask.models.Story;

import java.util.List;

/**
 * Created by MahmoudBahaa on 30/12/2018.
 */

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.PhotoListHolder> {


    private final Context context;
    private List<Story> stories;

    public PhotoListAdapter(List<Story> stories, Context context) {
        this.stories=stories;
        this.context = context;

    }


    @Override
    public PhotoListHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photo_list, parent, false);
        return new PhotoListHolder(v);
    }



    @Override
    public void onBindViewHolder(PhotoListHolder holder, final int position) {



         Story story = stories.get(position);
//(new RequestOptions().override(100, 100))

        Glide.with(context).load(story.getUrlToImage()).apply(new RequestOptions().centerCrop()).into(holder.photo);


        holder.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PhotoDetail.class);
                intent.putExtra("storyObj",stories.get(position));
                context.startActivity(intent);
            }
        });



    }
    @Override
    public int getItemCount() {

        return stories.size();
    }

    public class PhotoListHolder extends RecyclerView.ViewHolder  {

        ImageView photo;



        public PhotoListHolder(View itemView) {
            super(itemView);

            photo = itemView.findViewById(R.id.PhotoList_photo);

        }


    }











}
