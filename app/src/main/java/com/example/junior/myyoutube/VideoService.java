package com.example.junior.myyoutube;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Junior on 17/07/2016.
 */
public interface VideoService {

    String END_POINT = "https://raw.githubusercontent.com/florent37/MyYoutube/master/";
    @GET("myyoutube.json")
    Call<List<Video>> getVideo();
}
