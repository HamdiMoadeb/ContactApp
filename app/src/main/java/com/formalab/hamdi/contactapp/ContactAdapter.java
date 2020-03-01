package com.formalab.hamdi.contactapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter<Contact> {


    private Context ctx;
    private ArrayList<Contact> mydata;
    public ContactAdapter(@NonNull Context context, ArrayList<Contact> contacts) {
        super(context, R.layout.contact_item, contacts);
        this.ctx = context;
        this.mydata = contacts;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //super.getView(position, convertView, parent);

        convertView = LayoutInflater.from(ctx).inflate(R.layout.contact_item, parent, false);

        TextView name = convertView.findViewById(R.id.nameConatact);
        TextView firstchar = convertView.findViewById(R.id.TextViewF);

        Contact contact = getItem(position);
        name.setText(contact.getName());
        firstchar.setText(contact.getName().substring(0,1));

        return convertView;
    }
}
