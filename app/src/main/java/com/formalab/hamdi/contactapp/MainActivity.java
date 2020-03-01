package com.formalab.hamdi.contactapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView mylistview;
    ArrayList<Contact> myarrylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mylistview = findViewById(R.id.mylist);

       DBAdapter dbAdapter = new DBAdapter(this);
       myarrylist = dbAdapter.afficherContact();

        ContactAdapter contactAdapter = new ContactAdapter(MainActivity.this, myarrylist);
        mylistview.setAdapter(contactAdapter);

//        Ion.with(this)
//                .load("http://10.128.2.201/formalab/all_messages.php")
//                .asJsonArray()
//                .setCallback(new FutureCallback<JsonArray>() {
//                    @Override
//                    public void onCompleted(Exception e, JsonArray result) {
//
//                        if(e != null){
//                            Toast.makeText(MainActivity.this, "Ereur : "+e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }else{
//
//                            Gson convertiseur = new Gson();
//
//                            Contact[] contacts = convertiseur.fromJson(result.toString(), Contact[].class);
//
//                            myarrylist = new ArrayList<>();
//                            for(Contact ctc : contacts){
//                                myarrylist.add(ctc);
//                            }
//
//                            ContactAdapter contactAdapter = new ContactAdapter(MainActivity.this, myarrylist);
//                            mylistview.setAdapter(contactAdapter);
//
//                        }
//                    }
//                });






//
//
//        Ion.with(this) //10.0.2.2
//                .load("http://localhost/formalab/all_messages.php")
//                .asJsonArray()
//                .setCallback(new FutureCallback<JsonArray>() {
//                    @Override
//                    public void onCompleted(Exception e, JsonArray result) {
//
//                        if (e != null) {
//                            // i information , d debug , e error , w warning
//                            Log.e("error", "Erooooor "+e.getMessage());
//                        }
//                        else {
//                            Gson convertisseur = new Gson();
//
//                            Contact[] usersArray = convertisseur.fromJson(result.toString(), Contact[].class);
//
//                            values = new ArrayList<Contact>();
//
//                            for (Contact contact : usersArray) {
//                                values.add(contact);
//                            }
//
//
//                            //listUsers.setVisibility(View.VISIBLE);
//
//                        }
//                    }
//                });

        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String phone =  myarrylist.get(i).getNumber();

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

                    startActivity(intent);
                    return;
                }else{
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE},1);

                    // Toast.makeText(Main2Activity.this, "Sorry you don't have permession", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.addcontact){
            Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.refreshContact){
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        }

        return true;
    }
}
