package com.fd.baselibrary.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fd.baselibrary.R;
import com.fd.baselibrary.base.BaseRVHolder;
import com.fd.baselibrary.base.SelectedAdapter;
import com.fd.baselibrary.baseBean.QuaryShaeBean;
import com.fd.baselibrary.utils.ImageUtil;

import java.util.HashMap;

/**
 * 文章列表
 */
public class Frragment_Find_Item_Adapter extends SelectedAdapter<QuaryShaeBean> {
    String url;
    private HashMap<String, Integer> mHeights;
    Integer viewHeight;
    public Frragment_Find_Item_Adapter() {
        super(R.layout.frragment_find_item_adapter);
        mHeights = new HashMap<>();
    }

    @Override
    public void onBindVH(BaseRVHolder viewHolder, QuaryShaeBean bean, int position) {
        viewHolder.setText(R.id.text, bean.getShaeTitl());
        viewHolder.setText(R.id.userNick, bean.getUserNick());
        viewHolder.setText(R.id.tv_raisLook, bean.getRaisLook());
        ImageView iv_userHead = viewHolder.getView(R.id.iv_userHead);
        ImageUtil.load( iv_userHead, bean.getUserHead());
        iv_userHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                QuaryShaeBean bean = (QuaryShaeBean) item;
//                Intent intent = new Intent(mContext, CommunityActivity1.class);
//                intent.putExtra("userCodeOthers", bean.getUserCode());
//                SystemUtils.gotoActivity(mContext, intent);
            }
        });

        ImageView iv_isContType = viewHolder.getView(R.id.iv_isContType);
        if (bean.getContType().equals("0")|bean.getContType().equals("3")) {
            iv_isContType.setVisibility(View.GONE);
        } else {
            iv_isContType.setVisibility(View.VISIBLE);
        }


        final ImageView image = viewHolder.getView(R.id.image);
        if (bean.getContType().equals("0"))
            url = bean.getUserPice();
        else if (bean.getContType().equals("3")){
            url = bean.getUserPice();
        }
        else if (bean.getContType().equals("1") | bean.getContType().equals("4"))
            url = bean.getVidoCovr();


        ViewGroup.LayoutParams params = image.getLayoutParams();//得到item的LayoutParams布局参数
        if (!mHeights.containsKey(position + "")) {
            viewHeight = getRandomHeight();
            mHeights.put(position + "", viewHeight);
        }
        params.height = mHeights.get(position + "");


//        params.height = heights.get(position);//把随机的高度赋予itemView布局
        image.setLayoutParams(params);//把params设置给itemView布局
        ImageUtil.load(image, url);
        TextView tv_Audit_status=viewHolder.getView(R.id.tv_Audit_status);
        if (bean.getExaeStas() == 1) {
            tv_Audit_status.setVisibility(View.GONE);
            iv_isContType.setImageResource(R.mipmap.icon_userscenter_delete);
            iv_isContType.setVisibility(View.VISIBLE);
            tv_Audit_status.setVisibility(View.VISIBLE);
            tv_Audit_status.setText("审核失败");
            tv_Audit_status.setBackgroundResource(R.drawable.shape_btn_red_white);
            tv_Audit_status.setTextColor(mContext.getResources().getColor(R.color.text_red));
//            if (listener != null) {
//                iv_isContType.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        listener.Delete(position);
//                    }
//                });
//            }
        }else if (bean.getExaeStas() == 2){
            tv_Audit_status.setVisibility(View.VISIBLE);
        }else {
            tv_Audit_status.setVisibility(View.GONE);
        }
    }

    private int getRandomHeight() {//得到随机item的高度
        return (int) (300 + Math.random() * 400);
    }

}
