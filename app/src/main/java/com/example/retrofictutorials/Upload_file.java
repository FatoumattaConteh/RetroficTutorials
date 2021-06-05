package com.example.retrofictutorials;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Multipart;

public class Upload_file extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_file);

        bind_views();
    }

    TextView data;
    private void bind_views() {
        data=findViewById(R.id.data);
        ((Button)(findViewById(R.id.pick_file))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick_file();
            }
        });
        ((Button)(findViewById(R.id.pick_file))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload_file();
            }
        });
    }

    private void upload_file() {
        //step 1
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/retrofit/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();


        //step 2
        WebInterface webInterface=retrofit.create(WebInterface.class);





        //step 3
        File file=new File("");
        RequestBody picture=RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part part=MultipartBody.Part.createFormData("photo",file.getName(),picture);

        //step 4
        Call<String> plain_text_call=webInterface.upload_files(
                part);





        //step 5
        plain_text_call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!response.isSuccessful()) {

                    data.setText("Failed because" + response.code() + "\n\n" + response.errorBody());
                    return;
                }

                data.setText("SUCCESS:"+response.body());


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                data.setText("Failed because "+t.getMessage());
            }
        });
    }




    private Uri imagepath=null;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1 && resultCode==RESULT_OK && data!=null){
            imagepath=data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imagepath);
                ((ImageView)(findViewById(R.id.image_preview))).setImageBitmap(bitmap);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void pick_file() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //HAHAHAA... this is another baby problem... no... I know... i will record a video for this tomorrow!,
        //hurray,that will help a lot of people cuz i dont seem to see the result on stackoverflow..... please dont forget my search function video pls
        // i will do so! yo
        startActivityForResult(Intent.createChooser(intent,"Select a book image"),1);
    }
}