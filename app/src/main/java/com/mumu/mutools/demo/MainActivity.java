package com.mumu.mutools.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mumu.mutools.R;
import com.mumu.mutools.kernel.MuTool;
import com.mumu.mutools.ui.toast.MuToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //toast
    private TextView tv_info;
    private TextView tv_error;
    private TextView tv_success;
    private TextView tv_warning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MuTool.init(this);

        tv_info = findViewById(R.id.main_tv_info);
        tv_error = findViewById(R.id.main_tv_error);
        tv_success = findViewById(R.id.main_tv_success);
        tv_warning = findViewById(R.id.main_tv_warning);

        tv_info.setOnClickListener(this);
        tv_error.setOnClickListener(this);
        tv_success.setOnClickListener(this);
        tv_warning.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_tv_info:
                MuToast.info(R.string.test);
                break;
            case R.id.main_tv_error:
                MuToast.error(R.string.test);
                break;
            case R.id.main_tv_success:
                MuToast.success(R.string.test);
                break;
            case R.id.main_tv_warning:
                MuToast.warning(R.string.test);
                break;

        }
    }
}
