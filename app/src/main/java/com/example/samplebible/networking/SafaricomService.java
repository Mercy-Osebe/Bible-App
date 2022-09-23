package com.example.samplebible.networking;

import com.example.samplebible.pojos.BibleResponse;
import com.example.samplebible.pojos.TokenResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface SafaricomService {

    @Headers("Authorization: Basic YUhqbFJmUXZoMGZzekVzU3VyTkk1bEUzdWJUNGZXZHI6WEc3czZxT0MyS0tVQndxQg==")
    @GET("oauth/v1/generate?grant_type=client_credentials")
    Call<TokenResponse> getAccessToken();


    @Headers("api-key: 97519447e1b0672ce68311f1c61d7c59")
    @GET("mpesa/stkpush/v1/processrequest")
    Call<BibleResponse> makeStkPush();

}
