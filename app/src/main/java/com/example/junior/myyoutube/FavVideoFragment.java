package com.example.junior.myyoutube;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by Junior on 17/07/2016.
 */
public class FavVideoFragment extends Fragment {

    YoutubeDB youtubeDB;
    VideoAdaptateur videoAdaptateur;
    Video videos;
    ArrayList<Video> lesVideos;


    RecyclerView recyclerView;

    public static FavVideoFragment newInstance() {
        return new FavVideoFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fav,container,false);
        recyclerView = (RecyclerView)view.findViewById(R.id.video_fav_recycle);
        youtubeDB = new YoutubeDB(getContext());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lesVideos = youtubeDB.getFav();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        videoAdaptateur = new VideoAdaptateur(lesVideos);
        recyclerView.setAdapter(videoAdaptateur);

    }
}
