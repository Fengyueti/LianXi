package com.example.lenovo.lx.contract;

import com.example.lenovo.lx.entity.User;
import com.example.lenovo.lx.net.RequestCallback;

import java.util.HashMap;

public interface IShowContract {
    public abstract class IShowPresenter{
        public abstract void show(HashMap<String,String> params);
    }
    interface IShowModel{
        void show(HashMap<String,String> params, RequestCallback requestCallback);
    }
    interface IShowView{
        void onKeywordError(String error);
        void onPageError(String error);
        void onFailUre(String msg);
        void onSuccess(User user);
        void onSuccessMsg(String msg);
    }
}
