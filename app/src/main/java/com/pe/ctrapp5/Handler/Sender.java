package com.pe.ctrapp5.Handler;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Sender {

    private static Sender instance;

    private Srv01 apiService;


    //public String url="http://192.168.0.101:8085/";

    public String url="http://192.168.128.26:8085/";

    //public String url="https://ctrapi8-hxd5cqbxfuebabdv.mexicocentral-01.azurewebsites.net/";



    public static Sender getInstance() {
        if (instance == null) {
            instance = new Sender();
        }
        return instance;
    }

    public Sender() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(Srv01.class);
    }

    public Srv01 connect() {
        return apiService;
    }

}
