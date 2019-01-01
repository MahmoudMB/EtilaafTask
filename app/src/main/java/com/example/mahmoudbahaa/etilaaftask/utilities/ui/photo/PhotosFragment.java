package com.example.mahmoudbahaa.etilaaftask.utilities.ui.photo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.mahmoudbahaa.etilaaftask.R;
import com.example.mahmoudbahaa.etilaaftask.adapters.PhotoListAdapter;
import com.example.mahmoudbahaa.etilaaftask.data.DBHelper;
import com.example.mahmoudbahaa.etilaaftask.models.Story;
import com.example.mahmoudbahaa.etilaaftask.utilities.OnEventListener;
import com.example.mahmoudbahaa.etilaaftask.utilities.StoryService;
import com.example.mahmoudbahaa.etilaaftask.utilities.UpdateUi;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotosFragment extends Fragment implements UpdateUi {


    private static final String ARG_category = "category";
    private static final String ARG_type = "type";



    private PhotoListAdapter storyListAdapter;


    private ArrayList<Story> stories;

    private DBHelper helper;
    private String category = "general";
    private String type = "";

    View rootView;
    int page = 0;
    int pageSize = 10;
    boolean isLoading = false ;


    @BindView(R.id.Photo_RecyclerView)
    RecyclerView photoRecyclerView;

    @BindView(R.id.photos_LoadItems)
    ProgressBar loadItemsPrograss;


    public PhotosFragment() {
        // Required empty public constructor
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

        rootView = inflater.inflate(R.layout.fragment_photos, container, false);

        ButterKnife.bind(this,rootView);
        helper = new DBHelper(getActivity());


        initPhotosRecyclerView();

        if (type.equals("live")) {
            loadPhotos();
            loadMorePhotos();
        }
        else {
            loadPhotosFromDB();
        }

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event





    void initPhotosRecyclerView(){
      GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity(),3,GridLayoutManager.VERTICAL,false);
        photoRecyclerView.setLayoutManager(mGridLayoutManager);
        stories = new ArrayList<>();
        storyListAdapter = new PhotoListAdapter(stories,getActivity());
        photoRecyclerView.setAdapter(storyListAdapter);
    }




    void loadMorePhotos()
    {

        final  GridLayoutManager layoutManager = (GridLayoutManager) photoRecyclerView.getLayoutManager();
        photoRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);



                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading ) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= pageSize) {
                        loadPhotos();

                    }




                }



            }
        });


    }

    void loadPhotos(){

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
        loadPhotosFromDB();
    }


    void loadPhotosFromDB(){
        stories.clear();
        stories.addAll( helper.getPhotos());
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



}
