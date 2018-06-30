package com.rxcontactviewer.adapter;

/**
 * Created by ravi on 31/01/18.
 */

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
    private ContactsAdapterListener listener;

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Contact contact = contactList.get(position);
        holder.name.setText(contact.getName());
        holder.phones.setText(contact.getPhones().toString());
        holder.companyName.setText(contact.getCompanyName());
        holder.managers.setText(contact.getManagers().toString());
        holder.parent.setText(contact.getParent());
        holder.addresses.setText(contact.getAddresses().toString());

    }


    public ContactsAdapter(Context context, List<Contact> contactList, ContactsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.contactList = contactList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_row_item, parent, false);

        return new MyViewHolder(itemView);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, phones, companyName, managers, addresses, parent;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            phones = view.findViewById(R.id.phones);
            companyName = view.findViewById(R.id.companyName);
            managers = view.findViewById(R.id.managers);
            addresses = view.findViewById(R.id.addresses);
            parent = view.findViewById(R.id.parent);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(contactList.get(getAdapterPosition()));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public interface ContactsAdapterListener {
        void onContactSelected(Contact contact);
    }
}
