package com.mumu.mutools.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mumu.kernel.MuTool;
import com.mumu.mutools.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //toast
    private TextView tv_toast;
    //sp
    private TextView tv_sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MuTool.init(this);

        tv_toast = findViewById(R.id.main_tv_toast);
        tv_sp = findViewById(R.id.main_tv_sp);

        tv_toast.setOnClickListener(this);
        tv_sp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.main_tv_toast://Toast
                intent = new Intent(this, ToastActivity.class);
                break;
            case R.id.main_tv_sp://SharedPresences
                intent = new Intent(this, SPActivity.class);
                break;
        }
        if (null != intent) {
            startActivity(intent);
        }
    }
}
