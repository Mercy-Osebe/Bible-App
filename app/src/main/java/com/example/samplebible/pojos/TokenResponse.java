package com.example.samplebible.pojos;

public class TokenResponse {

    String access_token;
    String expiry_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpiry_in() {
        return expiry_in;
    }

    public void setExpiry_in(String expiry_in) {
        this.expiry_in = expiry_in;
    }
}
