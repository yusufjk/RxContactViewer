package com.rxcontactviewer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rxcontactviewer.R;
import com.rxcontactviewer.network.model.Contact;

import java.util.ArrayList;
import java.util.Arrays;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    private ArrayList<Contact> contacts;

    public ContactsAdapter(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder holder, int position) {
        holder.name.setText(contacts.get(position).getName());
        holder.companyName.setText(contacts.get(position).getCompanyName());
        holder.parent.setText(contacts.get(position).getParent());
        holder.managers.setText(Arrays.toString(contacts.get(position).getManagers()));
        holder.addresses.setText(Arrays.toString(contacts.get(position).getAddresses()));
        holder.phones.setText(Arrays.toString(contacts.get(position).getPhones()));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView managers;
        private TextView name;
        private TextView companyName;
        private TextView phones;
        private TextView addresses;
        private TextView parent;

        ViewHolder(View itemView) {
            super(itemView);

            managers = itemView.findViewById(R.id.managers);
            addresses = itemView.findViewById(R.id.addresses);
            phones = itemView.findViewById(R.id.phones);
            name = itemView.findViewById(R.id.name);
            companyName = itemView.findViewById(R.id.companyName);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
