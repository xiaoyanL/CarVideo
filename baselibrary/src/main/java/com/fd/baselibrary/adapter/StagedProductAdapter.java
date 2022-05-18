package com.fd.baselibrary.adapter;

import android.graphics.Paint;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fd.baselibrary.R;
import com.fd.baselibrary.base.BaseRVHolder;
import com.fd.baselibrary.base.SelectedAdapter;
import com.fd.baselibrary.baseBean.QuaryHotPushBean;
import com.fd.baselibrary.utils.ImageUtil;

/**
 * 产品列表
 */
public class StagedProductAdapter extends SelectedAdapter<QuaryHotPushBean> {

    public StagedProductAdapter() {
//        mLayoutResId=R.layout.staged_product_item;
        super(R.layout.staged_product_item);
    }

    @Override
    public void onBindVH(BaseRVHolder holder, QuaryHotPushBean data, int position) {
        ImageUtil.displayCenterCropImage2(holder.getView(R.id.iv_icon), data.getProtCovr());
        holder.setText(R.id.tv_projectName, data.getProtName());
        TextView view1 = holder.getView(R.id.view1);
        TextView view2 = holder.getView(R.id.view2);
        TextView tv_price2 = holder.getView(R.id.tv_price2);
        TextView tv_price1 = holder.getView(R.id.tv_price1);
        if (data.getProtPrie() == null | TextUtils.isEmpty(data.getProtPrie()) | data.getProtPrie().equals("0")) {
            holder.setText(R.id.tv_price1, "价格面议");
            tv_price2.setVisibility(View.INVISIBLE);
            view1.setVisibility(View.GONE);
            view2.setVisibility(View.INVISIBLE);
            tv_price1.setTextSize(14);
        } else {
            holder.setText(R.id.tv_price1, data.getProtPrie());
            tv_price2.setVisibility(View.VISIBLE);
            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.VISIBLE);
            tv_price1.setTextSize(16);
        }

        if (data.getLablContList() != null) {
            if (data.getLablContList().size() > 0) {
                TextView tv_Label1 = holder.getView(R.id.tv_Label1);
                tv_Label1.setVisibility(View.VISIBLE);
                tv_Label1.setText(data.getLablContList().get(0));
            }
            if (data.getLablContList().size() > 1) {
                TextView tv_Label2 = holder.getView(R.id.tv_Label2);
                tv_Label2.setVisibility(View.VISIBLE);
                tv_Label2.setText(data.getLablContList().get(1));
            }


            LinearLayout ll_couppon_r = holder.getView(R.id.ll_Label);
            if (data.getCoupons() != null && data.getCoupons().size() > 0) {
                ll_couppon_r.setVisibility(View.VISIBLE);
                if (data.getCoupons().size() > 0) {
                    TextView tv_Coupon1 = holder.getView(R.id.tv_Label1);
                    tv_Coupon1.setVisibility(View.VISIBLE);
                    if (data.getCoupons().get(0).getCounType().equals("1")) {
                        tv_Coupon1.setText("满" + data.getCoupons().get(0).getCounThrd() + "元减" + data.getCoupons().get(0).getCounVale() + "元");
                    } else if (data.getCoupons().get(0).getCounType().equals("2")) {
                        if (java.lang.Double.valueOf(data.getCoupons().get(0).getCounVale()) >= 10) {
                            tv_Coupon1.setText("满" + data.getCoupons().get(0).getCounThrd() + "元享" + java.lang.Double.valueOf(data.getCoupons().get(0).getCounVale()) / 10 + "折");
                        } else {
                            tv_Coupon1.setText("满" + data.getCoupons().get(0).getCounThrd() + "元享" + java.lang.Double.valueOf(data.getCoupons().get(0).getCounVale()) / 100 + "折");
                        }
                    }
                }
                if (data.getCoupons().size() > 1) {
                    TextView tv_Coupon2 = holder.getView(R.id.tv_Label2);
                    tv_Coupon2.setVisibility(View.VISIBLE);
                    if (data.getCoupons().get(1).getCounType().equals("1")) {
                        tv_Coupon2.setText("满" + data.getCoupons().get(1).getCounThrd() + "元减" + data.getCoupons().get(1).getCounVale() + "元");
                    } else if (data.getCoupons().get(1).getCounType().equals("2")) {
                        if (java.lang.Double.valueOf(data.getCoupons().get(1).getCounVale()) >= 10) {
                            tv_Coupon2.setText("满" + data.getCoupons().get(1).getCounThrd() + "元享" + java.lang.Double.valueOf(data.getCoupons().get(1).getCounVale()) / 10 + "折");
                        } else {
                            tv_Coupon2.setText("满" + data.getCoupons().get(1).getCounThrd() + "元享" + java.lang.Double.valueOf(data.getCoupons().get(1).getCounVale()) / 100 + "折");
                        }
                    }
                }
            }
            if (data.getOrgnPric() != null && !TextUtils.isEmpty(data.getOrgnPric()) && !data.getOrgnPric().equals("0")) {
                tv_price2.setText(data.getOrgnPric());
            } else {
                tv_price2.setText(data.getProtPrie());
            }
            tv_price2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
        }
        View view = holder.getView(R.id.view);
        if (getItemCount() - 1 <= position) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }
}
