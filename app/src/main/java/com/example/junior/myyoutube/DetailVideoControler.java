package com.example.junior.myyoutube;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Junior on 17/07/2016.
 */
public class DetailVideoControler extends Fragment {
    String urlVideo = "";
    ImageView imageView;
    ImageButton btnFav;
    ImageButton btnLire;
    TextView textDescr;
    TextView textTitre;

    YoutubeDB youtubeDB;

    Video laVideo = null;
/*
    @BindView(R.id.imageView)
    ImageView imageView ;

    @BindView(R.id.imageButtonFav)
    Button btnFav;

    @BindView(R.id.textDescr)
    TextView textDescr;*/


    public static DetailVideoControler newInstance(){

        return new DetailVideoControler();
    }

    public static DetailVideoControler newInstance(Video video){
        DetailVideoControler detailVideoControler = new DetailVideoControler();
        Bundle args = new Bundle();
        args.putString("id",video.getId());
        args.putString("name",video.getName());
        args.putString("description",video.getDescription());
        args.putString("image",video.getImageUrl());
        args.putString("video", video.getVideoUrl());
        detailVideoControler.setArguments(args);

        return detailVideoControler;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_detail_video,container,false);

        imageView = (ImageView)view.findViewById(R.id.imageView);
        btnFav = (ImageButton)view.findViewById(R.id.imageButtonFav);
        btnLire = (ImageButton)view.findViewById(R.id.imageButtonLire);
        textDescr = (TextView)view.findViewById(R.id.textDescr);
        textTitre = (TextView)view.findViewById(R.id.textTitre);

        youtubeDB = new YoutubeDB(getContext());

        //ButterKnife.bind(this,view);
        laVideo = new Video(this.getArguments().getString("id"),this.getArguments().getString("name"),this.getArguments().getString("description"),this.getArguments().getString("image"),this.getArguments().getString("video"));
       /* laVideo.setId(this.getArguments().getString("id"));
        laVideo.setName(this.getArguments().getString("name"));
        laVideo.setDescription(this.getArguments().getString("description"));
        laVideo.setImageUrl(this.getArguments().getString("image"));
        laVideo.setVideoUrl(this.getArguments().getString("video"));*/


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Picasso.with(view.getContext()).load(this.getArguments().getString("image")).into(imageView);
        textDescr.setText(this.getArguments().getString("description"));
        textTitre.setText(this.getArguments().getString("name"));
        youtubeDB.onUpgrade(youtubeDB.getWritableDatabase(),1,2);

        urlVideo = this.getArguments().getString("video");
        btnLire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlVideo));
                startActivity(browserIntent);
            }
        });


        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!youtubeDB.isFav(laVideo))
                    youtubeDB.insererFav(laVideo);
                else {
                    AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Video deja dans les favorites");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }

                if(youtubeDB.isFav(laVideo)){
                    btnFav.setBackgroundResource(R.drawable.sombrestar);
                }
                else{
                    btnFav.setBackgroundResource(R.drawable.star);
                }
            }
        });




    }
}
