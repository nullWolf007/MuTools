package com.mumu.kernel.ui.view.dialog;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mumu.kernel.R;
import com.mumu.kernel.toast.MuToast;

/**
 * to be a better man.
 *
 * @author nullWolf
 * @date 2019/11/1
 * <p>
 * 封装了从相册/相机获取图片的Dialog.
 */
public class MuDialogChooseImage extends MuDialog {

    //界面
    private TextView tv_title;//标题
    private TextView tv_camera;//相机
    private TextView tv_album;//相册
    private TextView tv_cancel;//取消

    private static LayoutType layoutType = LayoutType.TITLE;

    public MuDialogChooseImage(@NonNull Activity activity) {
        super(activity);
        initView(activity);
    }

    public MuDialogChooseImage(@NonNull Fragment fragment) {
        super(fragment.getContext());
        initView(fragment);
    }

    private void initView(final Activity activity) {
        init();

        tv_camera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //请求Camera权限
//                RxPhotoTool.openCameraImage(fragment);
                MuToast.info("相机");
                cancel();
            }
        });
        tv_album.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                RxPhotoTool.openLocalImage(fragment);
                MuToast.info("相册");
                cancel();
            }
        });
    }

    private void initView(final Fragment fragment) {
        init();

        tv_camera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //请求Camera权限
//                RxPhotoTool.openCameraImage(fragment);
                MuToast.info("相机");
                cancel();
            }
        });
        tv_album.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                RxPhotoTool.openLocalImage(fragment);
                MuToast.info("相册");
                cancel();
            }
        });
    }

    //提取出来一样的代码 init()
    private void init() {
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.ui_dialog_choose_image, null);
        tv_title = dialogView.findViewById(R.id.dialog_choose_image_tv_title);
        tv_camera = dialogView.findViewById(R.id.dialog_choose_image_tv_camera);
        tv_album = dialogView.findViewById(R.id.dialog_choose_image_tv_album);
        tv_cancel = dialogView.findViewById(R.id.dialog_choose_image_tv_cancel);

        switch (layoutType) {
            case TITLE://有标题
                tv_title.setVisibility(View.VISIBLE);
                break;
            case NO_TITLE://无标题
                tv_title.setVisibility(View.GONE);
                break;
            default:
                tv_title.setVisibility(View.VISIBLE);
                break;
        }

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                cancel();
            }
        });

        setContentView(dialogView);
        mLayoutParams.gravity = Gravity.BOTTOM;
    }

    //属性
    public static class Config {
        private LayoutType layoutType = LayoutType.TITLE;

        private Config() {
            // avoiding instantiation
        }

        @CheckResult
        public static Config getInstance() {
            return new Config();
        }

        @CheckResult
        public Config setLayoutType(LayoutType layoutType) {
            this.layoutType = layoutType;
            return this;
        }

        //重置属性
        public static void reset() {
            MuDialogChooseImage.layoutType = LayoutType.TITLE;
        }

        //属性复制到MuDialogChooseImage中
        public void apply() {
            MuDialogChooseImage.layoutType = layoutType;
        }
    }

    public enum LayoutType {
        TITLE, NO_TITLE
    }

}
