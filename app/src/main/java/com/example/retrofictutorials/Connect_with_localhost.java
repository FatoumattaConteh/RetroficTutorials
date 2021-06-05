package com.example.retrofictutorials;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Connect_with_localhost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_with_localhost);
        textView =findViewById(R.id.textView);
        get_localhost_data();
    }

    TextView textView;
    private void get_localhost_data() {

        //step 1
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://localhost/retrofit/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();


        //step 2
        WebInterface webInterface=retrofit.create(WebInterface.class);


        //step 3
        Call<String> plain_text_call=webInterface.get_plain_data("http://10.0.2.2/retrofit/index.php");

        textView.setText("Loading...");


        //step 4
        plain_text_call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {



                textView.setText("SUCCESS" +response.code() +"\n\n"+response.body());

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                textView.setText("Failed because "+t.getMessage()+"\n\n"+t.getCause());
            }
        });

    }
}