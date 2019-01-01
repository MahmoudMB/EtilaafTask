package com.example.mahmoudbahaa.etilaaftask.utilities.ui.story;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mahmoudbahaa.etilaaftask.R;
import com.example.mahmoudbahaa.etilaaftask.data.DBHelper;
import com.example.mahmoudbahaa.etilaaftask.models.Story;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoryDetail extends AppCompatActivity {


    @BindView(R.id.StoryDetail_toolbar)
    Toolbar toolbar;



    @BindView(R.id.StoryDetail_Title)
    TextView title;


    @BindView(R.id.StoryDetail_Date)
    TextView publishedAt;

    @BindView(R.id.StoryDetail_image)
    ImageView image;


    @BindView(R.id.StoryDetail_content)
    TextView content;

    @BindView(R.id.StoryDetail_Like)
    ImageView likeStory;

    private DBHelper helper;
    Story story;
    Boolean storyLiked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);
        ButterKnife.bind(this);
        helper = new DBHelper(getApplicationContext());

        initToolbar();
        story = (Story) getIntent().getSerializableExtra("storyObj");
        CheckStoryStatus();
        initStoryFields();
    }

    void initToolbar(){
        setSupportActionBar(toolbar);
        toolbar.setContentInsetStartWithNavigation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    void initStoryFields(){

        title.setText(story.getTitle());
        content.setText(story.getContent());
        Glide.with(getApplicationContext()).load(story.getUrlToImage()).into(image);
        SimpleDateFormat format = new SimpleDateFormat("MMMM dd yyyy");
        publishedAt.setText(format.format(story.getPublishedAt()));
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



    void CheckStoryStatus(){
        storyLiked = helper.CheckForStory(story);

        if (storyLiked)
        {
            likeStory.setImageResource(R.drawable.ic_baseline_star_24px);

        }
        else {
            likeStory.setImageResource(R.drawable.ic_baseline_star_border_24px);

        }


    }

    @OnClick(R.id.StoryDetail_Like)
    void likeStory()
    {
if (storyLiked)
{

    helper.unLikeStory(story);
    storyLiked = false;
    likeStory.setImageResource(R.drawable.ic_baseline_star_border_24px);

}

else{

    helper.likeStory(story);
    storyLiked = true;
    likeStory.setImageResource(R.drawable.ic_baseline_star_24px);

}


    }

    @OnClick(R.id.StoryDetail_share)
    void share(){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Sharing Story");
        i.putExtra(Intent.EXTRA_TEXT, story.getUrl());
        startActivity(Intent.createChooser(i, "Sharing Story"));
    }

}
