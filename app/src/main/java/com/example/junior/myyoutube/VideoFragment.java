package com.example.junior.myyoutube;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Junior on 17/07/2016.
 */
public class VideoFragment extends Fragment {
    VideoService videoService;
    VideoAdaptateur videoAdaptateur;
    Video video;

    //@BindView(R.id.video_recycle)
    RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_video_fragment,container,false);
        //ButterKnife.bind(this,view);
        recyclerView = (RecyclerView)view.findViewById(R.id.video_recycle);
        videoService = VideoWebService.get();

        return view;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        videoAdaptateur = new VideoAdaptateur(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(videoAdaptateur);


        videoService.getVideo().enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                videoAdaptateur.setAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {

            }
        });

    }

    public static VideoFragment newInstance(){

        return new VideoFragment();
    }
}
