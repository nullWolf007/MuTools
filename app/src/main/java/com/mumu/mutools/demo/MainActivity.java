package com.mumu.mutools.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mumu.kernel.MuTool;
import com.mumu.mutools.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //toast
    private TextView tv_toast;
    //sp
    private TextView tv_sp;
    //dialog
    private TextView tv_dialog;
    //sidebar
    private TextView tv_sidebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MuTool.init(this);

        tv_toast = findViewById(R.id.main_tv_toast);
        tv_sp = findViewById(R.id.main_tv_sp);
        tv_dialog = findViewById(R.id.main_tv_dialog);
        tv_sidebar = findViewById(R.id.main_tv_sidebar);

        tv_toast.setOnClickListener(this);
        tv_sp.setOnClickListener(this);
        tv_dialog.setOnClickListener(this);
        tv_sidebar.setOnClickListener(this);
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
            case R.id.main_tv_dialog://dialog
                intent = new Intent(this, DialogActivity.class);
                break;
            case R.id.main_tv_sidebar://sidebar
                intent = new Intent(this, AZSidebarActivity.class);
                break;
        }
        if (null != intent) {
            startActivity(intent);
        }
    }
}
