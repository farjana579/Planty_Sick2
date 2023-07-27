package com.example.plantysick;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIinterface {
    @GET("species-list?page=1&key=sk-ymsN64c1e4d3dcbc81686")
    Call<List<Plants>> getPlants();
}
