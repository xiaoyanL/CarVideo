package com.fd.baselibrary.base;


import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.fd.baselibrary.utils.LanguageUtil;
import com.fd.baselibrary.utils.UIUtils;
import com.mirkowu.basetoolbar.BaseToolbar;
import com.mirkowu.statusbarutil.StatusBarUtil;
import com.fd.baselibrary.R;
import com.fd.baselibrary.utils.BaseSPManager;
import com.fd.baselibrary.base.BaseActivity;

import java.util.Locale;

import butterknife.ButterKnife;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public abstract class ToolbarActivity<P extends IBasePresenter> extends BaseActivity<P> {
    private BaseToolbar mBaseToolbar;
    public boolean isStatusBar = true;


    @Override
    public void bindView() {
        /*** 这里可以对Toolbar进行统一的预设置 */
        BaseToolbar.Builder builder
                = new BaseToolbar.Builder(getContext())
                .setBackButton(R.mipmap.icon_back)//统一设置返回键
                //    .setStatusBarColor(ContextUtil.getColor(R.color.colorPrimary))//统一设置颜色
                .setBackgroundColor(ContextCompat.getColor(getContext(), R.color.red_title_bg))
                .setSubTextColor(Color.RED)
                .setTitleTextColor(ContextCompat.getColor(getContext(), R.color.white));
        builder = setToolbar(builder);
        if (builder != null) {
            mBaseToolbar = builder.build();
        }
        initLocale();

        if (mBaseToolbar != null) {
            //添加Toolbar
            LinearLayout layout = new LinearLayout(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
            layout.setLayoutParams(params);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.addView(mBaseToolbar);
            View mView = getActivity().getLayoutInflater().inflate(getLayoutId(), layout, false);
            layout.addView(mView);

            setContentView(layout);

            //将toolbar设置为actionbar
            setSupportActionBar(mBaseToolbar);
        } else {
            setContentView(getLayoutId());
        }

        //设置沉浸式透明状态栏
//        StatusBarUtil.setTransparent(this);
//        StatusBarUtil.setStatusBarColor(getActivity(), ContextCompat.getColor(getContext(), R.color.colorPrimary));

        //ButterKnife
        unbinder = ButterKnife.bind(this);

        if (isStatusBar) {
            StatusBarUtil.setStatusBarColor(getActivity(), ContextCompat.getColor(getContext(), R.color.red_title_bg));
            StatusBarUtil.setStatusBarLightMode(this, 1);
            StatusBarUtil.setStatusBarLightMode(this, 2);
            StatusBarUtil.setStatusBarLightMode(this, 3);
        } else {
            StatusBarUtil.setTranslucentForImageView(getActivity(), 0, null);
        }

        //非夜间模式 要开启亮色模式
        // setStatusBarLightMode();
    }

    public void setStatusBarLightMode() {
        if (!BaseSPManager.isNightMode()) {
            if (StatusBarUtil.setStatusBarLightModeWithNoSupport(getActivity(), true)) {
                if (getToolbar() != null) getToolbar().hideStatusBar();
            }
        }
    }

    public BaseToolbar getToolbar() {
        return mBaseToolbar;
    }


    public void showToolbar() {
        if (mBaseToolbar != null) mBaseToolbar.setVisibility(View.VISIBLE);
    }

    public void hideToolbar() {
        if (mBaseToolbar != null) mBaseToolbar.setVisibility(View.GONE);
    }


    /**
     * 不需要toolbar的 可以不用管
     *
     * @return
     */
    @Nullable
    protected abstract BaseToolbar.Builder setToolbar(@NonNull BaseToolbar.Builder builder);

    public void initLocale() {
        Locale locale = LanguageUtil.getUserLocale();
        if (locale.getLanguage().equals("en")) {
            LanguageUtil.updateLocale(new LanguageBean(Locale.ENGLISH, UIUtils.getString(R.string.english)).getLanguage());
        }else {
            LanguageUtil.updateLocale(new LanguageBean(Locale.CHINESE, UIUtils.getString(com.fd.baselibrary.R.string.zh)).getLanguage());
        }
    }



}
