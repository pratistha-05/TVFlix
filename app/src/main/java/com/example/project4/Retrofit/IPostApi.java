package com.example.project4.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IPostApi {
    @GET("?apikey=9cfa95f8&s=\"naruto\"")
    Call<Response> getPosts();
    @GET("?apikey=9cfa95f8")
    Call<Response> getPostsbyQuery(@Query("s") String search);

}
