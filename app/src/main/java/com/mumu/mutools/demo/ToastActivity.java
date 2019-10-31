package com.mumu.mutools.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mumu.kernel.MuTool;
import com.mumu.mutools.R;
import com.mumu.kernel.toast.MuToast;

public class ToastActivity extends AppCompatActivity implements View.OnClickListener {

    //允许toast
    private TextView tv_info;
    private TextView tv_error;
    private TextView tv_success;
    private TextView tv_warning;

    //不允许toast
    private TextView tv_no_queue_info;
    private TextView tv_no_queue_error;
    private TextView tv_no_queue_success;
    private TextView tv_no_queue_warning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);

        MuTool.init(this);

        tv_info = findViewById(R.id.main_tv_info);
        tv_error = findViewById(R.id.main_tv_error);
        tv_success = findViewById(R.id.main_tv_success);
        tv_warning = findViewById(R.id.main_tv_warning);

        tv_no_queue_info = findViewById(R.id.toast_no_queue_tv_info);
        tv_no_queue_error = findViewById(R.id.toast_no_queue_tv_error);
        tv_no_queue_success = findViewById(R.id.toast_no_queue_tv_success);
        tv_no_queue_warning = findViewById(R.id.toast_no_queue_tv_warning);

        tv_info.setOnClickListener(this);
        tv_error.setOnClickListener(this);
        tv_success.setOnClickListener(this);
        tv_warning.setOnClickListener(this);


        tv_no_queue_info.setOnClickListener(this);
        tv_no_queue_error.setOnClickListener(this);
        tv_no_queue_success.setOnClickListener(this);
        tv_no_queue_warning.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_tv_info:
                MuToast.Config.reset();
                MuToast.info(R.string.test);
                break;
            case R.id.main_tv_error:
                MuToast.Config.reset();
                MuToast.error(R.string.test);
                break;
            case R.id.main_tv_success:
                MuToast.Config.reset();
                MuToast.success(R.string.test);
                break;
            case R.id.main_tv_warning:
                MuToast.Config.reset();
                MuToast.warning(R.string.test);
                break;
            case R.id.toast_no_queue_tv_info:
                MuToast.Config.getInstance().allowQueue(false).apply();
                MuToast.info(R.string.test);
                break;
            case R.id.toast_no_queue_tv_error:
                MuToast.Config.getInstance().allowQueue(false).apply();
                MuToast.error(R.string.test);
                break;
            case R.id.toast_no_queue_tv_success:
                MuToast.Config.getInstance().allowQueue(false).apply();
                MuToast.success(R.string.test);
                break;
            case R.id.toast_no_queue_tv_warning:
                MuToast.Config.getInstance().allowQueue(false).apply();
                MuToast.warning(R.string.test);
                break;
        }
    }
}
