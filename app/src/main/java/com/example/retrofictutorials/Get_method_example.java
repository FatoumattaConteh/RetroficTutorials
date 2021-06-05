package com.example.retrofictutorials;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Get_method_example extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_method_example);

        bindViews();
    }

    EditText nameView;
    EditText countryView;
    EditText ageView;
    Button btn_upload;
    TextView textView;
    private void bindViews() {
        nameView=findViewById(R.id.name);
        countryView=findViewById(R.id.country);
        ageView=findViewById(R.id.age);
        textView=findViewById(R.id.text_view);
        btn_upload=findViewById(R.id.btn_upload_data);

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_upload();
            }
        });

    }

    String name,country,age;
    private void do_upload() {
        name=nameView.getText().toString();
        country=countryView.getText().toString();
        age=ageView.getText().toString();




            //step 1
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl("http://192.168.64.172/retrofit/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();


            //step 2
            WebInterface webInterface=retrofit.create(WebInterface.class);


            //step 3
            Call<String> plain_text_call=webInterface.get_method_example(name,country,age);


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