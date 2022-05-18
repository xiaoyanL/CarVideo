package com.aliyun.apsaravideo.sophon.api;


import com.aliyun.apsaravideo.sophon.bean.ImageAsBean;
import com.fd.baselibrary.base.BasePresenter;
import com.fd.baselibrary.baseBean.TokenBean;
import com.fd.baselibrary.baseBean.UserBean;
import com.fd.baselibrary.bean.CallListBean;
import com.fd.baselibrary.bean.CheckCallDTO;
import com.fd.baselibrary.bean.CheckUpdate;
import com.fd.baselibrary.bean.ImgListDTO;
import com.fd.baselibrary.bean.ProjectImg;
import com.fd.baselibrary.bean.ProjectInfo;
import com.fd.baselibrary.bean.ProjectList;
import com.fd.baselibrary.network.NetworkTransformer;
import com.fd.baselibrary.utils.SPManager;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;


public class TestMvpPresenter extends BasePresenter {

    public Observable<UserBean> login(String account, String password) {
        return RetrofitClientapi.getTestService()
                .login(account, password)
                .compose(new NetworkTransformer<>(this));

    }

    public Observable<UserBean> forceLogin(String account, String password) {
        return RetrofitClientapi.getTestService()
                .forceLogin(account, password)
                .compose(new NetworkTransformer<>(this));

    }
    public Observable<ProjectList> Project_List(String keyword, int pageNum, int pageSize) {
        return RetrofitClientapi.getTestService().
                Project_List(keyword, pageNum, pageSize).
                compose(new NetworkTransformer<>(this));
    }

    public Observable<ProjectInfo> Project_Info(int id) {
        return RetrofitClientapi.getTestService().
                Project_info(id).
                compose(new NetworkTransformer<>(this));
    }

    public Observable<ProjectImg> Project_Img(int id, int pageNum, int pageSize) {
        return RetrofitClientapi.getTestService().
                Project_img(id,pageNum,pageSize).
                compose(new NetworkTransformer<>(this));
    }

    public Observable<ProjectImg> Project_delete_img(int id, List<ImgListDTO> list, int type) {
        ImageAsBean imageAsBean=new ImageAsBean(id,list,type);
        String imageAsBean1=new Gson().toJson(imageAsBean);
        return RetrofitClientapi1.getTestService()
                .Project_delete_img(imageAsBean1)
                .compose(new NetworkTransformer<>(this));
    }

    public Observable<CheckCallDTO> Check_call(int pageNum, int pageSize) {
        return RetrofitClientapi.getTestService().
                Check_call(pageNum,pageSize).
                compose(new NetworkTransformer<>(this));
    }

    public Observable<Boolean> Check_update(String  version) {
        return RetrofitClientapi.getTestService().
                Check_update(version).
                compose(new NetworkTransformer<>(this));
    }
    public Observable<CheckUpdate> Get_update() {
        return RetrofitClientapi.getTestService().
                Get_update().
                compose(new NetworkTransformer<>(this));
    }

    public Observable<TokenBean> token(String tyep,String channelId) {
        return RetrofitClientapi.getTestService().
                token(tyep,channelId).
                compose(new NetworkTransformer<>(this));
    }
}
