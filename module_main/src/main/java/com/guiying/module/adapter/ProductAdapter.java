package com.guiying.module.adapter;

import android.util.Log;

import com.fd.baselibrary.base.BaseRVHolder;
import com.fd.baselibrary.base.SelectedAdapter;
import com.fd.baselibrary.bean.ListDTO;
import com.guiying.module.main.R;

public class ProductAdapter  extends SelectedAdapter<ListDTO> {

    public ProductAdapter() {
        super(R.layout.layout_product_adapter);
    }

    @Override
    public void onBindVH(BaseRVHolder holder, ListDTO data, int position) {
        holder.setText(R.id.title_name,data.getProjectSectionName()+"");
    }
}
