package com.example.lenovo.lx.net;

import android.os.Handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OKhttpUtils {
    private Handler handler=new Handler();
    private final OkHttpClient okHttpClient;
    public static OKhttpUtils mInstance;
    public OKhttpUtils() {
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build();
    }

    public static OKhttpUtils getmInstance() {
        if(mInstance==null){
            synchronized (OKhttpUtils.class){
                if(mInstance==null){
                    mInstance=new OKhttpUtils();
                }
            }
        }
        return mInstance;
    }
    public void doPost(String url, HashMap<String,String> params, final OkhttpCallback okhttpCallback){
        final FormBody.Builder formbody = new FormBody.Builder();
        for (Map.Entry<String, String> map : params.entrySet()) {
            formbody.add(map.getKey(),map.getValue());
        }
        final Request request = new Request.Builder().url(url).post(formbody.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(okhttpCallback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okhttpCallback.onFailUre("网络异常");
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                final int code = response.code();
                if(okhttpCallback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(200==code){
                                okhttpCallback.onSuccess(result);
                            }
                        }
                    });
                }
            }
        });
    }
    public void cancelAllTask(){
        if(okHttpClient!=null){
            okHttpClient.dispatcher().cancelAll();
        }
    }
}
