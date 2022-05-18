package com.guiying.module.ui.act;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fd.baselibrary.base.ToolbarActivity;
import com.guiying.module.main.R;
import com.guiying.module.main.R2;
import com.mirkowu.basetoolbar.BaseToolbar;

import butterknife.OnClick;

//设置
public class SettingActivity extends ToolbarActivity {
    @Nullable
    @Override
    protected BaseToolbar.Builder setToolbar(@NonNull BaseToolbar.Builder builder) {
        return null;
    }

    @Override
    protected int getLayoutId() {
        isStatusBar=false;
        return R.layout.activity_setting;
    }

    @Override
    protected void initialize() {

    }

    @OnClick({R2.id.info_update,R2.id.icon_back})
    public void OnClick(View v){
        if (v.getId()==R.id.info_update){  //软件更新
            finish();
        }else if (v.getId()==R.id.icon_back){  //返回
            finish();
        }
    }
}
