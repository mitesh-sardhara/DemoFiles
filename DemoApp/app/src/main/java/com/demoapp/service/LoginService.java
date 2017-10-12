package com.demoapp.service;

import com.demoapp.models.ResponseModel;
import com.demoapp.utils.Constants;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by mitesh on 12/10/17.
 */

public class LoginService {

    private LoginApi mLogiinApi;

    public LoginService(){
        super();

        //AWS4-HMAC-SHA256 Credential={{aws_access_key_id}}/20170410/us-east-1/execute-api/aws4_request, SignedHeaders=accept;content-length;content-type;host;x-amz-date, Signature=990aad8009fda078130a4243631af8f0f288fd6746abdb758ba37c21b36ed58d

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        //AWS4-HMAC-SHA256 Credential={{aws_access_key_id}}/20170410/us-east-1/execute-api/aws4_request, SignedHeaders=accept;content-length;content-type;host;x-amz-date, Signature=990aad8009fda078130a4243631af8f0f288fd6746abdb758ba37c21b36ed58d

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mLogiinApi = retrofit.create(LoginApi.class);
    }

    public LoginApi getLoginApi(){ return mLogiinApi;}

    public interface LoginApi {

        @POST(Constants.ENV+""+Constants.END_POINT_LOGIN)
        Call<ResponseModel> CallLogin(@Body Map<String, String> body);

        @POST(Constants.ENV+""+Constants.END_POINT_SIGNUP)
        Call<ResponseModel> CallSignup(@Body Map<String, String> body);

    }
}
