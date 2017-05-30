package com.offcasoftware.weddingapp.view;

import com.offcasoftware.weddingapp.R;
import com.offcasoftware.weddingapp.model.Contact;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private final List<Contact> mContactList = new ArrayList<>();
    private final LayoutInflater mLayoutInflater;

    public ContactAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public ContactViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = mLayoutInflater.inflate(R.layout.row_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ContactViewHolder holder, final int position) {
        holder.mName.setText(mContactList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    public void setContactList(final List<Contact> contactList) {
        mContactList.clear();
        mContactList.addAll(contactList);
        notifyDataSetChanged();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.contact_name)
        TextView mName;

        public ContactViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
