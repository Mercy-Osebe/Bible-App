package com.example.samplebible.networking;

import com.example.samplebible.pojos.BibleResponse;
import com.example.samplebible.pojos.StkPushRequest;
import com.example.samplebible.pojos.StkPushResponse;
import com.example.samplebible.pojos.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SafaricomService {

    @Headers("Authorization: Basic YUhqbFJmUXZoMGZzekVzU3VyTkk1bEUzdWJUNGZXZHI6WEc3czZxT0MyS0tVQndxQg==")
    @GET("oauth/v1/generate?grant_type=client_credentials")
    Call<TokenResponse> getAccessToken();

    @POST("mpesa/stkpush/v1/processrequest")
    Call<StkPushResponse> makeStkPush(@Header("Authorization") String authorization, @Body StkPushRequest stkPushRequest);

}
