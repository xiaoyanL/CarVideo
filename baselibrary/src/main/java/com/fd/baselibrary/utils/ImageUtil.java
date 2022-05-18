package com.fd.baselibrary.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.fd.baselibrary.R;
import com.fd.baselibrary.base.BaseActivity;
import com.fd.baselibrary.dialog.LoadingDialog;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ImageUtil {
    public static final String TAG = ImageUtil.class.getName();

    /**
     * 七牛地址
     *
     * @param url
     * @return
     */
    public static String getImageUrl(String url) {
        return "自己定义要添加的路径" + url;
    }


    /**
     * 检查url 是否拼接
     *
     * @param url
     * @return
     */
    public static String checkUrl(String url) {
        //有完整路径的url不用拼接 本地图片也不用
        if (url != null && (url.startsWith("http://")
                || url.startsWith("https://")
                || url.startsWith("file:///android_asset/")
                || url.startsWith("file:///android_asset/")
                || url.startsWith(FileUtil.getAppRootDir().getAbsolutePath()))) {
        } else {
            url = getImageUrl(url);
        }

        return url;
    }

    /**
     * 检查url集合 是否拼接
     *
     * @param urlList
     * @return
     */
    public static List<String> checkUrl(List<String> urlList) {
        List<String> imgList = new ArrayList<>();
        if (EmptyUtil.isEmpty(urlList)) {
            return imgList;
        }
        for (String path : urlList) {
            imgList.add(checkUrl(path));
        }
        return imgList;
    }


    /**
     * 加载图片正方形图片
     * 默认图是正方形
     *
     * @param imageView
     * @param url
     */
    public static void load(ImageView imageView, @Nullable String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imageView);
    }

    /**
     * 加载drawable资源
     *
     * @param imageView
     * @param resId
     */
    public static void loadSrc(ImageView imageView, int resId) {
        Glide.with(imageView.getContext())
                .load(resId)
                .apply(RequestOptions.noAnimation()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imageView);
    }

    /**
     * 加载本地视频 自动获取缩略图
     *
     * @param imageView
     * @param path
     */
    public static void loadLocalVideo(ImageView imageView, @Nullable String path) {
        Glide.with(imageView.getContext())
                .load(path)
                .apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imageView);
    }

    /**
     * 加载本地图片 最好override一下宽高，不然滑动会卡顿
     *
     * @param imageView
     * @param url
     * @param width
     */
    public static void loadLocalSize(ImageView imageView, @Nullable String url, int width) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .override(width, width)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imageView);
    }

    /**
     * 加载头像
     *
     * @param imageView
     * @param url
     */
    public static void loadHeader(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(checkUrl(url))
                .apply(RequestOptions.placeholderOf(R.mipmap.touxiang)
                        .error(R.mipmap.touxiang)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imageView);
    }
    /**
     * 加载圆角图片
     *
     * @param imageView
     * @param url       链接地址
     */

    public static void loadrounded(ImageView imageView, @Nullable String url) {
        //设置图片大小
        RoundedCorners roundedCorners = new RoundedCorners(32);
        //通过RequestOptions扩展功能,
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners)
                .placeholder(new ColorDrawable(Color.GRAY))//设置占位图
                .error(R.mipmap.ic_launcher)//设置错误图片
                .fallback(new ColorDrawable(Color.RED))
                .override(300, 300);//采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗

        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .apply(options)
                //fixme 注意加载圆角的时候，注释掉下面的方法
//                .centerCrop()//centerCrop()是将图片按照比例放大到imageview的尺寸；
//                .fitCenter()//fitCenter()是将图片按照比例缩小到imageview的尺寸；
                .into(imageView);
    }
    /**
     * 加载大头像
     * 只是默认图不同
     *
     * @param imageView
     * @param url
     */
    public static void loadBigHeader(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(checkUrl(url))
                .apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imageView);
    }

    /**
     * @Desction : 图片填满控件左上右上圆角
     * @auther : Chopper
     * Creater at 2018/10/26 9:28
     */
    public static void displayCenterCropImage2(ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
      /* if (TextUtils.isEmpty(url))
           return;*/
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
                .transform(new GlideRoundTransform(20))
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);

        Glide.with(imageView.getContext()).asBitmap().
                load(url).apply(options).
                into(imageView);
    }

    /**
     * 没有占位图
     *
     * @param imageView
     * @param url
     */
    public static void loadNoHolder(ImageView imageView, @Nullable String url) {
        Glide.with(imageView.getContext())
                .load(checkUrl(url))
                .apply(RequestOptions.noAnimation()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                //  .placeholder(R.mipmap.ic_launcher)
                //  .error(R.mipmap.ic_launcher)
                .into(imageView);
    }


    /**
     * 高斯模糊
     *
     * @param imageView
     * @param url
     */
    public static void loadBlur(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(checkUrl(url))
                .apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .dontAnimate()
                        // .bitmapTransform(new BlurTransformation(imageView.getContext(), 1, 3))
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imageView);
    }

    public static void loadBlur(ImageView imageView, String url, int radius, int sampling) {
        // radius "23":模糊度；sampling "4":图片缩放4倍后再进行模糊a
        Glide.with(imageView.getContext())
                .load(checkUrl(url))
                .apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .dontAnimate()
                        // .bitmapTransform(new BlurTransformation(imageView.getContext(), radius, sampling))
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imageView);
    }


    public static void loadAsGif(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .asGif()
                .thumbnail(0.5f)
                .load(checkUrl(url))
                .apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imageView);
    }

    /**
     * 清除缓存
     * {@link FileUtil#clearImageAllCache()}
     *
     * @param context
     * @return
     */
    public static Observable<Boolean> clearCache(BaseActivity context) {
        return Observable.create((ObservableOnSubscribe<Boolean>) e -> {
            // GlideApp.get(context).clearDiskCache();
            FileUtil.clearImageAllCache();
            e.onNext(true);
            e.onComplete();

        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> context.showProgressDialog())
                .doOnTerminate(context::hideProgressDialog)
                .compose(context.bindUntilEvent(ActivityEvent.DESTROY));

    }

    /**
     * 保存图片集合到sd卡
     *
     * @param context
     * @param imgList
     * @param isSilentLoad 是否静默下载
     */
    public static void loadImagesToSDCard(Activity context, List<String> imgList, boolean isSilentLoad) {
        if (imgList == null || imgList.isEmpty())
            return;
        //申请权限
        RxPermissionsUtil.request(context, RxPermissionsUtil.STORAGE).subscribe(aBoolean -> {
            if (aBoolean) {
                LoadingDialog dialog = new LoadingDialog(context);
                if (!isSilentLoad) {
                    dialog.show();
                }
                Observable.create((ObservableOnSubscribe<List<String>>) e -> {
                    List<String> count = new ArrayList<String>();
                    for (String path : imgList) {
                        save2SDCard(context, ImageUtil.checkUrl(path))
                                .subscribe(bitmap -> {
                                    saveBitmap2File(context, bitmap, FileUtil.getAppRootDir(), path.hashCode() + ".jpeg");
                                    count.add(path);
                                    if (!e.isDisposed())
                                        e.onNext(count);
                                }, throwable -> {
                                    if (!isSilentLoad) ToastUtil.s(path + "下载失败");
                                });
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(count -> {
                            if (count.size() == imgList.size()) {
                                if (!isSilentLoad) {
                                    dialog.dismiss();
                                    ToastUtil.l(String.format("图片已保存至%s文件夹内", FileUtil.getAppRootDir()));
                                }
                            }
                        }, throwable -> {
                            if (!isSilentLoad) {
                                dialog.dismiss();
                                ToastUtil.s("下载失败");
                            }
                        });
            } else {//被拒绝 要去再次申请
                RxPermissionsUtil.shouldShowRequestPermissionRationale(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(aBoolean1 -> {
                            if (aBoolean1) {// 查询是否 应该提醒
                                RxPermissionsUtil.showPermissionDialog(context,
                                        "权限申请", "此功能需要读写存储权限，请授权",
                                        (dialog, which) -> loadImagesToSDCard(context, imgList, isSilentLoad));
                            } else {//选中了禁止以后提醒
                                RxPermissionsUtil.showLackPermissionDialog(context);
                            }
                        });
            }
        });
    }

    public static void loadImagesToSDCard(Activity activity, List<String> imageList) {
        loadImagesToSDCard(activity, imageList, false);
    }

    /**
     * 下载图片到本地SDCard
     *
     * @param context
     * @param imageUrl
     * @param saveName
     * @param isSilentLoad
     */
    public static void loadImageToSDCard(Activity context, String imageUrl, @NonNull String saveName, boolean isSilentLoad) {
        if (EmptyUtil.isEmpty(imageUrl))
            return;

        //申请权限
        RxPermissionsUtil.request(context, RxPermissionsUtil.STORAGE).subscribe(aBoolean -> {
            if (aBoolean) {
                LoadingDialog dialog = new LoadingDialog(context);
                if (!isSilentLoad) {
                    dialog.show();
                }
                Observable.create((ObservableOnSubscribe<String>) e -> {
                    save2SDCard(context, ImageUtil.checkUrl(imageUrl))
                            .subscribe(bitmap -> {
                                saveBitmap2File(context, bitmap, FileUtil.getAppRootDir(), saveName);
                                e.onNext(" ");
                            }/*, throwable -> {
                                if (!isSilentLoad) ToastUtil.s(R.string.load_img_failed);
                                e.onNext(" ");
                            }*/);
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(count -> {
                            if (!isSilentLoad) {
                                dialog.dismiss();
                                ToastUtil.l(String.format("图片已保存至%s文件夹内", FileUtil.getAppRootDir()));
                            }
                        }, throwable -> {
                            if (!isSilentLoad) {
                                dialog.dismiss();
                                ToastUtil.s("保存失败");
                            }
                        });
            } else {//被拒绝 要去再次申请
                RxPermissionsUtil.shouldShowRequestPermissionRationale(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(aBoolean1 -> {
                            if (aBoolean1) {// 查询是否 应该提醒
                                RxPermissionsUtil.showPermissionDialog(context,
                                        "权限申请", "此功能需要读写存储权限，请授权",
                                        (dialog, which) -> loadImageToSDCard(context, imageUrl, saveName, isSilentLoad));
                            } else {//选中了禁止以后提醒
                                RxPermissionsUtil.showLackPermissionDialog(context);
                            }
                        });
            }
        });
    }

    public static void loadImageToSDCard(Activity activity, String imageUrl) {
        loadImageToSDCard(activity, imageUrl, imageUrl.hashCode() + ".jpeg", false);
    }


    /**
     * @param context
     * @param url     图片的下载地址
     */
    public static Observable<Bitmap> save2SDCard(final Context context, final String url) {
        L.d(TAG, "save2SDCard url=" + url);
        return Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(ObservableEmitter<Bitmap> e) throws Exception {
                Bitmap bitmap = Glide.with(context)
                        .asBitmap()
                        .load(url)
                        .submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .get();

                if (!e.isDisposed())
                    e.onNext(bitmap);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 将Bitmap保存为图片文件
     *
     * @param context
     * @param bitmap
     * @param dirFile
     * @param saveName
     */
    public static void saveBitmap2File(Context context, Bitmap bitmap, File dirFile, String saveName) {
        if (dirFile != null && dirFile.exists() && dirFile.isDirectory()) {

            File file = new File(dirFile, saveName);
            L.d(TAG, "saveBitmap2File  file.getAbsolutePath()=" + file.getAbsolutePath());
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));//刷新相册
        } else {
            throw new RuntimeException("the file is not exists or it is not directory !");
        }
    }


}
