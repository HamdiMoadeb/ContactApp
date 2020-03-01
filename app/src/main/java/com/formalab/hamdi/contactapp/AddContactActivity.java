package com.formalab.hamdi.contactapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class AddContactActivity extends AppCompatActivity {

    EditText nameadd, phoneadd;
    Button btnadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        nameadd = findViewById(R.id.nameadd);
        phoneadd = findViewById(R.id.phoneadd);
        btnadd = findViewById(R.id.btnadd);
        final DBAdapter dbAdapter = new DBAdapter(AddContactActivity.this);


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name  = nameadd.getText().toString();
                String phone = phoneadd.getText().toString();

                Contact contact = new Contact(name, phone);
                dbAdapter.ajouterContact(contact);
                //addContactt(contact);

                Intent intent = new Intent(AddContactActivity.this, MainActivity.class);
                startActivity(intent);



            }
        });

    }

    public void addContactt(Contact contact){

        String url = "http://10.0.2.2/formalab/add_message.php?name="+contact.getName()+"&number="+contact.getNumber();


        Ion.with(this)
                .load(url.replace(" ", "%20"))
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                        if(result != null){
                            boolean reponse = result.get("reponse").getAsBoolean();

                            if(reponse){
                                Toast.makeText(AddContactActivity.this, "Contact added succeffully", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(AddContactActivity.this, "ye frer 3andek error", Toast.LENGTH_SHORT).show();
                            }
                            Intent intent = new Intent(AddContactActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });

    }












    public void  addContact(Contact contact){
        String url = "http://192.168.137.92/formalab/add_message.php?name="+contact.getName()+"&number="+contact.getNumber();
        Ion.with(this)
                .load(url.replace(" ", "%20"))
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error

                        if(result != null){
                            boolean reponse = result.get("reponse").getAsBoolean();

                            if(reponse){
                                Toast.makeText(AddContactActivity.this,"Contact added with success !",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(AddContactActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                                Toast.makeText(AddContactActivity.this,"Insert Problem",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
