package com.example.mahmoudbahaa.etilaaftask.utilities.ui.story;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.mahmoudbahaa.etilaaftask.R;
import com.example.mahmoudbahaa.etilaaftask.adapters.StoryListAdapter;
import com.example.mahmoudbahaa.etilaaftask.data.DBHelper;
import com.example.mahmoudbahaa.etilaaftask.models.Story;
import com.example.mahmoudbahaa.etilaaftask.utilities.OnEventListener;
import com.example.mahmoudbahaa.etilaaftask.utilities.StoryService;
import com.example.mahmoudbahaa.etilaaftask.utilities.UpdateUi;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StoriesFragment extends Fragment implements UpdateUi  {

    private static final String ARG_category = "category";
    private static final String ARG_type = "type";
       private StoryListAdapter storyListAdapter;






   private ArrayList<Story> stories;


    View rootView;
    int page = 0;
    int pageSize = 10;
    boolean isLoading = false ;



    @BindView(R.id.Stories_Stories_List)
    RecyclerView storiesRecyclerView;

    @BindView(R.id.Stories_LoadItems)
    ProgressBar loadItemsPrograss;


    private DBHelper helper;

        private String category = "general";
        private String type = "";

    public StoriesFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
        category= getArguments().getString(ARG_category);
            type= getArguments().getString(ARG_type);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_stories, container, false);
        ButterKnife.bind(this,rootView);
        helper = new DBHelper(getActivity());



        initStoriesRecyclerView();


        if (type.equals("live")) {
            loadStories();
            loadMoreStories();
        }
        else
        {
            loadStoriesFromDB();
        }

        return rootView;
    }









    void initStoriesRecyclerView(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        storiesRecyclerView.setLayoutManager(layoutManager);
        stories = new ArrayList<>();
        storyListAdapter = new StoryListAdapter(stories,getActivity());
        storiesRecyclerView.setAdapter(storyListAdapter);
    }







    void loadMoreStories()
    {


        storiesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);



                int visibleItemCount = ((LinearLayoutManager) storiesRecyclerView.getLayoutManager()).getChildCount();
                int totalItemCount = ((LinearLayoutManager) storiesRecyclerView.getLayoutManager()).getItemCount();
                int firstVisibleItemPosition = ((LinearLayoutManager) storiesRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                if (!isLoading ) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= pageSize) {
                        loadStories();

                    }

                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                    int pos = linearLayoutManager.findFirstVisibleItemPosition();

                    //  Log.v("mRecyclerView1",recyclerView.getChildAt(0).getTop()+"");
                    // Log.v("mRecyclerView2",linearLayoutManager.findFirstVisibleItemPosition() +"");



                }



            }
        });


    }

    void loadStories(){

        isLoading = true;
        loadItemsPrograss.setVisibility(View.VISIBLE);

        StoryService.getInstance(getActivity()).GetStories(category,pageSize,page,new OnEventListener<ArrayList<Story>>() {
            @Override
            public void onSuccess(ArrayList<Story> object) throws JSONException {
                stories.addAll(object);
                storyListAdapter.notifyDataSetChanged();
                isLoading  = false;
                loadItemsPrograss.setVisibility(View.GONE);
                page++;
                isLoading = false;

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }


    void loadStoriesFromDB(){

        stories.clear();

        stories.addAll(helper.getStories());
        storyListAdapter.notifyDataSetChanged();
    }

    void Refresh(){

        isLoading = true;
        page = 0;
        stories.clear();
        loadItemsPrograss.setVisibility(View.VISIBLE);

        StoryService.getInstance(getActivity()).GetStories(category,pageSize,page,new OnEventListener<ArrayList<Story>>() {
            @Override
            public void onSuccess(ArrayList<Story> object) throws JSONException {
                stories.addAll(object);
                storyListAdapter.notifyDataSetChanged();
                isLoading  = false;
                loadItemsPrograss.setVisibility(View.GONE);
                page++;
                isLoading = false;

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }


    @Override
    public void changeCategory(String Category) {
        category = Category;
        Refresh();
    }

    @Override
    public void refreshRemote() {
        Refresh();
    }

    @Override
    public void refreshLocal() {
        loadStoriesFromDB();
    }


}
