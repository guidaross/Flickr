package com.example.guilhermedaros.flickr;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface FlickrAPI {
    @GET("/services/feeds/photos_public.gne?format=json&nojsoncallback=1")
    Call<FlickrFeed> loadPhotos(@Query("tags") String tags);
    @GET("/services/feeds/photos_public.gne?format=json&nojsoncallback=1")
    Call<FlickrFeed> loadPhotos();
}
