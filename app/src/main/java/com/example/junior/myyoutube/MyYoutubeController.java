package com.example.junior.myyoutube;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Junior on 17/07/2016.
 */
public class MyYoutubeController extends AppCompatActivity {
    /*@BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout dLayout;

    @BindView(R.id.nvView)
    NavigationView navigationView;*/

    VideoService videoService;
    Toolbar toolbar;
    DrawerLayout dLayout;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_youtube);
        toolbar= (Toolbar)findViewById(R.id.toolbar);
        dLayout= (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView= (NavigationView)findViewById(R.id.nvView);


        videoService = VideoWebService.get();
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        setupDrawerContent(navigationView);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dLayout.openDrawer(navigationView);
            }
        });

        Fragment fragment = VideoFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent,fragment).commit();


    }


    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });
    }

    public void selectDrawerItem(MenuItem menuItem){
        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.nav_first_fragment:
                try {
                    fragment = FavVideoFragment.newInstance();
                }catch (Exception e){
                    e.printStackTrace();
                }
            case R.id.nav_second_fragment:
                try {
                    fragment = VideoFragment.newInstance();
                }catch (Exception e){
                    e.printStackTrace();
                }
        }


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();


        menuItem.setChecked(true);

        setTitle(menuItem.getTitle());

        dLayout.closeDrawers();

    }




}
