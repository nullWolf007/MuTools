package com.mumu.kernel.permission;

/**
 * to be a better man.
 *
 * @author nullWolf
 * @date 2019/11/4
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MuPermissionsTool {

    private static int requestCode = 775;

    /**
     * 动态申请权限
     */
    public static void requestPermission(Activity activity, PermissionListener permissionListener, String... permissions) {
        requestPermission(activity, requestCode, permissionListener, permissions);
    }

    public static void requestPermission(Activity activity, int requestCode, PermissionListener permissionListener, String... permissions) {
        MuPermissionsTool.requestCode = requestCode;
        if (Build.VERSION.SDK_INT < 23) {
            if (permissionListener != null)
                permissionListener.onPermissionSucceed(MuPermissionsTool.requestCode, Arrays.asList(permissions));
        } else {
            try {
                //利用Fragment申请权限,不用开发者处理onRequestPermissionsResult了
                PermissionFragment permissionFragment = new PermissionFragment();
                permissionFragment.setPermission(permissionListener, MuPermissionsTool.requestCode, permissions);
                activity.getFragmentManager().beginTransaction().add(Window.ID_ANDROID_CONTENT, permissionFragment).commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 动态申请权限,被拒一次后申请前增加提示框
     */
    public static void requestRationalePermission(Activity activity, PermissionListener permissionListener, String... permissions) {
        requestRationalePermission(activity, MuPermissionsTool.requestCode, permissionListener, permissions);
    }

    public static void requestRationalePermission(final Activity activity, final int requestCode, final PermissionListener permissionListener, final String... permissions) {
        MuPermissionsTool.requestCode = requestCode;
        if (Build.VERSION.SDK_INT < 23) {
            permissionListener.onPermissionSucceed(MuPermissionsTool.requestCode, Arrays.asList(permissions));
        } else {
            if (isRationalePermission(activity, permissions)) {
                showRequestAgainDialog(activity, permissionListener, permissions);
            } else if (isAlwaysDeniedPermission(activity, permissions)) {
                showSettingDialog(activity, permissionListener);
            } else {
                requestPermission(activity, MuPermissionsTool.requestCode, permissionListener, permissions);
            }
        }
    }

    /**
     * 显示手动设置权限对话框
     */
    public static void showRequestAgainDialog(final Activity activity, final PermissionListener permissionListener, final String... permissions) {
        new AlertDialog.Builder(activity)
                .setTitle("权限已被拒绝")
                .setMessage("您已经拒绝过我们申请授权，请您同意授权，否则功能无法正常使用！")
                .setPositiveButton("同意", (dialog, which) -> {
                    requestPermission(activity, MuPermissionsTool.requestCode, permissionListener, permissions);
                })
                .setNegativeButton("取消", (dialog, which) -> {
                    permissionListener.onPermissionsDialogCancel();
                })
                .setCancelable(false)
                .show();
    }

    /**
     * 显示手动设置权限对话框
     */
    public static void showSettingDialog(final Activity activity, final PermissionListener permissionListener) {
        new AlertDialog.Builder(activity)
                .setTitle("权限申请失败")
                .setMessage("我们需要的一些权限被您拒绝且不再询问，请您到设置页面手动授权，否则功能无法正常使用！")
                .setPositiveButton("去设置", (dialog, which) -> {
                    Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                    Uri uri = Uri.fromParts("package", activity.getPackageName(), (String) null);
                    intent.setData(uri);
                    activity.startActivityForResult(intent, MuPermissionsTool.requestCode);
                })
                .setNegativeButton("取消", (dialog, which) -> {
                    permissionListener.onPermissionsDialogCancel();
                })
                .setCancelable(false)
                .show();
    }

    /**
     * 是否有权限
     */
    public static boolean hasPermission(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        } else {
            for (String permission : permissions) {
                boolean hasPermission = context.checkPermission
                        (permission, Process.myPid(), Process.myUid()) == PackageManager.PERMISSION_GRANTED;
                if (!hasPermission) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 是否被拒绝了一次了
     */
    public static boolean isRationalePermission(Activity activity, String... permissions) {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        } else {
            for (String permission : permissions) {
                //有一个被拒了一次返回
                if (activity.shouldShowRequestPermissionRationale(permission)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 是否永远拒绝了
     * 需要在被拒绝后调用
     */
    public static boolean isAlwaysDeniedPermission(Activity activity, String... permissions) {
        return isAlwaysDeniedPermission(activity, Arrays.asList(permissions));
    }

    /**
     * 是否永远拒绝了
     * 需要在被拒绝后调用
     */
    public static boolean isAlwaysDeniedPermission(Activity activity, List<String> permissions) {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        } else {
            for (String permission : permissions) {
                //有一个被永远拒了返回
                //shouldShowRequestPermissionRationale只有拒绝了之后返回true
                if (!activity.shouldShowRequestPermissionRationale(permission)) {
                    return true;
                }
            }
            return false;
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static class PermissionFragment extends Fragment {
        private PermissionListener permissionListener;
        private int requestCode;
        private String[] permissions;

        public void setPermission(PermissionListener permissionListener, int requestCode, String... permissions) {
            this.permissionListener = permissionListener;
            this.requestCode = requestCode;
            this.permissions = permissions;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return new View(container.getContext());
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            try {
                requestPermissions(permissions, requestCode);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //申请权限回调
        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (permissionListener != null) {
                List<String> grantedList = new ArrayList<>();
                List<String> deniedList = new ArrayList<>();
                for (int i = 0; i < permissions.length; ++i) {
                    if (grantResults[i] == 0) {
                        grantedList.add(permissions[i]);
                    } else {
                        deniedList.add(permissions[i]);
                    }
                }
                if (deniedList.isEmpty()) {
                    permissionListener.onPermissionSucceed(requestCode, grantedList);
                } else {
                    permissionListener.onPermissionFailed(requestCode, deniedList);
                }
            }
            getActivity().getFragmentManager().beginTransaction().remove(this).commit();
        }
    }

    public interface PermissionListener {
        //权限申请成功
        void onPermissionSucceed(int requestCode, List<String> grantedList);

        //权限申请失败
        void onPermissionFailed(int requestCode, List<String> deniedList);

        //取消dialog
        void onPermissionsDialogCancel();
    }
}
