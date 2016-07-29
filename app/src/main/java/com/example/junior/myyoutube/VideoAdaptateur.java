package com.example.junior.myyoutube;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francois on 17/07/2016.
 */
public class VideoAdaptateur extends RecyclerView.Adapter<VideoViewHolder> {
    public List<Video> repos;

    public VideoAdaptateur(List<Video> repos) {
        this.repos = repos;
    }

    public VideoAdaptateur(Context context) {this (new ArrayList<Video>());
    this.elContext = context;}

    private Context elContext = null;

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_video_adapteur,parent,false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        holder.Bind(repos.get(position));
        holder.imageVideo.setOnClickListener(clickListener);
        holder.titreVideo.setOnClickListener(clickListener);
        holder.textDescription.setOnClickListener(clickListener);

        holder.imageVideo.setTag(holder);
        holder.textDescription.setTag(holder);
        holder.titreVideo.setTag(holder);



    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            VideoViewHolder holder = (VideoViewHolder)v.getTag();
            int position = holder.getPosition();

            Video video = repos.get(position);
            Fragment fragment = null;
            fragment = DetailVideoControler.newInstance(video);

            ((FragmentActivity) elContext).getSupportFragmentManager().beginTransaction()
            .replace(R.id.flContent, fragment)
            .commit();



        }


    };


    @Override
    public int getItemCount() {
        return repos.size();
    }

    public void setAll(List<Video> repos_){
        this.repos.clear();
        this.repos.addAll(repos_);;
        notifyDataSetChanged();
    }
}
