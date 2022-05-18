package com.guiying.module.adapter;

import android.view.View;
import android.widget.ImageView;

import com.fd.baselibrary.base.BaseRVHolder;
import com.fd.baselibrary.base.SelectedAdapter;
import com.fd.baselibrary.bean.ImgListDTO;
import com.fd.baselibrary.utils.ImageUtil;
import com.guiying.module.main.R;
import com.makeramen.roundedimageview.RoundedImageView;

import static com.fd.baselibrary.api.HostUrl.HOST_URL;
import static com.fd.baselibrary.api.HostUrl.IMAGE_URL;

public class ImageInfoAdapter extends SelectedAdapter<ImgListDTO> {
    private onClickCallBack clickCallBack=null;
    public ImageInfoAdapter() {
        super(R.layout.layout_image_info_adapter);
    }

    @Override
    public void onBindVH(BaseRVHolder holder, ImgListDTO data, int position) {
        if (data==null)
            return;
        RoundedImageView iv=holder.getView(R.id.img);
//        ImageUtil.loadrounded(iv,IMAGE_URL+data.getDiskPath());
        ImageUtil.load(iv,IMAGE_URL+data.getDiskPath());
        holder.getView(R.id.iv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickCallBack!=null){
                    clickCallBack.onClick(position);
                }
            }
        });

    }

    public interface onClickCallBack {
        void onClick(int pos);
    }

    public void setOnClick(onClickCallBack clickcallBack){
        clickCallBack=clickcallBack;
    }
}
