package com.demoapp.presenters;

import android.util.Log;

import com.demoapp.IMainView;
import com.demoapp.models.ResponseModel;
import com.demoapp.module.IMainComponent;
import com.demoapp.service.LoginService;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mitesh on 12/10/17.
 */

public class LoginPresenter {

    IMainView iMainView;
    LoginService service;


    public LoginPresenter(IMainView iMainView, LoginService service) {
        this.iMainView = iMainView;
        this.service = service;
    }

    public void requestLogin(Map<String, String> paramMap){

        Call<ResponseModel> call = service.getLoginApi()
                .CallLogin(paramMap);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Log.e("onResponse code Login", "=> " + response.code());
                Log.e("onResponse URL Login", "=> " + response.raw().request().url());
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

                Log.e("onFailure", "=> " + t.getMessage());
                //loginView.dismissProgress();
                //loginView.showErrorDialog(t.getMessage());
            }
        });

    }
}
