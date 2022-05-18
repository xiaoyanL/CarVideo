package com.fd.baselibrary.base;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class JGSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int hspace = 0;
    private int vspace = 0;

    public JGSpaceItemDecoration(int hspace, int vspace) {
        this.hspace = hspace;
        this.vspace = vspace;
    }

    @SuppressLint({"NewApi"})
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.right = this.hspace;
        outRect.bottom = this.vspace;
    }
}