package com.fd.baselibrary.api;

import android.view.View;

import androidx.annotation.Keep;

import com.fd.baselibrary.base.BaseFragment;

@Keep
public interface IViewDelegate {
    BaseFragment getFragment(String name);

    View getView(String name);

}
