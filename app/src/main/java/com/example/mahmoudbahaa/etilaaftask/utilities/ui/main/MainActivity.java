package com.example.mahmoudbahaa.etilaaftask.utilities.ui.main;

import android.content.Context;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mahmoudbahaa.etilaaftask.utilities.ui.photo.PhotosFragment;
import com.example.mahmoudbahaa.etilaaftask.R;
import com.example.mahmoudbahaa.etilaaftask.SavedFragment;
import com.example.mahmoudbahaa.etilaaftask.adapters.CategoryListAdapter;
import com.example.mahmoudbahaa.etilaaftask.adapters.SpinnerAdapter;
import com.example.mahmoudbahaa.etilaaftask.models.Category;
import com.example.mahmoudbahaa.etilaaftask.models.Option;
import com.example.mahmoudbahaa.etilaaftask.utilities.AppConstants;
import com.example.mahmoudbahaa.etilaaftask.utilities.UpdateUi;
import com.example.mahmoudbahaa.etilaaftask.utilities.ui.story.StoriesFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements CategoryListAdapter.ListItemClickListener {





    @BindView(R.id.Main_spinner)
    Spinner spinner;


    @BindView(R.id.Main_Refresh)
    ImageView Refresh;

    @BindView(R.id.Main_Category)
    LinearLayout CategoryLayout;

    private CategoryListAdapter categoryListAdapter;
    private List<Category> categories;

    String currentCategory = "general";

    @BindView(R.id.Stories_CategoryList)
    RecyclerView categoryRecyclerView;

    Boolean initialized = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        final ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo == null || networkInfo.isConnected())
            Toast.makeText(getApplicationContext(),"You Must Connect to Internet",Toast.LENGTH_LONG);

        initCategoryRecyclerView();
        initNewsCategoryList();
        initOptionsList();


    }





    void initOptionsList(){

        spinner.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);

        List<Option> options = new ArrayList<>();

        Option e = new Option();
        e.setName("Stories");
        e.setImage(R.drawable.ic_story);
        options.add(e);
        e = new Option();
        e.setName("Photos");
        e.setImage(R.drawable.ic_photo);
        options.add(e);
        e = new Option();
        e.setName("Saved");
        e.setImage(R.drawable.ic_baseline_star_24px);
        options.add(e);


        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(MainActivity.this,R.layout.item_spinner,R.id.title,options);
        spinner.setAdapter(spinnerAdapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                openSelectedOption(i);




            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }




    private UpdateUi listener ;

    public void setListener(UpdateUi listener)
    {
        this.listener = listener ;
    }



void openSelectedOption(int option)
{


    switch (option) {


        case AppConstants.Category_Stories:
            CategoryLayout.setVisibility(View.VISIBLE);
            openStoriesFragment();
            break;

        case AppConstants.Category_Photos:
            CategoryLayout.setVisibility(View.GONE);
            openPhotosFragment();
            break;

        case AppConstants.Category_Saved:
            CategoryLayout.setVisibility(View.GONE);
            openSavedFragment();
            break;



    }

}



    void openStoriesFragment(){

        StoriesFragment f = new StoriesFragment();

        Bundle bundle = new Bundle();

        bundle.putString("category",currentCategory);
        bundle.putString("type","live");

        f.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        setListener(f);

        if (!initialized){
            fragmentManager.beginTransaction()
                    .add(R.id.Main_FragmentContainer, f)
                    .commitAllowingStateLoss();
            initialized = true;
        }

        else {
            fragmentManager.beginTransaction()
                    .replace(R.id.Main_FragmentContainer, f)
                    .commitAllowingStateLoss();
        }

    }



    void openPhotosFragment(){

        PhotosFragment f = new PhotosFragment();

        Bundle bundle = new Bundle();
        bundle.putString("category",currentCategory);
        bundle.putString("type","live");

        f.setArguments(bundle);
        setListener(f);


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.Main_FragmentContainer, f)
                .commit();



    }



    void openSavedFragment(){

        SavedFragment f = new SavedFragment();

        Bundle bundle = new Bundle();

        f.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();


        fragmentManager.beginTransaction()
                .replace(R.id.Main_FragmentContainer, f)
                .commit();

    }



    void initCategoryRecyclerView(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categories = new ArrayList<>();
        categoryListAdapter = new CategoryListAdapter(categories,getApplicationContext(),this);
        categoryRecyclerView.setAdapter(categoryListAdapter);
    }
    void initNewsCategoryList()
    {

        List<Category> categoryList = new ArrayList<>();

        Category c = new Category();
        c.setName("general");
        c.setId("1");
        c.setSelected(true);
        categoryList.add(c);

        c = new Category();
        c.setName("entertainment");
        c.setId("2");
        c.setSelected(false);
        categoryList.add(c);

        c = new Category();
        c.setName("business");
        c.setId("3");
        c.setSelected(false);
        categoryList.add(c);



        c = new Category();
        c.setName("health");
        c.setId("4");
        c.setSelected(false);
        categoryList.add(c);


        c = new Category();
        c.setName("science");
        c.setId("5");
        c.setSelected(false);
        categoryList.add(c);


        c = new Category();
        c.setName("sports");
        c.setId("6");
        c.setSelected(false);
        categoryList.add(c);


        c = new Category();
        c.setName("technology");
        c.setId("7");
        c.setSelected(false);
        categoryList.add(c);


        categories.addAll(categoryList);
        categoryListAdapter.notifyDataSetChanged();

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

        listener.changeCategory(categories.get(clickedItemIndex).getName());
    }




@OnClick(R.id.Main_Refresh)
void Refresh(){


    listener.refreshRemote();


}




}
