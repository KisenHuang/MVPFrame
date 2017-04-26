package com.kisen.mvpframe.list;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.kisen.mvplib.model.ModelException;
import com.kisen.mvplib.model.ModelResult;
import com.kisen.mvplib.model.ResultAnalysis;
import com.kisen.mvplib.view.MvpActivity;
import com.kisen.mvpframe.R;

public class ListActivity extends MvpActivity<TestListPresenter> implements View.OnClickListener {

    private View btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }

    @Override
    public void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(getPresenter().getAdapter());
        btnSave = findViewById(R.id.btn_commit);
    }

    @Override
    public void initData() {
        getPresenter().onRefresh(101, null);
        getPresenter().setSelect("10");
    }

    @Override
    public void initListener() {
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onModelComplete(ModelResult result) {
        super.onModelComplete(result);
        result.analysis(new ResultAnalysis.DefResultAnalysis(){
            @Override
            public void success(int reqCode, Bundle args) {
                switch (reqCode) {
                    case 101:
                        Toast.makeText(mContext, "列表加载完毕", Toast.LENGTH_SHORT).show();
                        break;
                    case 201:
                        String ids = args.getString("ids");
                        Toast.makeText(mContext, "提交成功 " + ids, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    @Override
    protected void handleError(ModelException e) {
//            switch (e.getErrorType()) {
//                case ModelException.ERROR_NET_NONE:
//                    break;
//                case ModelException.ERROR_NET_UNSTABLE:
//                    break;
//                case ModelException.ERROR_NET_SERVER:
//                    break;
//                case ModelException.ERROR_IO_CACHE:
//                    break;
//                case ModelException.ERROR_IO_LOCAL:
//                    break;
//            }
    }

    @Override
    public void openLoadingAnim() {
        super.openLoadingAnim();
    }

    @Override
    public void closeLoadingAnim() {
        super.closeLoadingAnim();
    }

    @Override
    public TestListPresenter newPresenter() {
        return new TestListPresenter();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_commit) {
            getPresenter().commit(201);
        }
    }
}
