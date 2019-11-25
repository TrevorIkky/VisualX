package com.io.org.visualx.core;

import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private Retrofit retrofit = null;
    private String baseUrl = " "; //TODO..insert base url

    public Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
            return retrofit;
        }
        return null;
    }


}
