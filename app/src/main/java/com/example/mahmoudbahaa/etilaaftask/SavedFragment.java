package com.example.mahmoudbahaa.etilaaftask;

import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mahmoudbahaa.etilaaftask.adapters.AddPagerAdapter;
import com.example.mahmoudbahaa.etilaaftask.utilities.AppConstants;
import com.example.mahmoudbahaa.etilaaftask.utilities.UpdateUi;
import com.example.mahmoudbahaa.etilaaftask.utilities.ui.photo.PhotosFragment;
import com.example.mahmoudbahaa.etilaaftask.utilities.ui.story.StoriesFragment;

import butterknife.ButterKnife;


public class SavedFragment extends Fragment{


    View rootView;
    TabLayout tabLayout;
    ViewPager viewPager;
    AddPagerAdapter pagerAdapter;
    TabItem tabItemStory;
    TabItem tabItemPhoto;

    Boolean initialized = false;




    public SavedFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_saved, container, false);
        ButterKnife.bind(this,rootView);


        tabLayout = rootView.findViewById(R.id.tabLayout);
        tabItemStory = rootView.findViewById(R.id.tabItem_Stories);
        tabItemPhoto = rootView.findViewById(R.id.tabItem_Photos);


        init();







        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {




                switch (tab.getPosition()) {


                    case AppConstants.Category_Stories:
                        openStoriesFragment();
                        break;

                    case AppConstants.Category_Photos:
                        openPhotosFragment();
                        break;





                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



getActivity().findViewById(R.id.Main_Refresh).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        listener.refreshLocal();
    }
});


        return rootView;
    }



void init(){

    StoriesFragment f = new StoriesFragment();

    Bundle bundle = new Bundle();

    bundle.putString("type","Local");

    f.setArguments(bundle);
    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
    setListener(f);


    fragmentManager.beginTransaction()
            .add(R.id.saved_container, f)
            .commit();

}
    void openStoriesFragment(){

        StoriesFragment f = new StoriesFragment();

        Bundle bundle = new Bundle();

        bundle.putString("type","Local");

        f.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
       setListener(f);


            fragmentManager.beginTransaction()
                    .replace(R.id.saved_container, f)
                    .commit();

    }



    void openPhotosFragment(){

        PhotosFragment f = new PhotosFragment();

        Bundle bundle = new Bundle();
        bundle.putString("type","Local");

        f.setArguments(bundle);
       setListener(f);


        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.saved_container, f)
                .commit();



    }

    private UpdateUi listener ;

    public void setListener(UpdateUi listener)
    {
        this.listener = listener ;
    }




}
