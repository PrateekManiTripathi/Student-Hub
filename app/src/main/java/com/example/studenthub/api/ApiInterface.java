package com.example.studenthub.api;

import static com.example.studenthub.Constants.content_type;
import static com.example.studenthub.Constants.server_key;

import com.example.studenthub.model.PushNotifications;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface ApiInterface {

    @Headers({"Authorization: key="+server_key, "Content-Type:"+content_type})
    @POST("fcm/send")
    Call<PushNotifications> sendNotify(@Body PushNotifications notifications);
}
