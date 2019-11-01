package com.mumu.mutools.demo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import com.mumu.kernel.permission.MuPermissionsTool;
import com.mumu.kernel.toast.MuToast;
import com.mumu.mutools.R;

public class SplashActivity extends AppCompatActivity implements MuPermissionsTool.IPermissionsResult {

    public static String[] STORAGE_PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        MuPermissionsTool.getInstance().checkPermissions(this, STORAGE_PERMISSIONS, this);
    }

    @Override
    public void passPermissions() {
        MuToast.info("权限全部通过");
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void forbidPermissions() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MuPermissionsTool.getInstance().onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
}
