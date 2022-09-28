package com.example.samplebible;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.samplebible.databinding.ActivityMainBinding;
import com.example.samplebible.networking.BibleService;
import com.example.samplebible.networking.SafaricomService;
import com.example.samplebible.pojos.BibleResponse;
import com.example.samplebible.pojos.StkPushRequest;
import com.example.samplebible.pojos.StkPushResponse;
import com.example.samplebible.pojos.TokenResponse;


import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SafaricomActivity extends AppCompatActivity {
    private EditText editText;
    private Button btn;
    private ProgressBar progressBar;
    private static final String TAG = "SafaricomActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safaricom);


        progressBar = findViewById(R.id.progressBar);
        editText = findViewById(R.id.editTextPhoneNumber);
        btn = findViewById(R.id.btnSend);
        String phone = editText.getText().toString().trim();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getAccessToken();
            }
        });

    }

    private void getAccessToken() {
        progressBar.setVisibility(View.VISIBLE);
//        String keyCombined = SafaricomConfigs.CONSUMER_KEY + ":" + SafaricomConfigs.CONSUMER_SECRET;
//        String credentials = Base64.encodeToString(keyCombined.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sandbox.safaricom.co.ke/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SafaricomService safaricomService = retrofit.create(SafaricomService.class);

        Call<TokenResponse> fetchAccessToken = safaricomService.getAccessToken();
        fetchAccessToken.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                progressBar.setVisibility(View.INVISIBLE);
                TokenResponse tokenResponse = response.body();
                String token = tokenResponse.getAccess_token();
                Log.d(TAG, "token" + token);

                sendStkPush(token);

            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.d(TAG, t.getMessage());

                Toast.makeText(SafaricomActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void sendStkPush(String token)
    {

        progressBar.setVisibility(View.VISIBLE);
//        String keyCombined = SafaricomConfigs.CONSUMER_KEY + ":" + SafaricomConfigs.CONSUMER_SECRET;
//        String credentials = Base64.encodeToString(keyCombined.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);

        String businessShortCode = SafaricomConfigs.BusinessShortCode;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sandbox.safaricom.co.ke/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SafaricomService safaricomService = retrofit.create(SafaricomService.class);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date());
        String _password = businessShortCode + SafaricomConfigs.ONLINE_CHECKOUT_PASSKEY + timestamp;
        String password = Base64.encodeToString(_password.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);


        Log.d(TAG, "Timestamp => "+ timestamp);
        Log.d(TAG, "password => "+ password);
        Log.d(TAG, "token => "+ token);
        StkPushRequest request = new StkPushRequest();
        request.setBusinessShortCode(businessShortCode);
        request.setPassword(password);
        request.setTimestamp(timestamp);
        request.setTransactionType("CustomerPayBillOnline");
        request.setAmount("10");
        request.setPartyB(businessShortCode);
        request.setPartyA("254716630770");
        request.setPhoneNumber("254716630770");
        request.setCallBackURL("https://pledge.tindarsoft.com/payment/txn-status");
        request.setAccountReference("ref");
        request.setTransactionDesc("desc");

        Call<StkPushResponse> fetchAccessToken = safaricomService.makeStkPush("Bearer "+ token, request);
        fetchAccessToken.enqueue(new Callback<StkPushResponse>() {
            @Override
            public void onResponse(Call<StkPushResponse> call, Response<StkPushResponse> response) {
                progressBar.setVisibility(View.INVISIBLE);
                String y  = response.message();
                String x  = response.raw().body().toString();
                StkPushResponse responseStk = response.body();
                Log.d(TAG, x);
//                Log.d(TAG, responseStk.getErrorMessage());
//                Log.d(TAG, responseStk.getErrorCode());
//                Log.d(TAG, responseStk.getMerchantRequestID());

//                if(response.isSuccessful())
//                {
//                    if(response.body())
//                }


            }

            @Override
            public void onFailure(Call<StkPushResponse> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.d(TAG, t.getMessage());

                Toast.makeText(SafaricomActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}