package com.guiying.module.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.aliyun.apsaravideo.sophon.Service.JWebSocketClientService;
import com.fd.baselibrary.base.ToolbarFragment;
import com.fd.baselibrary.baseBean.UserBean;
import com.fd.baselibrary.utils.ImageUtil;
import com.fd.baselibrary.utils.SPManager;
import com.guiying.module.ui.act.LoginActivity;
import com.guiying.module.ui.act.SettingActivity;
import com.guiying.module.main.R;
import com.guiying.module.main.R2;
import com.makeramen.roundedimageview.RoundedImageView;
import com.mirkowu.basetoolbar.BaseToolbar;

import butterknife.BindView;
import butterknife.OnClick;

public class MeFragment extends ToolbarFragment {

    @BindView(R2.id.user_name)
    TextView userName;
    @BindView(R2.id.ic_head)
    RoundedImageView ic_head;
    public static MeFragment newInstance() {
        Bundle args = new Bundle();
        MeFragment fragment = new MeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    protected BaseToolbar.Builder setToolbar(@NonNull BaseToolbar.Builder builder) {
        return null;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void lazyLoad() {
        if (SPManager.getUserData()!=null){
            UserBean userBean=SPManager.getUserData();
           String name= userBean.getUserInfo().getName();
           String imgUrl=userBean.getUserInfo().getHeadImgUrl();
           ImageUtil.loadHeader(ic_head,imgUrl);
           userName.setText(name+"");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @OnClick({R2.id.info_sz,R2.id.logout})
    public void OnClick(View v){
        if (v.getId()==R.id.info_sz){    //设置
            startActivity(new Intent(getActivity(), SettingActivity.class));
        }else if(v.getId()==R.id.logout){  //注销
            showPermissionDialog();

        }
    }

    /**
     * 权限提示对话框
     */
    public void showPermissionDialog() {
        new AlertDialog.Builder(getActivity())
                .setTitle(com.fd.baselibrary.R.string.base_prompt_message)
                .setMessage("是否退出登录")
                .setNegativeButton(com.fd.baselibrary.R.string.base_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(com.fd.baselibrary.R.string.base_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        SPManager.setUserToken("");
                        SPManager.setIsLogin(false);
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity(). stopService(new Intent(getActivity(), JWebSocketClientService.class));
                        getActivity().finish();
                    }
                }).show();
    }
}
