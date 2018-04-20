package com.example.student2.yaapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by student2 on 20.04.18.
 */

public interface TranslationInterface {
    @GET("translate")
    public Call<Translate> getTranslation(@Query("text") String text,@Query("lang") String lang,@Query("key") String key);

}
