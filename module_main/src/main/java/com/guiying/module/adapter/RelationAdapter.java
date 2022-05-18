package com.guiying.module.adapter;

import com.fd.baselibrary.base.BaseRVHolder;
import com.fd.baselibrary.base.SelectedAdapter;
import com.fd.baselibrary.bean.CallListBean;
import com.fd.baselibrary.network.BaseBean;
import com.guiying.module.main.R;

public class RelationAdapter extends SelectedAdapter<CallListBean> {

    public RelationAdapter() {
        super(R.layout.layout_relation_adapter);
    }

    @Override
    public void onBindVH(BaseRVHolder holder, CallListBean data, int position) {
        holder.setText(R.id.name, data.getCallOutUserName() + "");
        holder.setText(R.id.position, data.getUserGroupName() + "");
        holder.setText(R.id.startTime, data.getStartTime() + "");
        holder.setText(R.id.duration, secToTime((data.getCallDuration()/1000)) + "");
    }

    /**
     * 将秒数转化为时分秒
     *
     * @param time 时间段对应的秒数
     * @return 时分秒格式时间戳
     */

    public static String secToTime(int time) {
        int hour = 0;

        int minute = 0;

        int second = 0;

        if (time <= 0) {
            return "00:00";

        } else {
//            if (time >= 3600) {
//                hour = time / 3600;
//
//                time = time % 3600;
//
//            }
//
//            if (time >= 60) {
//                minute = time / 60;
//
//                second = time % 60;
//
//            }
//            if (time < 60) {
//                second = time;
//            }
            hour = time / 3600;
            minute = time / 60;
            second = time % 60;
            return timeFormat(hour) + "小时" + timeFormat(minute) + "分钟" + timeFormat(second) + "秒";

        }

    }

    public static String timeFormat(int num) {
        String retStr = null;

        if (num > 0 && num < 10) {
            retStr = "0" + Integer.toString(num);

        } else {
            retStr = "" + num;

        }

        return retStr;

    }

}
