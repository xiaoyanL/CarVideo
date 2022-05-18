package com.aliyun.apsaravideo.sophon.api;


import com.fd.baselibrary.api.HostUrl;
import com.fd.baselibrary.baseBean.TokenBean;
import com.fd.baselibrary.baseBean.UserBean;
import com.fd.baselibrary.bean.CallListBean;
import com.fd.baselibrary.bean.CheckCallDTO;
import com.fd.baselibrary.bean.CheckUpdate;
import com.fd.baselibrary.bean.ImgListDTO;
import com.fd.baselibrary.bean.ProjectImg;
import com.fd.baselibrary.bean.ProjectInfo;
import com.fd.baselibrary.bean.ProjectList;
import com.fd.baselibrary.network.BaseBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TestService {
    /**
     *   登录
     * @param account 账号
     * @param password 密码
     * @return
     */
    @FormUrlEncoded
    @POST(HostUrl.LOGIN)
    Observable<BaseBean<UserBean>> login(@Field("account") String account , @Field("password") String password );
    /**
     *   强制登录
     * @param account 账号
     * @param password 密码
     * @return
     */
    @FormUrlEncoded
    @POST(HostUrl.FORCELOGIN)
    Observable<BaseBean<UserBean>> forceLogin(@Field("account") String account , @Field("password") String password );

    /**
     * 分页查询项目列表
     * @param keyword  关键字
     * @param pageNum 当前页数
     * @param pageSize 每页展示条数
     * @return
     */
    @FormUrlEncoded
    @POST(HostUrl.PROJECTLIST)
    Observable<BaseBean<ProjectList>> Project_List(@Field("keyword") String keyword,@Field("pageNum") int  pageNum,@Field("pageSize") int pageSize);

    /**
     * 查看项目概况信息
     * @param id  id
     * @return
     */
    @GET(HostUrl.PROJECTINFO)
    Observable<BaseBean<ProjectInfo>> Project_info(@Query("id") int  id);

    /**
     * 分页查询项目图片
     * @param id  id
     * @param pageNum 当前页数
     * @param pageSize 每页展示条数
     * @return
     */
    @FormUrlEncoded
    @POST(HostUrl.PROJECTIMG)
    Observable<BaseBean<ProjectImg>> Project_img(@Field("id") int  id, @Field("pageNum") int  pageNum, @Field("pageSize") int pageSize);
    /**
     * 删除图片
     * @return
     */
    @FormUrlEncoded
    @POST(HostUrl.PROJECTDELETEIMG)
    Observable<BaseBean<ProjectImg>> Project_delete_img(@Field("json") String json);
    /**
     * 分页查询项目图片
     * @param pageNum 当前页数
     * @param pageSize 每页展示条数
     * @return
     */
    @FormUrlEncoded
    @POST(HostUrl.CHECKCALLRECORD)
    Observable<BaseBean<CheckCallDTO>> Check_call(@Field("pageNum") int  pageNum, @Field("pageSize") int pageSize);

    /**
     * 检查新版本data: true最新版,false需要更新
     * @param  version 版本号
     * @return
     */
    @GET(HostUrl.CHECKUPDATE)
    Observable<BaseBean<Boolean>> Check_update(@Query("version") String  version);

    /**
     * 获取最新版本
     * @return
     */
    @GET(HostUrl.GETNEWVERSION)
    Observable<BaseBean<CheckUpdate>> Get_update();

    /**
     * 获取最新版本
     * @return
     */
    @GET(HostUrl.TOKEN)
    Observable<BaseBean<TokenBean>> token(@Query("type") String  type,@Query("channelId") String channelId);
}
