package com.io.org.visualx.core;

import com.io.org.visualx.pojo.QueryObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("sampleUrl")
    Call<List<QueryObject>> getResults(@Query("query") String query);
}
