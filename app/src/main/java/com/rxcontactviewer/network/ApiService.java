package com.rxcontactviewer.network;

import com.rxcontactviewer.network.model.ContactWrapper;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET(" https://api.myjson.com/bins/jz6bp")
    Call<ContactWrapper> getContacts();
}
