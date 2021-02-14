package com.fetch_rewards.fetchrewardsassessment;

import com.fetch_rewards.fetchrewardsassessment.models.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebAPIService {

        @GET("/hiring.json")
        Call<List<Item>> getItems();
}
