package com.example.retrofictutorials;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface WebInterface {

    @GET
    Call <String> get_plain_data(@Url String url);

    @GET("get_method_example.php")
    Call<String> get_method_example(
            @Query("name")String name,
            @Query("country")String country,
            @Query("age")String age
    );


    @FormUrlEncoded
    @POST("post_method_example.php")
    Call<String> post_method_example(
            @Field("name") String name,
            @Field("country") String country,
            @Field("age") String age
    );

    @GET("dynamic_form.php")
    Call<String> dynamic_form(
            @QueryMap Map<String,String> data
    );

    @FormUrlEncoded
    @POST("dynamic_form.php")
    Call<String> dynamic_form_post_method(
            @FieldMap Map<String,String> data
    );

    @Multipart
    @POST("upload_files.php")
    Call<String> upload_files(
            @Part MultipartBody.Part photo
            );
}
