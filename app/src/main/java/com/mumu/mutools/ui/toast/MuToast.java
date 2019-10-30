package com.mumu.mutools.ui.toast;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mumu.mutools.R;
import com.mumu.mutools.kernel.MuTool;


/**
 * Created by nullWolf on 2019/10/29.
 */
public class MuToast {

    //默认字的颜色
    @ColorInt
    private static final int MU_DEFAULT_TEXT_COLOR = Color.parseColor("#FFFFFF");

    //info的背景颜色
    @ColorInt
    private static final int MU_INFO_COLOR = Color.parseColor("#3F51B5");

    //error的背景颜色
    @ColorInt
    private static final int MU_ERROR_COLOR = Color.parseColor("#FD4C5B");

    //success的背景颜色
    @ColorInt
    private static final int MU_SUCCESS_COLOR = Color.parseColor("#388E3C");

    //warning的背景颜色
    @ColorInt
    private static final int MU_WARNING_COLOR = Color.parseColor("#FFA900");

    //字体样式
    private static final String YS_TOAST_TYPEFACE = "sans-serif-condensed";

    //当前的toast
    private static Toast currentToast;

    //info形式toast 字符串
    public static void info(@NonNull String message) {
        info(MuTool.getContext(), message, Toast.LENGTH_SHORT, true).show();
    }

    //info形式toast 字符串id
    public static void info(@StringRes int id) {
        info(MuTool.getContext(), MuTool.getContext().getString(id), Toast.LENGTH_SHORT, true).show();
    }

    //info形式toast
    @CheckResult
    public static Toast info(@NonNull Context context, @NonNull String message, int duration, boolean withIcon) {
        return custom(context, message, getDrawable(context, R.drawable.ui_mu_info_outline_white_48dp), MU_DEFAULT_TEXT_COLOR, MU_INFO_COLOR, duration, withIcon, true);
    }

    //error形式toast 字符串
    public static void error(@NonNull String message) {
        error(MuTool.getContext(), message, Toast.LENGTH_SHORT, true).show();
    }

    //error形式toast 字符串id
    public static void error(@StringRes int id) {
        error(MuTool.getContext(), MuTool.getContext().getString(id), Toast.LENGTH_SHORT, true).show();
    }

    //error形式toast
    @CheckResult
    public static Toast error(@NonNull Context context, @NonNull String message, int duration, boolean withIcon) {
        return custom(context, message, getDrawable(context, R.drawable.ui_mu_clear_white_48dp), MU_DEFAULT_TEXT_COLOR, MU_ERROR_COLOR, duration, withIcon, true);
    }

    //success形式toast 字符串
    public static void success(@NonNull String message) {
        success(MuTool.getContext(), message, Toast.LENGTH_SHORT, true).show();
    }

    //success形式toast 字符串id
    public static void success(@StringRes int id) {
        success(MuTool.getContext(), MuTool.getContext().getString(id), Toast.LENGTH_SHORT, true).show();
    }

    //success形式toast
    @CheckResult
    public static Toast success(@NonNull Context context, @NonNull String message, int duration, boolean withIcon) {
        return custom(context, message, getDrawable(context, R.drawable.ui_mu_success_white_48dp), MU_DEFAULT_TEXT_COLOR, MU_SUCCESS_COLOR, duration, withIcon, true);
    }

    //warning形式toast 字符串
    public static void warning(@NonNull String message) {
        warning(MuTool.getContext(), message, Toast.LENGTH_SHORT, true).show();
    }

    //warning形式toast 字符串id
    public static void warning(@StringRes int id) {
        warning(MuTool.getContext(), MuTool.getContext().getString(id), Toast.LENGTH_SHORT, true).show();
    }

    //warning形式toast
    @CheckResult
    public static Toast warning(@NonNull Context context, @NonNull String message, int duration, boolean withIcon) {
        return custom(context, message, getDrawable(context, R.drawable.ui_mu_warning_outline_white_48dp), MU_DEFAULT_TEXT_COLOR, MU_WARNING_COLOR, duration, withIcon, true);
    }

    //toast的具体实现
    @CheckResult
    public static Toast custom(@NonNull Context context, @NonNull String message, Drawable icon, @ColorInt int textColor, @ColorInt int tintColor, int duration, boolean withIcon, boolean shouldTint) {
        if (currentToast == null) {
            currentToast = new Toast(context);
        }
        final View toastLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.ui_mu_widget_toast, null);
        final ImageView toastIcon = toastLayout.findViewById(R.id.ui_ys_widget_toast_iv_icon);
        final TextView toastTextView = toastLayout.findViewById(R.id.ui_ys_widget_toast_tv_msg);
        Drawable drawableFrame;

        if (shouldTint) {
            drawableFrame = tint9PatchDrawableFrame(context, tintColor);
        } else {
            drawableFrame = getDrawable(context, R.drawable.ui_mu_toast_frame);
        }
        setBackground(toastLayout, drawableFrame);

        if (withIcon) {
            if (icon == null) {
                throw new IllegalArgumentException("Avoid passing 'icon' as null if 'withIcon' is set to true");
            }
            setBackground(toastIcon, icon);
        } else {
            toastIcon.setVisibility(View.GONE);
        }

        toastTextView.setTextColor(textColor);
        toastTextView.setText(message);
        toastTextView.setTypeface(Typeface.create(YS_TOAST_TYPEFACE, Typeface.NORMAL));

        currentToast.setView(toastLayout);
        currentToast.setDuration(duration);
        return currentToast;
    }

    public static final Drawable tint9PatchDrawableFrame(@NonNull Context context, @ColorInt int tintColor) {
        final NinePatchDrawable toastDrawable = (NinePatchDrawable) getDrawable(context, R.drawable.ui_mu_toast_frame);
        toastDrawable.setColorFilter(new PorterDuffColorFilter(tintColor, PorterDuff.Mode.SRC_IN));
        return toastDrawable;
    }

    public static final void setBackground(@NonNull View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static final Drawable getDrawable(@NonNull Context context, @DrawableRes int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getDrawable(id);
        } else {
            return context.getResources().getDrawable(id);
        }
    }
}

