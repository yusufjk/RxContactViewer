package com.rxcontactviewer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.google.common.base.Strings;
import com.rxcontactviewer.R;
import com.rxcontactviewer.network.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private List<Contact> contactList;
    private Object contactListFiltered;
    private ContactsAdapterListener listener;

    public ContactsAdapter(Context context, List<Contact> contactList, ContactsAdapterListener listener) {
        this.context = context;
        this.contactList = contactList;
        this.listener = listener;
        this.contactListFiltered = contactList;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Contact contact = contactList.get(position);
        List<String> phones = contact.getPhones();
        List<String> managers = contact.getManagers();
        List<String> addresses = contact.getAddresses();
        holder.name.setText(contact.getName());
        holder.companyName.setText(contact.getCompanyName());
        holder.parent.setText(contact.getParent());
        nestedArrays(holder, phones, managers, addresses);
    }

    private void nestedArrays(MyViewHolder holder, List<String> phones, List<String> managers, List<String> addresses) {
        if (null == phones) {
            holder.phones.setText("-");
        } else {
            for (int i = 0; i < phones.size(); i++) {
                holder.phones.setText(phones.get(i));
            }

        }

        if (null == managers) {
            holder.managers.setText("-");
        } else {

            for (int i = 0; i < managers.size(); i++) {
                holder.managers.setText(managers.get(i));
            }
        }

        if (null == addresses) {
            holder.addresses.setText("-");
        } else {

            for (int i = 0; i < addresses.size(); i++) {
                holder.addresses.setText(addresses.get(i));
            }
        }

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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    contactListFiltered = contactList;
                }
                List<Contact> filteredList = new ArrayList<>();
                for (int i = 0; i < contactList.size(); i++) {
                    Contact contact = contactList.get(i);
                    if (!Strings.isNullOrEmpty(contact.getName()) && contact.getName().toLowerCase().contains(charString.toLowerCase())) {
                        filteredList.add(contact);
                        contactListFiltered = filteredList;
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                contactListFiltered = filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(Contact contact);
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
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(contactList.get(getAdapterPosition()));
                }
            });
        }
    }
}
