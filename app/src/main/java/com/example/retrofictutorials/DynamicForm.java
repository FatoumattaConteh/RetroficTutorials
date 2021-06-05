package com.example.retrofictutorials;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class DynamicForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_form);
        
        bind_views();
    }

    Button btn_send_data;
    TextView responses;
    private void bind_views() {
        btn_send_data=findViewById(R.id.btn_send_data);
        responses=findViewById(R.id.response);
        btn_send_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               send_data();
            }
        });
    }

    private void send_data() {

        Map  <String,String> form_query=new HashMap<>();
        form_query.put("name","Kaddijatou");
        form_query.put("age","16");
        form_query.put("country","Gambia");
        form_query.put("gender","Female");
        form_query.put("religion","Islam");
        form_query.put("relation","Sister");
        form_query.put("language","MAndinka");
        //step 1
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.64.172/retrofit/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();


        //step 2
        WebInterface webInterface=retrofit.create(WebInterface.class);


        //step 3 GET
        //Call<String> plain_text_call=webInterface.dynamic_form(form_query);



        //step 3 POST
        Call<String> plain_text_call=webInterface.dynamic_form_post_method(form_query);


        //textView.setText("Loading...");


        //step 4
        plain_text_call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                        if (!response.isSuccessful()) {

                            responses.setText("Failed because" + response.code() + "\n\n" + response.errorBody());
                            return;
                        }

                responses.setText("SUCCESS:"+response.body());


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                responses.setText("Failed because "+t.getMessage());
            }
        });
    }
}