package com.example.samplebible.networking;

import com.example.samplebible.pojos.Bible;
import com.example.samplebible.pojos.BibleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface BibleService {

    @Headers("api-key: 97519447e1b0672ce68311f1c61d7c59")
    @GET("/v1/bibles")
    Call<BibleResponse> listBibles();
}