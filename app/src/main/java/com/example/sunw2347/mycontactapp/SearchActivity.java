package com.example.sunw2347.mycontactapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Get the intent that started the activity
        android.content.Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //Set the string in TextView
        TextView textView = findViewById(R.id.textView4);
        textView.setText("\"" + message + "\"");


        DatabaseHelper myDb;
        myDb = new DatabaseHelper(this);
        Cursor res = myDb.getAllData();

        if(res.getCount() == 0){
            Log.d("MyContactApp", "MainActivity: searchData - no contact found");
            showSearch("Error","No contact found in database");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()) {
            if (res.getString(1).contains(message) || res.getString(2).contains(message) || res.getString(3).contains(message)) {
                buffer.append("Item Number: " + res.getString(0) + "\n" + "Name: " + res.getString(1) + "\n" + "Address: " + res.getString(2) + "\n" + "Phone Number: " + res.getString(3) + "\n\n");
            }

        }
        if(buffer.toString().length() > 0) {
            showSearch("Found Contacts", buffer.toString());
        }
        else{
            buffer.append("No contact containing \"" + message + "\" was found.");
            showSearch("Error", buffer.toString());
        }

    }

    private void showSearch(String title, String message) {
        Log.d("MyContactApp", "MainActivity: assembling AlertDialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
