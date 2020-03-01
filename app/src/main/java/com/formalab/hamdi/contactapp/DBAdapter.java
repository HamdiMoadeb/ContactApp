package com.formalab.hamdi.contactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBAdapter extends SQLiteOpenHelper {


    public DBAdapter(Context context) {
        super(context, "formalab", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE contact(id integer Primary key, number text, name text);";
        db.execSQL(createTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String deleteTable = "DROP Table IF EXISTS contact;";
        db.execSQL(deleteTable);
        onCreate(db);
    }

    public void ajouterContact(Contact contact){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("number", contact.getNumber());
        contentValues.put("name", contact.getName());

        db.insert("contact", null, contentValues);
    }

    public ArrayList<Contact> afficherContact(){

        SQLiteDatabase db = getReadableDatabase();

        String selectall = "SELECT * FROM contact";

        Cursor cursor = db.rawQuery(selectall, null);

        ArrayList<Contact> arrayList = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{

                Contact contact = new Contact();
                contact.setName(cursor.getString(2));
                contact.setNumber(cursor.getString(1));

                arrayList.add(contact);

            }while(cursor.moveToNext());
        }

        return arrayList;
    }








    public void addContact(Contact contact){

        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("number", contact.getNumber());
        values.put("name", contact.getName());

        database.insert("contact", null, values);

    }

    public ArrayList<Contact> getAllContact(){
        ArrayList<Contact> arrayList = new ArrayList<>();

        String selectall = "SELECT * FROM contact ";
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(selectall, null);

        if (cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.setNumber(cursor.getString(1));
                contact.setName(cursor.getString(2));
                contact.setId(cursor.getInt(0));

                arrayList.add(contact);
            }while (cursor.moveToNext());
        }

        return arrayList;

    }
}