package com.example.student2.yaapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by student2 on 23.04.18.
 */

public interface GetCity {
    @GET("city.php")
    public Call<List<City>> getCity();

}
