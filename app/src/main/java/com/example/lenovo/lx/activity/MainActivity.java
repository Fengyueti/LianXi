package com.example.lenovo.lx.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lenovo.lx.R;
import com.example.lenovo.lx.adapter.MAdapter;
import com.example.lenovo.lx.contract.IShowContract;
import com.example.lenovo.lx.entity.User;
import com.example.lenovo.lx.net.OKhttpUtils;
import com.example.lenovo.lx.presenter.ShowPresenter;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements IShowContract.IShowView {

    private Button btn_search;
    private EditText ed_title;
    private RecyclerView gv;
    private ShowPresenter presenter;
    private MAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_search = findViewById(R.id.btn_search);
        ed_title = findViewById(R.id.ed_title);
        gv = findViewById(R.id.gv);
        gv.setLayoutManager(new LinearLayoutManager(this));
        presenter = new ShowPresenter(this);
        mAdapter = new MAdapter(this);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String keywords = ed_title.getText().toString();
                final HashMap<String, String> params = new HashMap<>();
                params.put("keywords",keywords);
                params.put("page",1+"");
                presenter.show(params);
            }
        });
    }

    @Override
    public void onKeywordError(String error) {

    }

    @Override
    public void onPageError(String error) {

    }

    @Override
    public void onFailUre(String msg) {

    }

    @Override
    public void onSuccess(User user) {
        final List<User.DataBean> data = user.getData();
        mAdapter.setList(data);
        gv.setAdapter(mAdapter);
    }

    @Override
    public void onSuccessMsg(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OKhttpUtils.getmInstance().cancelAllTask();
        presenter.destroy();
    }
}
