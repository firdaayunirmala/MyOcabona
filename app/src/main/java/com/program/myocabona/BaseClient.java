package com.program.myocabona;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseClient {
    private final static String BaseUrl = "https://www.themealdb.com/api/json/v1/1/";
    private static Retrofit retrofitEndPoint;

    public static Retrofit getBaseClient(){
        if(retrofitEndPoint == null){
            retrofitEndPoint = new Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitEndPoint;
    }
}
