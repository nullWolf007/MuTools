package com.mumu.kernel.ui.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mumu.kernel.R;

/**
 * Created by nullWolf on 2019/11/1.
 */
public class MuDialog extends Dialog {
    protected Context mContext;
    protected WindowManager.LayoutParams mLayoutParams;

    public MuDialog(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public MuDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initView(context);
    }

    protected MuDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView(context);
    }

    //初始化
    private void initView(Context context) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置透明背景
        this.getWindow().setBackgroundDrawableResource(R.drawable.ui_bg_transparent);
        mContext = context;
        Window window = this.getWindow();
        //设置控件Layout参数(透明度，高，位置等)
        mLayoutParams = window.getAttributes();
        mLayoutParams.alpha = 1f;
        window.setAttributes(mLayoutParams);
        if (mLayoutParams != null) {
            mLayoutParams.width = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
            mLayoutParams.height = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
            mLayoutParams.gravity = Gravity.CENTER;
        }
    }
}
