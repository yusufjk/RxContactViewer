package com.rxcontactviewer.view;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.rxcontactviewer.R;
import com.rxcontactviewer.adapter.ContactsAdapter;
import com.rxcontactviewer.app.Const;
import com.rxcontactviewer.network.ApiService;
import com.rxcontactviewer.network.model.Contact;
import com.rxcontactviewer.network.model.ContactWrapper;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteSearchActivity extends AppCompatActivity {

    private static final String TAG = RemoteSearchActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private ArrayList<Contact> contacts;
    private ContactsAdapter adapter;

    @BindView(R.id.input_search)
    EditText inputSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_search);
        Unbinder unbinder = ButterKnife.bind(this);
        initViews();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        whiteNotificationBar(recyclerView);

    }

    private void initViews() {
        recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        loadJSON();
    }

    private void loadJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService request = retrofit.create(ApiService.class);
        Call<ContactWrapper> call = request.getContacts();
        call.enqueue(new Callback<ContactWrapper>() {
            @Override
            public void onResponse(Call<ContactWrapper> call, Response<ContactWrapper> response) {

                ContactWrapper jsonResponse = response.body();
                contacts = new ArrayList<>(Arrays.asList(jsonResponse.getContacts()));
                adapter = new ContactsAdapter(contacts);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ContactWrapper> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}