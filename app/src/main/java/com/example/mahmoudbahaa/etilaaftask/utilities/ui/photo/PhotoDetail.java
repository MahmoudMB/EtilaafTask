package com.example.mahmoudbahaa.etilaaftask.utilities.ui.photo;


import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mahmoudbahaa.etilaaftask.R;
import com.example.mahmoudbahaa.etilaaftask.data.DBHelper;
import com.example.mahmoudbahaa.etilaaftask.models.Story;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhotoDetail extends AppCompatActivity {



    @BindView(R.id.PhotoDetail_toolbar)
    Toolbar toolbar;


    @BindView(R.id.PhotoDetail_photo)
    ImageView photo;

    @BindView(R.id.expandedImageView)
    ImageView expandedImageView;



    @BindView(R.id.PhotoDetail_Title)
    TextView title;


    @BindView(R.id.PhotoDetail_publishedAt)
    TextView publishedAt;

    @BindView(R.id.PhotoDetail_summery)
    LinearLayout summery;


    @BindView(R.id.PhotoDetail_layout)
    RelativeLayout layout;



    Story story;

    Boolean enlargedImageStatus = false;



    @BindView(R.id.PhotoDetail_like)
    ImageView likPhoto;

    private DBHelper helper;
    Boolean photoLiked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);
        ButterKnife.bind(this);
        initToolbar();
        helper = new DBHelper(getApplicationContext());
        story = (Story) getIntent().getSerializableExtra("storyObj");
        CheckPhotoStatus();
        initStoryFields();


    }

    void initToolbar(){
        setSupportActionBar(toolbar);
        toolbar.setContentInsetStartWithNavigation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

  void  initStoryFields(){


      title.setText(story.getTitle());

      Glide.with(getApplicationContext()).load(story.getUrlToImage()).apply(new RequestOptions().centerCrop()).into(photo);


      Glide.with(getApplicationContext()).load(story.getUrlToImage()).apply(new RequestOptions().centerCrop()).into(expandedImageView);


      SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
      publishedAt.setText(format.format(story.getPublishedAt()));

  }

  @OnClick(R.id.PhotoDetail_photo)
     void enlargeImage(){


     if (!enlargedImageStatus)
     {
         toolbar.setVisibility(View.GONE);
         summery.setVisibility(View.GONE);
         enlargedImageStatus = true;
     }
     else {

         toolbar.setVisibility(View.VISIBLE);
         summery.setVisibility(View.VISIBLE);
         enlargedImageStatus  = false;
     }

  }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        //Back Button to navigate back to the details screen
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }


        return true;
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();


        if (enlargedImageStatus)
        {
            toolbar.setVisibility(View.VISIBLE);
            summery.setVisibility(View.VISIBLE);
            enlargedImageStatus  = false;
        }

        else {
            finish();
        }

    }







    void CheckPhotoStatus(){
        photoLiked = helper.CheckForPhoto(story);

        if (photoLiked)
        {
            likPhoto.setImageResource(R.drawable.ic_baseline_star_24px);

        }
        else {
            likPhoto.setImageResource(R.drawable.ic_baseline_star_border_24px);

        }


    }

    @OnClick(R.id.PhotoDetail_like)
    void likePhoto()
    {
        if (photoLiked)
        {

            helper.unLikePhoto(story);
            photoLiked = false;
            likPhoto.setImageResource(R.drawable.ic_baseline_star_border_24px);

        }

        else{

            helper.likePhoto(story);
            photoLiked = true;
            likPhoto.setImageResource(R.drawable.ic_baseline_star_24px);

        }


    }


    @OnClick(R.id.PhotoDetail_share)
    void share(){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Sharing Story");
        i.putExtra(Intent.EXTRA_TEXT, story.getUrl());
        startActivity(Intent.createChooser(i, "Sharing Story"));
    }





}
