package com.example.lenovo.lx.presenter;

import com.example.lenovo.lx.contract.IShowContract;
import com.example.lenovo.lx.entity.User;
import com.example.lenovo.lx.model.ShowModel;
import com.example.lenovo.lx.net.RequestCallback;

import java.util.HashMap;

public class ShowPresenter extends IShowContract.IShowPresenter {
    private ShowModel showModel;
    private IShowContract.IShowView iShowView;

    public ShowPresenter(IShowContract.IShowView iShowView) {
        this.showModel = new ShowModel();
        this.iShowView = iShowView;
    }

    @Override
    public void show(HashMap<String, String> params) {
        if(showModel!=null){
            showModel.show(params, new RequestCallback() {
                @Override
                public void onFailUre(String msg) {
                    if(iShowView!=null){
                        iShowView.onFailUre(msg);
                    }
                }

                @Override
                public void onSuccess(User user) {
                    if(iShowView!=null){
                        iShowView.onSuccess(user);
                    }
                }

                @Override
                public void onSuccessMsg(String msg) {
                    if(iShowView!=null){
                        iShowView.onSuccessMsg(msg);
                    }
                }
            });
        }
    }
    public void destroy(){
        if (iShowView!=null){
            iShowView=null;
        }
    }
}
