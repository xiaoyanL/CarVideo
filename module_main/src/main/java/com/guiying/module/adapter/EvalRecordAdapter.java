package com.guiying.module.adapter;

import android.view.View;
import android.widget.TextView;

import com.fd.baselibrary.base.BaseRVHolder;
import com.fd.baselibrary.base.SelectedAdapter;
import com.fd.baselibrary.bean.AssessInfoListDTO;
import com.guiying.module.main.R;

public class EvalRecordAdapter extends SelectedAdapter<AssessInfoListDTO> {

    public EvalRecordAdapter() {
        super(R.layout.layout_eval_adapter);
    }

    @Override
    public void onBindVH(BaseRVHolder holder, AssessInfoListDTO data, int position) {
        holder.setText(R.id.time_tv,data.getAssessTime());
        holder.setText(R.id.num_tv,data.getTotalNum()+"");
        holder.setText(R.id.desc_tv,data.getVersionDes());
        TextView ps=holder.getView(R.id.position1);
        ps.setText(getData().size()-position+"");
        if (position==0){
            ps.setBackgroundResource(R.drawable.shape_circle_red);
        }else {
            ps.setBackgroundResource(R.drawable.shape_circle_grey);
        }
        if (position== getData().size()-1){
            View line=holder.getView(R.id.line);
            line.setVisibility(View.GONE);
        }
    }
}
