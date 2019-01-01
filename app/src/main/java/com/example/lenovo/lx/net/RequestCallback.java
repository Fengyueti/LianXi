package com.example.lenovo.lx.net;

import com.example.lenovo.lx.entity.User;

public interface RequestCallback {
    void onFailUre(String msg);
    void onSuccess(User user);
    void onSuccessMsg(String msg);
}
