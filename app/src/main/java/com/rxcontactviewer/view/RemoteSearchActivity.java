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
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.rxcontactviewer.R;
import com.rxcontactviewer.adapter.ContactsAdapter;
import com.rxcontactviewer.network.ApiClient;
import com.rxcontactviewer.network.ApiService;
import com.rxcontactviewer.network.model.Contact;
import com.rxcontactviewer.network.model.ContactList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RemoteSearchActivity extends AppCompatActivity {

    private static final String TAG = RemoteSearchActivity.class.getSimpleName();
    @BindView(R.id.input_search)
    EditText inputSearch;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private ApiService apiService;
    private ContactsAdapter mAdapter;
    private List<Contact> contactsList = new ArrayList<>();
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_search);
        unbinder = ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }

        mAdapter = new ContactsAdapter(this, contactsList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        whiteNotificationBar(recyclerView);

        apiService = ApiClient.getClient().create(ApiService.class);
        Observable<ContactList> contactObservable = apiService.getContacts().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        contactObservable.subscribe(new Observer<ContactList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ContactList contactList) {
                List<Contact> contacts = contactList.getContactList();
                for (int i = 0; i < contacts.size(); i++) {
                    Contact contact = new Contact();
                    contact.setName(contacts.get(i).getName());
                    contact.setCompanyName(contacts.get(i).getCompanyName());
                    contact.setPhones(contacts.get(i).getPhones());
                    contact.setParent(contacts.get(i).getParent());
                    contact.setManagers(contacts.get(i).getManagers());
                    contact.setAddresses(contacts.get(i).getAddresses());
                    contactsList.add(contact);
                }

                mAdapter = new ContactsAdapter(RemoteSearchActivity.this, contactsList);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
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