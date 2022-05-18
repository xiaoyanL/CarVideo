package com.guiying.module.ui.fragment;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aliyun.apsaravideo.sophon.Service.JWebSocketClientService;
import com.aliyun.apsaravideo.sophon.api.TestMvpPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fd.baselibrary.base.RefreshFragment;
import com.fd.baselibrary.bean.ProjectList;
import com.fd.baselibrary.network.RxCallback;
import com.guiying.module.adapter.ProductAdapter;
import com.guiying.module.main.R;
import com.guiying.module.main.R2;

import java.util.List;

import butterknife.BindView;

public class ProjectListFragment extends RefreshFragment<TestMvpPresenter> {


    public static ProjectListFragment newInstance() {
        Bundle args = new Bundle();
        ProjectListFragment fragment = new ProjectListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initEventAndData() {


    }


    @Override
    protected void lazyLoad() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_projectlist;
    }




}
