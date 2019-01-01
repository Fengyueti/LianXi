package com.example.lenovo.lx.model;

import android.os.Handler;
import android.text.TextUtils;

import com.example.lenovo.lx.api.UserApi;
import com.example.lenovo.lx.contract.IShowContract;
import com.example.lenovo.lx.entity.User;
import com.example.lenovo.lx.net.OKhttpUtils;
import com.example.lenovo.lx.net.OkhttpCallback;
import com.example.lenovo.lx.net.RequestCallback;
import com.google.gson.Gson;

import java.util.HashMap;

public class ShowModel implements IShowContract.IShowModel {
    Handler handler=new Handler();
    @Override
    public void show(HashMap<String, String> params, final RequestCallback requestCallback) {
        OKhttpUtils.getmInstance().doPost(UserApi.UserShow, params, new OkhttpCallback() {
            @Override
            public void onFailUre(String msg) {
                if(requestCallback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            requestCallback.onFailUre("网络异常，请稍后再试");
                        }
                    });
                }
            }

            @Override
            public void onSuccess(String result) {
                if(!TextUtils.isEmpty(result)){
                    requestCall(result,requestCallback);
                }
            }
        });
    }
    private void requestCall(String result, final RequestCallback requestCallback) {
        final User user = new Gson().fromJson(result, User.class);
        if(requestCallback!=null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    requestCallback.onSuccess(user);
                }
            });
        }
    }
}
