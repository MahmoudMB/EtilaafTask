package com.example.mahmoudbahaa.etilaaftask.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mahmoudbahaa.etilaaftask.R;
import com.example.mahmoudbahaa.etilaaftask.utilities.ui.story.StoryDetail;
import com.example.mahmoudbahaa.etilaaftask.models.Story;

import java.util.Date;
import java.util.List;

/**
 * Created by MahmoudBahaa on 30/12/2018.
 */

public class StoryListAdapter  extends RecyclerView.Adapter<StoryListAdapter.StoryListHolder> {

    private  Context context;
    private List<Story> stories;


    public StoryListAdapter(List<Story> stories, Context context) {
        this.stories=stories;
        this.context = context;
      //  mOnClickListener = listener;

    }


    @Override
    public StoryListHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_story_list, parent, false);
        return new StoryListHolder(v);
    }


    @Override
    public void onBindViewHolder(StoryListHolder holder, final int position) {

       /* if(photos.get(position).isClose())
            holder.name.setText(photos.get(position).getName());
        */

        Story story = stories.get(position);
       holder.title.setText(story.getTitle());

        Glide.with(context).load(story.getUrlToImage()).into(holder.photo);


        Log.v("d",story.getPublishedAt() + "");


        holder.cratedAt.setText(setTime(story.getPublishedAt()));


holder.item.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, StoryDetail.class);
        intent.putExtra("storyObj",stories.get(position));
        context.startActivity(intent);
    }
});



    }
    @Override
    public int getItemCount() {

        return stories.size();
    }

    public class StoryListHolder extends RecyclerView.ViewHolder  {
        TextView title;
        TextView cratedAt;
        ImageView photo;
LinearLayout item;


        public StoryListHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.StoryList_title);
            cratedAt = itemView.findViewById(R.id.StoryList_cratedAt);
            photo = itemView.findViewById(R.id.StoryList_photo);
          item = itemView.findViewById(R.id.StoryList_item);
        }


    }



    public int daysBetween( Date d2,Date d1){
        return (Math.abs((int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60*24 ))));
    }

    public int monthsBetween( Date d2,Date d1){
        return (Math.abs((int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 7 ))));
    }

    public int hourBetween( Date d2,Date d1){
        return (Math.abs((int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 ))));
    }

    public int minuteBetween( Date d2,Date d1){
        return (Math.abs((int)( (d2.getTime() - d1.getTime()) / (1000 * 60 ))));
    }




    public String setTime(Date date){


        Date now = new Date();
        int months = monthsBetween(now,date);
        int days = daysBetween(now,date);
        int hours = hourBetween(now,date);
        int minutes = minuteBetween(now,date);




        if (months != 0){

            return months + " months ago";
        }

        else if (days!=0)
        {
            return days + " days ago";
        }
        else if (hours!=0){
            return hours + " hours ago";
        }

            else
        {
            return minutes + " minutes ago";
        }



    }




}
