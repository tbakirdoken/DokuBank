package com.tubili.dokubank.Fragments;


import com.tubili.dokubank.Notifications.MyResponse;
import com.tubili.dokubank.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAtMRDU8A:APA91bGQCo0lcJhR7oZECPqna6KZSGxKkIJhstrebCkw3yGiJ21uPqs_Cj3B471kktLNQq8FnYBXb_-67cEOIfTx3YJ68OFCtfioxoPWN8rzYX9ISUO2tDlQdGJuqnWuuYHw-t55i9N5"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
