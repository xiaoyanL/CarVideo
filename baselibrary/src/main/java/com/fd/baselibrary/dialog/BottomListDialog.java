package com.fd.baselibrary.dialog;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fd.baselibrary.R;
import com.fd.baselibrary.base.BaseDialogFragment;
import com.fd.baselibrary.base.BaseRVAdapter;
import com.fd.baselibrary.base.BaseRVHolder;
import com.fd.baselibrary.widget.ColorDividerDecoration;

import java.util.List;

/**
 * Created by MirkoWu on 2017/3/29 0029.
 */

public class BottomListDialog extends BaseDialogFragment  {

    BaseRVAdapter<String> bottomListAdapter;
    boolean hideCancelBtn = false;//默认不显示
    boolean useRoundBackground = false;// 是否使用圆角背景
    private String title;
    List<String> data;

    private RecyclerView mRecyclerView;
    private TextView tvTitle;
    private TextView tvCancel;

    @Override
    public int getLayoutId() {
        return useRoundBackground ? R.layout.dialog_bottom_round : R.layout.dialog_bottom;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //设置dialog在屏幕的位置 不用使其铺满整个屏幕 ，
        // 也解决getDialog().getWindow().setLayout(MATCH_PARENT，MATCH_PARENT）和 setCancelable(true)冲突了
        //该方法只对根布局为LinearLayout 适用
        getDialog().getWindow().setGravity(Gravity.BOTTOM);

        WindowManager.LayoutParams lp = getDialog().getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes(lp);
        getDialog().getWindow().getAttributes().windowAnimations = com.fd.baselibrary.R.style.dialogAnim;
        //使dialog和软键盘可以共存
        getDialog().getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initContentView() {
        super.initContentView();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        setCancelable(true);
    }

    @Override
    public void initialize() {
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvCancel = (TextView) findViewById(R.id.tvCancel);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);

        tvTitle.setText(title);
        tvTitle.setVisibility(TextUtils.isEmpty(title) ? View.GONE : View.VISIBLE);
        tvCancel.setVisibility(hideCancelBtn ? View.GONE : View.VISIBLE);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomListDialog.this.dismiss();
            }
        });
        mRecyclerView.addItemDecoration(new ColorDividerDecoration(LinearLayoutManager.VERTICAL,
                Color.parseColor("#CCCCCC"), 1, ColorDividerDecoration.MIDDLE));

        bottomListAdapter = new BaseRVAdapter<String>(R.layout.item_bottom) {
            @Override
            public void onBindVH(BaseRVHolder holder, String data, int position) {
                holder.setText(R.id.tvContent, data);
            }
        };
        bottomListAdapter.setNewData(data);
        if (onItemClickListener != null)
            bottomListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    onItemClickListener.onItemClick(BottomListDialog.this, bottomListAdapter.getItem(position), position);
                    BottomListDialog.this.dismiss();
                }
            });

        mRecyclerView.setAdapter(bottomListAdapter);
    }

    public BottomListDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public BottomListDialog setData(List<String> data) {
        this.data = data;
        return this;
    }

    public BottomListDialog hideCancelBtn() {
        this.hideCancelBtn = true;
        return this;
    }

    public BottomListDialog useRoundBackground() {
        this.useRoundBackground = true;
        return this;
    }

    public BottomListDialog setOnItemClickListener(OnItemClickListener<String> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    public void show(AppCompatActivity activity) {
        this.show(activity.getSupportFragmentManager(), "");
    }


    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener<T> {
        void onItemClick(BottomListDialog dialog, T data, int position);
    }

}
