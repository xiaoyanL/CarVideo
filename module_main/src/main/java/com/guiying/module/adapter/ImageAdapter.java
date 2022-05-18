package com.guiying.module.adapter;

import android.util.Log;
import android.widget.ImageView;

import com.fd.baselibrary.base.BaseRVHolder;
import com.fd.baselibrary.base.SelectedAdapter;
import com.fd.baselibrary.bean.ImgListDTO;
import com.fd.baselibrary.utils.ImageUtil;
import com.guiying.module.main.R;

import static com.fd.baselibrary.api.HostUrl.IMAGE_URL;

public class ImageAdapter extends SelectedAdapter<ImgListDTO> {

    public ImageAdapter() {
        super(R.layout.layout_image_adapter);
    }

    @Override
    public void onBindVH(BaseRVHolder holder, ImgListDTO data, int position) {
        if (data==null)
            return;
        ImageView iv=holder.getView(R.id.img);
//        ImageUtil.loadrounded(iv,IMAGE_URL+data.getDiskPath());
        ImageUtil.load(iv,IMAGE_URL+data.getDiskPath());

    }
}
