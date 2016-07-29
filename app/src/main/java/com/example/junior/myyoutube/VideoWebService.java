package com.example.junior.myyoutube;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Junior on 28/07/2016.
 */
public class VideoWebService {
    static  VideoWebService INSTANCE;
    final VideoService videoService;

    public VideoWebService() {
        this.videoService = new Retrofit.Builder()
            .baseUrl(VideoService.END_POINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VideoService.class);
    }

    public static VideoService get() {
        if(INSTANCE == null){
            INSTANCE = new VideoWebService();
        }
        return INSTANCE.videoService;
    }
}
