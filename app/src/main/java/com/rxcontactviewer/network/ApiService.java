package com.rxcontactviewer.network;

import com.rxcontactviewer.network.model.Contact;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ravi on 31/01/18.
 */

public interface ApiService {

    @GET("/bins/jz6bp")
    Single<List<Contact>> getContacts(@Query("source") String source, @Query("search") String query);
}
