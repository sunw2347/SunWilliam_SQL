package com.example.sunw2347.mycontactapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    EditText editAddress;
    EditText editPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editText_name);
        editAddress = findViewById(R.id.editText_address);
        editPhone = findViewById(R.id.editText_phone);

        myDb = new DatabaseHelper(this);
        Log.d("MyContactApp", "MainActivity: instantiated myDb");

    }

    public void addData(View view){
        Log.d("MyContactApp", "MainActivity: add contact button pressed");

        int isInserted = myDb.insertData(editName.getText().toString(), editAddress.getText().toString(), editPhone.getText().toString());
        if(isInserted == 1){
            Log.d("MyContactApp", "MainActivity: contact inserted");
            Toast.makeText(MainActivity.this, "Success - contact inserted", Toast.LENGTH_LONG);
        }
        else{
            Log.d("MyContactApp", "MainActivity: contact not inserted");
            Toast.makeText(MainActivity.this, "Failed - contact not inserted", Toast.LENGTH_LONG);

        }
    }

}
