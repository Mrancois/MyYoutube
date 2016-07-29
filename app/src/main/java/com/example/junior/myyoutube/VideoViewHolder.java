package com.example.junior.myyoutube;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * Created by Junior on 17/07/2016.
 */
public class VideoViewHolder extends RecyclerView.ViewHolder {
    /*@BindView(R.id.video_image)
    ImageView imageVideo;
    @BindView(R.id.video_description)
    TextView textDescription;
    @BindView(R.id.video_title)
    TextView titreVideo;*/
    ImageView imageVideo;
    TextView textDescription;
    TextView titreVideo;
    Video video;


    public VideoViewHolder(View itemView) {
        super(itemView);
        //ButterKnife.bind(this,itemView);
        textDescription = (TextView)itemView.findViewById(R.id.video_description);
        titreVideo = (TextView)itemView.findViewById(R.id.video_title);
        imageVideo= (ImageView)itemView.findViewById(R.id.video_image);

    }

    public void Bind(Video video){
        textDescription.setText(video.getDescription());
        titreVideo.setText(video.getName());
        Picasso.with(imageVideo.getContext()).load(video.getImageUrl()).into(imageVideo);


    }

}

