package com.rxcontactviewer.network;

import com.rxcontactviewer.network.model.ContactList;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/bins/jz6bp")
    Observable<ContactList> getContacts();

}
