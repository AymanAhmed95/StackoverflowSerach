package com.example.root.stackoverflowsearch;

import com.example.root.stackoverflowsearch.Models.Respone;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by root on 3/2/18.
 */


public interface StackoverflowService {

    @GET("search")
    public Call<Respone> search(@Query("intitle") String query,
                                @Query("site") String site);
}
