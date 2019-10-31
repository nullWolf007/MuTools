package com.mumu.mutools.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mumu.mutools.R;
import com.mumu.mutools.kernel.MuTool;
import com.mumu.mutools.ui.toast.MuToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //toast
    private TextView tv_toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MuTool.init(this);

        tv_toast = findViewById(R.id.main_tv_toast);

        tv_toast.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.main_tv_toast:
                intent = new Intent(this, ToastActivity.class);
                break;
        }
        if (null != intent) {
            startActivity(intent);
        }
    }
}
