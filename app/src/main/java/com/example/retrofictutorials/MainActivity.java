package com.example.retrofictutorials;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void open_introduction(View view) {
        Intent i=new Intent(this,Introduction.class);
        startActivity(i);
    }

    public void open_connect_with_localhost(View view) {
        Intent i=new Intent(this,Connect_with_localhost.class);
        startActivity(i);
    }

    public void open_Get_method_example(View view) {
        Intent i=new Intent(this,Get_method_example.class);
        startActivity(i);
    }

    public void open_Post_method_example(View view) {
        Intent i=new Intent(this, post_method_example.class);
        startActivity(i);
    }

    public void open_Dynamic_form(View view) {
        Intent i=new Intent(this, DynamicForm.class);
        startActivity(i);
    }

    public void open_upload_file(View view) {
        Intent i=new Intent(this, Upload_file.class);
        startActivity(i);
    }
}