package com.mumu.mutools.demo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import com.mumu.kernel.permission.MuPermissionsTool;
import com.mumu.mutools.R;

import java.util.List;

public class SplashActivity extends AppCompatActivity {
    public static String[] STORAGE_PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO};
    public static int REQUEST_CODE = 233;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        permissionsDeal();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            permissionsDeal();
        }
    }

    /**
     * 权限处理
     */
    public void permissionsDeal() {
        MuPermissionsTool.requestPermission(this, REQUEST_CODE, new MuPermissionsTool.PermissionListener() {
            @Override
            public void onPermissionSucceed(int requestCode, List<String> grantedList) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onPermissionFailed(int requestCode, List<String> deniedList) {
                String[] strings = new String[deniedList.size()];
                deniedList.toArray(strings);
                MuPermissionsTool.requestRationalePermission(SplashActivity.this, this, strings);
            }

            @Override
            public void onPermissionsDialogCancel() {
                SplashActivity.this.finish();
            }
        }, STORAGE_PERMISSIONS);
    }
}
