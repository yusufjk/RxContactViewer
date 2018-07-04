package com.rxcontactviewer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rxcontactviewer.R;
import com.rxcontactviewer.network.model.Contact;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {
    private Context context;
    private List<Contact> contactList;

    public ContactsAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Contact contact = contactList.get(position);

        holder.name.setText(contact.getName());
        holder.companyName.setText(contact.getCompanyName());
        //  holder.phones.setText(contact.getPhones().get(position));
        // holder.managers.setText(contact.getManagers().get(position));
        holder.parent.setText(contact.getParent());
        // holder.addresses.setText(contact.getAddresses().get(position));

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_row_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, phones, companyName, managers, addresses, parent;

        MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            phones = view.findViewById(R.id.phones);
            companyName = view.findViewById(R.id.companyName);
            managers = view.findViewById(R.id.managers);
            addresses = view.findViewById(R.id.addresses);
            parent = view.findViewById(R.id.parent);
        }
    }
}
