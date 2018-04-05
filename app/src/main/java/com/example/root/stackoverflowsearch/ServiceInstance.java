package com.example.root.stackoverflowsearch;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 3/2/18.
 */

public class ServiceInstance {
    private static Retrofit retrofit = null;
    private static StackoverflowService service = null;
    private static String baseUrl = "https://api.stackexchange.com/2.2/";

    public static StackoverflowService getServiceInstance(){
        if(service == null){
            service = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build().create(StackoverflowService.class);
        }
        return service;
    }
}
