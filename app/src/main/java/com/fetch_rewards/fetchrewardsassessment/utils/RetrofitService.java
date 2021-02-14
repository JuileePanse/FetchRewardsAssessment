package com.fetch_rewards.fetchrewardsassessment.utils;

import com.fetch_rewards.fetchrewardsassessment.WebAPIService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static final String BASE_URL = "http://fetch-hiring.s3.amazonaws.com";
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static WebAPIService getInterface() {
        return retrofit.create(WebAPIService.class);
    }
}

